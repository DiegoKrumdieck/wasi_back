package com.middevs.wasi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExecutePastSearchRealEstatesDTO {
    private Integer idRealEstate;
    private Double priceRealEstate;
    private Integer numBedrooms;
    private String addressRealEstate;
    private Integer coincidence;
    private boolean favorite;
    private List<FavoriteListImageDTO> images;
    private List<FavoriteListInteresPlaceDTO> interestPlaces;

    public ExecutePastSearchRealEstatesDTO(int idRealEstate, double priceRealEstate, int numBedrooms, String addressRealEstate){
        this.idRealEstate = idRealEstate;
        this.priceRealEstate = priceRealEstate;
        this.numBedrooms = numBedrooms;
        this.addressRealEstate = addressRealEstate;
    }
}
