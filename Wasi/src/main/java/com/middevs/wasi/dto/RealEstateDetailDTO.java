package com.middevs.wasi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RealEstateDetailDTO {
    private Integer idRealEstate;

    private Double priceRealEstate;

    private Double sizeRealEstate;

    private Integer numBedrooms;

    private String descriptionRealEstate;

    private Integer numBathrooms;

    private Integer antiquityRealEstate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone="America/Lima")
    private LocalDateTime publishDate;

    private Integer numFloors;

    private String latitudeRealEstate;

    private String longitudeRealEstate;

    private String addressRealEstate;

    List<ImagesRealEstateDTO> listImagesRealEstateDTO;
    List<ServiceRealEstateDTO> listServiceRealEstateDTO;
    List<FeatureRealEstateDTO> listFeatureRealEstateDTO;
    List<TypeInterestRealEstateDTO> listTypeInterestRealEstateDTO;
}
