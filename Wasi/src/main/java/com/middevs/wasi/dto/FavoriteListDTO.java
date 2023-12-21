package com.middevs.wasi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FavoriteListDTO {
    private int idRealEstate;
    private double priceRealEstate;
    private int numBedrooms;
    private String addressRealEstate;
    private List<FavoriteListImageDTO> images;
    private List<FavoriteListInteresPlaceDTO> interestPlaces;

    public FavoriteListDTO(int idRealEstate,double priceRealEstate,int numBedrooms,String addressRealEstate){
        this.idRealEstate = idRealEstate;
        this.priceRealEstate = priceRealEstate;
        this.numBedrooms = numBedrooms;
        this.addressRealEstate = addressRealEstate;
    }
}
