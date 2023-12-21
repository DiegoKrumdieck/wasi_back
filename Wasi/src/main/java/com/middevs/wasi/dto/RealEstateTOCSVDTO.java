package com.middevs.wasi.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RealEstateTOCSVDTO {
	private Integer idRealEstate;
	private String latitudeRealEstate;
	private String longitudeRealEstate;
	
	public RealEstateTOCSVDTO(Integer idRealEstate, String latitudeRealEstate, String longitudeRealEstate) {
		this.idRealEstate = idRealEstate;
		this.latitudeRealEstate = latitudeRealEstate;
		this.longitudeRealEstate = longitudeRealEstate;
	}
	
	
}
