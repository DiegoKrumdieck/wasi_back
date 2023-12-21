package com.middevs.wasi.controller;

import com.middevs.wasi.client.AzureMLClient;
import com.middevs.wasi.dto.IBMWatsonDTO;
import com.middevs.wasi.dto.RegisterSearchDTO;
import com.middevs.wasi.dto.ResponseDTO;
import com.middevs.wasi.repository.*;
import com.middevs.wasi.service.DistrictService;
import com.middevs.wasi.service.FeatureService;
import com.middevs.wasi.service.RealEstateService;
import com.middevs.wasi.service.SearchService;
import com.middevs.wasi.usecase.DeleteSearchUseCase;
import com.middevs.wasi.service.ServiceService;
import com.middevs.wasi.service.TypeInterestPlaceService;
import com.middevs.wasi.service.TypeRealEstateService;
import com.middevs.wasi.service.UserService;
import com.middevs.wasi.usecase.ExecutePastSearchUseCase;
import com.middevs.wasi.usecase.RegisterSearchWithMLUseCase;
import com.middevs.wasi.usecase.RegisterSearchWithoutMLUseCase;


import com.middevs.wasi.usecase.ListPastSearchUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    SearchService searchService;
    
    @Autowired
    DistrictJpaRepository districtJpaRepository;
    
    @Autowired
    TypeRealEstateJPARepository typeRealEstateJPARepository;
    
    @Autowired
    ServiceJpaRepository serviceJpaRepository;
    
    @Autowired
    FeatureJpaRepository featureJpaRepository;
    
    @Autowired
    TypeInterestPlaceJpaRepository typeInterestPlaceJpaRepository;
    
    @Autowired
    SearchDetailJpaRepository searchDetailJpaRepository;
    
    @Autowired
    RealEstateImageJpaRepository realEstateImageJpaRepository;
    @Autowired
    RealEstateService realEstateService; 
    
    @Autowired
    DistrictService districtService; 
    
    @Autowired
    TypeRealEstateService typeRealEstateService; 
    
    @Autowired
    UserService userService; 
    
    @Autowired
    TypeInterestPlaceService typeInterestPlaceService;
    
    @Autowired
    FeatureService featureService;
    
    @Autowired
    ServiceService serviceService; 
    
    @Autowired
    SearchJpaRepository searchJpapaRepository;

    @Value("${azure-ml.url}")
    private String url; 
    
    @Value("${azure-ml.api-key}")
    private String apiKey; 

    @GetMapping("/{id-search}")
    public ResponseEntity<ResponseDTO> listFavoriteRealEstate(@PathVariable(value = "id-search") int idSearch){
        ExecutePastSearchUseCase executePastSearchUseCase = new ExecutePastSearchUseCase(searchService,
                districtJpaRepository,
                typeRealEstateJPARepository,
                serviceJpaRepository,
                featureJpaRepository,
                typeInterestPlaceJpaRepository,
                searchDetailJpaRepository,
                realEstateImageJpaRepository);
        return new ResponseEntity<ResponseDTO>(executePastSearchUseCase.ejecutar(idSearch), HttpStatus.OK);
    }
    
    @PostMapping("/register/advanced")
    public ResponseEntity<ResponseDTO> registrar(@RequestBody RegisterSearchDTO dto){
    	RegisterSearchWithoutMLUseCase useCase = new RegisterSearchWithoutMLUseCase(realEstateService, districtService, 
    			typeRealEstateService, userService, typeInterestPlaceService, featureService, serviceService, searchService); 
    	return new ResponseEntity<ResponseDTO>(useCase.ejecutar(dto), HttpStatus.OK);

    }

    @PostMapping("/register/ml")
    public ResponseEntity<ResponseDTO> registrarUsandoML(@RequestBody IBMWatsonDTO dto){
    	AzureMLClient azureClient = new AzureMLClient(url,apiKey);
    	RegisterSearchWithoutMLUseCase useCase1 = new RegisterSearchWithoutMLUseCase(realEstateService, districtService, 
    			typeRealEstateService, userService, typeInterestPlaceService, featureService, serviceService, searchService); 
    	RegisterSearchWithMLUseCase useCase2 = new RegisterSearchWithMLUseCase(azureClient, districtService,
    			typeRealEstateService, useCase1); 
    	return new ResponseEntity<ResponseDTO>(useCase2.ejecutar(dto), HttpStatus.OK);

    }

    
    @GetMapping("/listByUser/{id-user}")
    public ResponseEntity<ResponseDTO> listPastSearch(@PathVariable(value = "id-user") int idUser){
        ListPastSearchUseCase listPastSearchUseCase = new ListPastSearchUseCase(searchJpapaRepository,districtJpaRepository);
        return new ResponseEntity<ResponseDTO>(listPastSearchUseCase.ejecutar(idUser), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id-search}")
    public ResponseEntity<ResponseDTO> deleteSearch(@PathVariable(value = "id-search") int idSearch){
        DeleteSearchUseCase deleteSearchUseCase = new DeleteSearchUseCase(searchJpapaRepository,searchDetailJpaRepository);
        return new ResponseEntity<ResponseDTO>(deleteSearchUseCase.ejecutar(idSearch), HttpStatus.OK);
    }
}
