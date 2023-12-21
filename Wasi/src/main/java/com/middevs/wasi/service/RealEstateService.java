package com.middevs.wasi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.middevs.wasi.dto.RealEstateTOCSVDTO;
import com.middevs.wasi.dto.RegisterSearchDTO;
import com.middevs.wasi.repository.RealEstateJpaRepository;
import com.middevs.wasi.table.District;
import com.middevs.wasi.table.Feature;
import com.middevs.wasi.table.RealEstate;
import com.middevs.wasi.table.RealEstateFeature;
import com.middevs.wasi.table.TypeInterestPlace;
import com.middevs.wasi.table.TypeRealEstate;

import java.util.stream.Collectors;
@Service
public class RealEstateService {
	@Autowired
	RealEstateJpaRepository realEstateRepo; 
	
	public RealEstate findById(Integer idRealEstate) {
		return realEstateRepo.findById(idRealEstate).orElse(null);
	}
	
	public List<RealEstate> findRealEstateIdLongLang(){
		return realEstateRepo.findRealEstateWithLongAndLng();
	}
	
	public List<RealEstate> findCoincidences(RegisterSearchDTO dto, District district, List<Integer>servicesIds,
			List<Integer>typesInterestPlaces, List<Integer>features,TypeRealEstate type){
		List<RealEstate> realEstates = realEstateRepo.findCoincidences(Double.valueOf(dto.getMinBudgetSearch()),
				Double.valueOf(dto.getMaxBudgetSearch()),
				dto.getAntiquityRealStateSearch(),
				dto.getNumBedrooms(),
				dto.getNumBathrooms(),
				district,
				type);
		
		List<RealEstate> selectedRealEstates = new ArrayList<RealEstate>();
		
		for(RealEstate re : realEstates) {
			if(servicesIds != null) {
				boolean condicionService = re.getListServices().stream()
					.map(com.middevs.wasi.table.Service::getIdService)
					.anyMatch(servicesIds.stream().collect(Collectors.toSet())::contains);
				if(condicionService == false) {
					continue;
				}
			}
			
			if(typesInterestPlaces != null) {
				boolean condicionTypeInterestPlace = re.getListEstateInteredDetail().stream()
					.map(TypeInterestPlace::getIdTypeInterestPlace)
					.anyMatch(typesInterestPlaces.stream().collect(Collectors.toSet())::contains);
				
				if(condicionTypeInterestPlace == false) {
					continue;
				}
			}
			
			if(features != null) {
				boolean condicionRealEstateFeature = re.getListFeatures().stream()
					.map(RealEstateFeature::getFeature)
					.map(Feature::getIdFeature)
					.anyMatch(features.stream().collect(Collectors.toSet())::contains);
				if(condicionRealEstateFeature == false) {
					continue;
				}
			}
			
			selectedRealEstates.add(re);
		}
		
		return selectedRealEstates;
	}
}
