package com.middevs.wasi.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterSearchDTO {
	Integer idUser; 
	Integer minBudgetSearch; 
	Integer maxBudgetSearch; 
	Integer antiquityRealStateSearch; 
	Integer numBedrooms; 
	Integer numBathrooms; 
	Integer idDistrict; 
	Integer idTypeRealEstate; 
	List<Integer> typesInterestPlaces; 
	List<Integer> services; 
	List<Integer> features; 
}
