package com.middevs.wasi.usecase;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import com.middevs.wasi.dto.RegisterSearchDTO;
import com.middevs.wasi.dto.ResponseDTO;
import com.middevs.wasi.repository.ServiceJpaRepository;
import com.middevs.wasi.service.DistrictService;
import com.middevs.wasi.service.FeatureService;
import com.middevs.wasi.service.RealEstateService;
import com.middevs.wasi.service.SearchService;
import com.middevs.wasi.service.ServiceService;
import com.middevs.wasi.service.TypeInterestPlaceService;
import com.middevs.wasi.service.TypeRealEstateService;
import com.middevs.wasi.service.UserService;
import com.middevs.wasi.table.District;
import com.middevs.wasi.table.Feature;
import com.middevs.wasi.table.RealEstate;
import com.middevs.wasi.table.Search;
import com.middevs.wasi.table.SearchDetail;
import com.middevs.wasi.table.Service;
import com.middevs.wasi.table.TypeInterestPlace;
import com.middevs.wasi.table.TypeRealEstate;
import com.middevs.wasi.table.User;
import com.middevs.wasi.util.Utils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterSearchWithoutMLUseCase {
	RealEstateService realEstateService; 
	DistrictService districtService;
	TypeRealEstateService typeRealEstateService; 
	UserService userService; 
	TypeInterestPlaceService typeInterestPlaceService;
	FeatureService featureService;
	ServiceService serviceService; 
	SearchService searchService;
	public ResponseDTO ejecutar(RegisterSearchDTO dto) {
		/*
		precio - obligatorio dentro del rango
		distrito - obligatorio mismo distrito
		tipo de inmueble - obligatorio mismo tipo
		numHabitaciones - variable hacia arriba (si es 4, de 4 hacia arriba)
		numBaños -  variable hacia arriba (si es 4, de 4 hacia arriba)
		antiguedad - variable +-5 
		lugares de interes - al menos uno de ellos
		servicios - al menos uno de ellos 
		features(extras) - al menos uno de ellos
		 */
		District district = (dto.getIdDistrict() == null)? null: districtService.findById(dto.getIdDistrict());
		User user = (dto.getIdUser() == null)? null : userService.getById(dto.getIdUser()); 
		TypeRealEstate typeRealEstate = (dto.getIdTypeRealEstate() == null)? null : typeRealEstateService.findById(dto.getIdTypeRealEstate());
		
		Set<TypeInterestPlace> typesInterestPlace = (dto.getTypesInterestPlaces() == null)? null : 
			new HashSet<> (typeInterestPlaceService.findByIds(dto.getTypesInterestPlaces()));
		
		Set<Feature> features = (dto.getFeatures() == null)? null : 
			new HashSet<> (featureService.findByIds(dto.getFeatures()));
		
		Set<Service> services = (dto.getServices() == null)? null : 
			new HashSet<> (serviceService.findByIds(dto.getServices()));
		
		List<RealEstate>realEstates = realEstateService.findCoincidences(dto, district, dto.getServices(),
				dto.getTypesInterestPlaces(),dto.getFeatures(), typeRealEstate);
		
		Search search = new Search(district, user, typeRealEstate, typesInterestPlace, features, services, Utils.obtenerFechaHoraActual(),
				dto.getMaxBudgetSearch(), dto.getMinBudgetSearch(), dto.getNumBedrooms(), dto.getNumBathrooms(), dto.getAntiquityRealStateSearch());
		
		Set<SearchDetail> searchDetails = new HashSet<>(Utils.calcularPorcentajeDeCoincidencia(realEstates, dto.getAntiquityRealStateSearch(), 
				dto.getNumBedrooms(), dto.getNumBathrooms(), typesInterestPlace, features, services, search));
		
		search.setSearchDetails(searchDetails);
		searchService.register(search);
		
		return new ResponseDTO(search.getIdSearch());
	}
}
