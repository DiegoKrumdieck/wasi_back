package com.middevs.wasi.usecase;

import java.util.ArrayList;
import java.util.List;

import com.middevs.wasi.client.AzureMLClient;
import com.middevs.wasi.dto.IBMWatsonDTO;
import com.middevs.wasi.dto.InputDTOAzure;
import com.middevs.wasi.dto.RegisterSearchDTO;
import com.middevs.wasi.dto.ResponseDTO;
import com.middevs.wasi.service.DistrictService;
import com.middevs.wasi.service.RealEstateService;
import com.middevs.wasi.service.TypeRealEstateService;
import com.middevs.wasi.table.District;
import com.middevs.wasi.table.RealEstate;
import com.middevs.wasi.table.TypeRealEstate;
import com.middevs.wasi.util.ConverterUtils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterSearchWithMLUseCase {
	
	AzureMLClient azureClient; 
	DistrictService districtService; 
	TypeRealEstateService typeRealEstateService;
	RegisterSearchWithoutMLUseCase useCase; 
	
	public ResponseDTO ejecutar(IBMWatsonDTO dto) {
		Integer codigoLugarInteres;
		InputDTOAzure inputAzure;
		try {
			inputAzure = ConverterUtils.convertIBMWatsonDataToAzure(dto); 
			codigoLugarInteres = azureClient.obtenerRespuesta(inputAzure);
		} catch (Exception e) {
			return new ResponseDTO("error","ocurrio un error al llamar al modelo de ML");
		}
		List<Integer> typesInterestPlaces = new ArrayList<Integer>();
		typesInterestPlaces.add(codigoLugarInteres);
		TypeRealEstate typeRealEstate = typeRealEstateService.findByName(dto.getTypeRealestate()); 
		District district = districtService.findByName(dto.getDistrict());
		RegisterSearchDTO registerSearchDTO = new RegisterSearchDTO(dto.getIdUser(), 0, 
				dto.getBudget(), null, dto.getNumChilds(), null, district.getIdDistrict(),typeRealEstate.getIdTypeRealEstate(),
				typesInterestPlaces, null, null);
		return useCase.ejecutar(registerSearchDTO);
	}
}
