package com.middevs.wasi.usecase;


import java.io.IOException;
import java.util.List;

import com.middevs.wasi.dto.ExportCSVDTO;
import com.middevs.wasi.dto.RealEstateTOCSVDTO;
import com.middevs.wasi.dto.ResponseDTO;
import com.middevs.wasi.service.RealEstateService;
import com.middevs.wasi.table.RealEstate;
import com.middevs.wasi.util.CSVUtils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExportRealEstateDataToCSVUseCase {
	
	RealEstateService estateService;
	
	public ResponseDTO ejecutar(String path) {
		List<RealEstate>realEstates = estateService.findRealEstateIdLongLang();
		try {
			CSVUtils.writeRealEstateDataToCSV(realEstates, path);
			return new ResponseDTO(true);
		} catch (IOException e) {
			return new ResponseDTO("error", "ocurrio un error al exportar a csv");
		}
	}
}
