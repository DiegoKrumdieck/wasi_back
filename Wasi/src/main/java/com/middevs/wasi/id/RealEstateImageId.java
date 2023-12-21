package com.middevs.wasi.id;

import com.middevs.wasi.table.RealEstate;
import com.middevs.wasi.table.RealEstateImage;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public class RealEstateImageId implements Serializable {
    private Integer idOrder;
    private RealEstate idRealEstate;

    public RealEstateImageId(){
    }
}
