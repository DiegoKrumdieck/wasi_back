package com.middevs.wasi.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.middevs.wasi.table.District;
import com.middevs.wasi.table.Feature;
import com.middevs.wasi.table.RealEstate;
import com.middevs.wasi.table.RealEstateFeature;
import com.middevs.wasi.table.Search;
import com.middevs.wasi.table.SearchDetail;
import com.middevs.wasi.table.Service;
import com.middevs.wasi.table.TypeInterestPlace;
import com.middevs.wasi.table.TypeRealEstate;

public class Utils {
	public static LocalDateTime obtenerFechaHoraActual() {
		return LocalDateTime.now(ZoneId.of("America/Lima"));
	}
	
	public static List<SearchDetail> calcularPorcentajeDeCoincidencia(List<RealEstate>realEstates,Integer antiquityRealStateSearch, 
			Integer numBedrooms,Integer numBathrooms, Set<TypeInterestPlace> typesInterestPlace,
			Set<Feature> features, Set<Service> services, Search search){
		
		
		List<SearchDetail> searchDetails = new ArrayList<SearchDetail>();
		
		for(RealEstate re : realEstates) {
			List<Double> weights = new ArrayList<Double>();
			
			if(antiquityRealStateSearch != null) {
				Double weightAntiquity = (re.getAntiquityRealEstate() <= antiquityRealStateSearch)? 1.0 : 0.5;
				weights.add(weightAntiquity); 
			}
			
			if(numBedrooms != null) {
				Double weightBedrooms = (re.getNumBedrooms() == numBedrooms)? 1.0 : 0.75;
				weights.add(weightBedrooms);
			}
			
			if(numBathrooms != null) {
				Double weightBathrooms = (re.getNumBathrooms() == numBathrooms)? 1.0 : 0.75;
				weights.add(weightBathrooms); 
			}
			
			if(typesInterestPlace != null) {
				int count = 0; 
				for(TypeInterestPlace tip : typesInterestPlace) {
					for(TypeInterestPlace tipRe : re.getListEstateInteredDetail()) {
						if(tip.getIdTypeInterestPlace().equals(tipRe.getIdTypeInterestPlace())) {
							count++; 
						}
					}
				}
				Double weightInterestPlaces = (double)count/typesInterestPlace.size();
				weights.add(weightInterestPlaces);
			}
			
			if(features != null) {
				int count = 0; 
				for(Feature f : features) {
					for(RealEstateFeature fRe : re.getListFeatures()) {
						if(f.getIdFeature().equals(fRe.getFeature().getIdFeature())) {
							count++; 
						}
					}
				}
				Double weightFeatures = (double)count/features.size();
				weights.add(weightFeatures);
			}
			
			if(services != null) {
				int count = 0; 
				for(Service s : services) {
					for(Service sRe : re.getListServices()) {
						if(s.getIdService().equals(sRe.getIdService())) {
							count++; 
						}
					}
				}
				Double weightServices = (double)count/services.size();
				weights.add(weightServices);
			}
			Double sumWeights = 0.0; 
			for(Double w : weights) {
				sumWeights += w; 
			}
			Integer coincidenceRate = (int) (sumWeights*100/weights.size());
			searchDetails.add(new SearchDetail(search, re, coincidenceRate)); 
		}
		return searchDetails;
	}
	
}
