package com.middevs.wasi.usecase;


import com.middevs.wasi.dto.*;
import com.middevs.wasi.repository.RealEstateFeatureJPARepository;
import com.middevs.wasi.repository.RealEstateImageJpaRepository;
import com.middevs.wasi.repository.RealEstateJpaRepository;
import com.middevs.wasi.repository.RealEstateServiceRepository;
import com.middevs.wasi.service.RealEstateService;
import com.middevs.wasi.table.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
public class ViewDetailRealEstateUseCase {

    RealEstateService realEstateService;
    RealEstateImageJpaRepository realEstateImageJpaRepository;
    RealEstateServiceRepository realEstateServiceRepository;
    RealEstateFeatureJPARepository realEstateFeatureJPARepository;

    public ResponseDTO ejecutar(int idRealEstate){
        RealEstate realEstate = realEstateService.findById(idRealEstate);
        if (realEstate==null){
            return new ResponseDTO("error", "Doesn't exist real estate");
        }else{
            RealEstateDetailDTO realEstateDetailDTO = new RealEstateDetailDTO();
            realEstateDetailDTO.setIdRealEstate(realEstate.getIdRealEstate());
            realEstateDetailDTO.setAddressRealEstate(realEstate.getAddressRealEstate());
            realEstateDetailDTO.setAntiquityRealEstate(realEstate.getAntiquityRealEstate());
            realEstateDetailDTO.setLatitudeRealEstate(realEstate.getLatitudeRealEstate());
            realEstateDetailDTO.setSizeRealEstate(realEstate.getSizeRealEstate());
            realEstateDetailDTO.setPriceRealEstate(realEstate.getPriceRealEstate());
            realEstateDetailDTO.setDescriptionRealEstate(realEstate.getDescriptionRealEstate());
            realEstateDetailDTO.setNumFloors(realEstate.getNumFloors());
            realEstateDetailDTO.setNumBedrooms(realEstate.getNumBedrooms());
            realEstateDetailDTO.setNumBathrooms(realEstate.getNumBathrooms());
            realEstateDetailDTO.setPublishDate(realEstate.getPublishDate());
            realEstateDetailDTO.setLongitudeRealEstate(realEstate.getLongitudeRealEstate());

            // Se llenga los servicios
            Set<Service> servicios = realEstate.getListServices();
            ArrayList<ServiceRealEstateDTO> serviceRealEstateDTOS = new ArrayList<>();
            for (Service servicio: servicios){
                ServiceRealEstateDTO serviceRealEstateDTO = new ServiceRealEstateDTO();
                serviceRealEstateDTO.setIdService(servicio.getIdService());
                serviceRealEstateDTO.setNameService(servicio.getNameService());
                serviceRealEstateDTOS.add(serviceRealEstateDTO);
            }
            realEstateDetailDTO.setListServiceRealEstateDTO(serviceRealEstateDTOS);

            //Se llena los typeInterest
            Set<TypeInterestPlace> listEstateInterest = realEstate.getListEstateInteredDetail();
            ArrayList<TypeInterestRealEstateDTO> typeInterestRealEstateDTOs = new ArrayList<>();
            for (TypeInterestPlace typeinterest: listEstateInterest){
                TypeInterestRealEstateDTO typeInterestRealEstateDTO = new TypeInterestRealEstateDTO();
                typeInterestRealEstateDTO.setIdTypeInterestPlace(typeinterest.getIdTypeInterestPlace());
                typeInterestRealEstateDTO.setNameTypeInterestPlace(typeinterest.getNameTypeInterestPlace());
                typeInterestRealEstateDTOs.add(typeInterestRealEstateDTO);
            }
            realEstateDetailDTO.setListTypeInterestRealEstateDTO(typeInterestRealEstateDTOs);


            List<RealEstateImage> imagesOfRealEstate = realEstateImageJpaRepository.findByIdRealEstate(idRealEstate);
            ArrayList<ImagesRealEstateDTO> responseImagesList = new ArrayList<>();
            for(RealEstateImage image: imagesOfRealEstate){
                ImagesRealEstateDTO responseImage = new ImagesRealEstateDTO();
                responseImage.setImageUrl(image.getImageURL());
                responseImage.setIdOrder(image.getIdOrder());
                responseImagesList.add(responseImage);
            }
            realEstateDetailDTO.setListImagesRealEstateDTO(responseImagesList);

            List<Feature> features = realEstateFeatureJPARepository.findByIdRealEstate(idRealEstate);
            ArrayList<FeatureRealEstateDTO> featureRealEstateDTOS = new ArrayList<>();
            for(Feature feature: features){
                FeatureRealEstateDTO responseFeature = new FeatureRealEstateDTO();
                responseFeature.setIdFeature(feature.getIdFeature());
                responseFeature.setNameFeature(feature.getNameFeature());
                featureRealEstateDTOS.add(responseFeature);
            }
            realEstateDetailDTO.setListFeatureRealEstateDTO(featureRealEstateDTOS);



            return new ResponseDTO("success",null, realEstateDetailDTO);
        }

    }
}
