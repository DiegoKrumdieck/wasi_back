package com.middevs.wasi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ExecutePastSearchOptionsDTO {
    private Integer minBudgetSearch;
    private Integer maxBudgetSearch;
    private Integer antiquityRealStateSearch;
    private Integer numBedrooms;
    private Integer numBathrooms;
    private List<ExecutePastSearchDistrictDTO> districts;
    private List<ExecutePastSearchTypeRealEstateDTO> typesRealEstate;
    private List<ExecutePastSearchServiceDTO> services;
    private List<ExecutePastSearchFeatureDTO> features;
    private List<ExecutePastSearchTypeInterestPlaceDTO> typesInterestPlaces;
    private List<Integer> idDistricts;
    private List<Integer> idTypesRealEstate;
    private List<Integer> idServices;
    private List<Integer> idFeatures;
    private List<Integer> idTypesInterestPlaces;
}
