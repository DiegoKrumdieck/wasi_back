package com.middevs.wasi.usecase;

import com.middevs.wasi.dto.*;
import com.middevs.wasi.repository.*;
import com.middevs.wasi.service.SearchService;
import com.middevs.wasi.table.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
public class ExecutePastSearchUseCase {

    SearchService searchService;
    DistrictJpaRepository districtJpaRepository;
    TypeRealEstateJPARepository typeRealEstateJPARepository;
    ServiceJpaRepository serviceJpaRepository;
    FeatureJpaRepository featureJpaRepository;
    TypeInterestPlaceJpaRepository typeInterestPlaceJpaRepository;
    SearchDetailJpaRepository searchDetailJpaRepository;
    RealEstateImageJpaRepository realEstateImageJpaRepository;

    public ResponseDTO ejecutar(int idSearch) {
        Search search = searchService.findByIdSearch(idSearch);
        if (search == null) {
            return new ResponseDTO("error", "El search con el codigo ingresado no existe");
        }else{
            User user = search.getUser(); //It can be null
            Set<RealEstate> realEstateFavorites = null;
            if(user != null) {
                realEstateFavorites = user.getUserFavs();
            }
            /*OptionsDTO is created with values*/
            ExecutePastSearchOptionsDTO executePastSearchOptionsDTO = new ExecutePastSearchOptionsDTO();
            executePastSearchOptionsDTO.setMinBudgetSearch(search.getMinBudgetSearch());
            executePastSearchOptionsDTO.setMaxBudgetSearch(search.getMaxBudgetSearch());
            executePastSearchOptionsDTO.setAntiquityRealStateSearch(search.getAntiquityRealStateSearch());
            executePastSearchOptionsDTO.setNumBedrooms(search.getNumBedrooms());
            executePastSearchOptionsDTO.setNumBathrooms(search.getNumBathrooms());
            /*Districts of OptionsDTO are added*/
            int idDistrictSelected = -1;
            if(search.getIdDistrict() != null) {
                idDistrictSelected = search.getIdDistrict().getIdDistrict();
            }
            List<District> listDistricts = districtJpaRepository.findAll();
            ArrayList<ExecutePastSearchDistrictDTO> responseDistrictList = new ArrayList<>();
            ArrayList<Integer> idDistricts = new ArrayList<>();
            for(District district: listDistricts){
                ExecutePastSearchDistrictDTO responseDistrict = new ExecutePastSearchDistrictDTO();
                responseDistrict.setIdDistrict(district.getIdDistrict());
                responseDistrict.setNameDistrict(district.getNameDistrict());
                if(idDistrictSelected == district.getIdDistrict()){
                    responseDistrict.setSelected(true);
                    idDistricts.add(idDistrictSelected);
                }
                else{
                    responseDistrict.setSelected(false);
                }
                responseDistrictList.add(responseDistrict);
            }
            /*Type Real Estate of OptionsDTO are added*/
            int idTypeRealEstateSelected = -1;
            if(search.getIdDistrict() != null) {
                if(search.getIdTypeRealEstate() != null){
                    idTypeRealEstateSelected = search.getIdTypeRealEstate().getIdTypeRealEstate();
                }
            }
            List<TypeRealEstate> listTypeRealEstate = typeRealEstateJPARepository.findAll();
            ArrayList<ExecutePastSearchTypeRealEstateDTO> responseTypeRealEstateList = new ArrayList<>();
            ArrayList<Integer> idTypeRealEstates = new ArrayList<>();
            for(TypeRealEstate typeRealEstate: listTypeRealEstate){
                ExecutePastSearchTypeRealEstateDTO responseTypeRealEstate = new ExecutePastSearchTypeRealEstateDTO();
                responseTypeRealEstate.setId(typeRealEstate.getIdTypeRealEstate());
                responseTypeRealEstate.setName(typeRealEstate.getNameTypeRealEstate());
                if(idTypeRealEstateSelected == typeRealEstate.getIdTypeRealEstate()){
                    responseTypeRealEstate.setSelected(true);
                    idTypeRealEstates.add(idTypeRealEstateSelected);
                }
                else{
                    responseTypeRealEstate.setSelected(false);
                }
                responseTypeRealEstateList.add(responseTypeRealEstate);
            }
            /*Services of OptionsDTO are added*/
            Set<Service> listServicesSelected = search.getListSearchServicesDetail();
            List<Service> listServices = serviceJpaRepository.findAll();
            ArrayList<ExecutePastSearchServiceDTO> responseServiceList = new ArrayList<>();
            ArrayList<Integer> idServices = new ArrayList<>();
            for(Service service: listServices){
                ExecutePastSearchServiceDTO responseService = new ExecutePastSearchServiceDTO();
                responseService.setId(service.getIdService());
                responseService.setName(service.getNameService());
                if(listServicesSelected.contains(service)){
                    responseService.setSelected(true);
                    idServices.add(service.getIdService());
                }
                else{
                    responseService.setSelected(false);
                }
                responseServiceList.add(responseService);
            }
            /*Features of OptionsDTO are added*/
            Set<Feature> listFeaturesSelected = search.getListFeaturesSearchDetail();
            List<Feature> listFeatures = featureJpaRepository.findAll();
            ArrayList<ExecutePastSearchFeatureDTO> responseFeatureList = new ArrayList<>();
            ArrayList<Integer> idFeatures = new ArrayList<>();
            for(Feature feature: listFeatures){
                ExecutePastSearchFeatureDTO responseFeature = new ExecutePastSearchFeatureDTO();
                responseFeature.setId(feature.getIdFeature());
                responseFeature.setName(feature.getNameFeature());
                if(listFeaturesSelected.contains(feature)){
                    responseFeature.setSelected(true);
                    idFeatures.add(feature.getIdFeature());
                }
                else{
                    responseFeature.setSelected(false);
                }
                responseFeatureList.add(responseFeature);
            }
            /*Interest Place of OptionsDTO are added*/
            Set<TypeInterestPlace> listTypeInterestPlaceSelected = search.getListInterestSearch();
            List<TypeInterestPlace> listTypeInterestPlaces = typeInterestPlaceJpaRepository.findAll();
            ArrayList<ExecutePastSearchTypeInterestPlaceDTO> responseTypeInterestPlaceList = new ArrayList<>();
            ArrayList<Integer> idTypeInterestPlaces = new ArrayList<>();
            for(TypeInterestPlace typeInterestPlace: listTypeInterestPlaces){
                ExecutePastSearchTypeInterestPlaceDTO responseTypeInterestPlace = new ExecutePastSearchTypeInterestPlaceDTO();
                responseTypeInterestPlace.setId(typeInterestPlace.getIdTypeInterestPlace());
                responseTypeInterestPlace.setName(typeInterestPlace.getNameTypeInterestPlace());
                if(listTypeInterestPlaceSelected.contains(typeInterestPlace)){
                    responseTypeInterestPlace.setSelected(true);
                    idTypeInterestPlaces.add(typeInterestPlace.getIdTypeInterestPlace());
                }
                else{
                    responseTypeInterestPlace.setSelected(false);
                }
                responseTypeInterestPlaceList.add(responseTypeInterestPlace);
            }
            /*OptionsDTO object final creation*/
            executePastSearchOptionsDTO.setDistricts(responseDistrictList);
            executePastSearchOptionsDTO.setTypesRealEstate(responseTypeRealEstateList);
            executePastSearchOptionsDTO.setServices(responseServiceList);
            executePastSearchOptionsDTO.setFeatures(responseFeatureList);
            executePastSearchOptionsDTO.setTypesInterestPlaces(responseTypeInterestPlaceList);
            executePastSearchOptionsDTO.setIdDistricts(idDistricts);
            executePastSearchOptionsDTO.setIdTypesInterestPlaces(idTypeInterestPlaces);
            executePastSearchOptionsDTO.setIdFeatures(idFeatures);
            executePastSearchOptionsDTO.setIdServices(idServices);
            executePastSearchOptionsDTO.setIdTypesRealEstate(idTypeRealEstates);
            /*ResultDTO object created for real estate asign*/
            List<RealEstate> listRealEstate = searchDetailJpaRepository.findRealStateByIdSearch(search.getIdSearch());
            ArrayList<ExecutePastSearchRealEstatesDTO> executePastSearchRealEstatesDTOS = new ArrayList<>();
            for(RealEstate realEstate : listRealEstate) {
                /*Response element is created with its constructor */
                ExecutePastSearchRealEstatesDTO responseElement = new ExecutePastSearchRealEstatesDTO(realEstate.getIdRealEstate(),
                        realEstate.getPriceRealEstate(), realEstate.getNumBedrooms(), realEstate.getAddressRealEstate());
                SearchDetail searchDetail = searchDetailJpaRepository
                        .findByIdSearchAndIdRealEstate(search.getIdSearch(), realEstate.getIdRealEstate());
                responseElement.setCoincidence(searchDetail.getCoincidenceRealState());
                /*True if real estate is in user favorites*/
                if(realEstateFavorites != null && realEstateFavorites.contains(realEstate)){
                    responseElement.setFavorite(true);
                }
                else{
                    responseElement.setFavorite(false);
                }
                /*Real estate images are added to the response element*/
                List<RealEstateImage> imagesOfRealEstate = realEstateImageJpaRepository
                        .findByIdRealEstate(realEstate.getIdRealEstate());
                ArrayList<FavoriteListImageDTO> responseImagesList = new ArrayList<>();
                for (RealEstateImage image : imagesOfRealEstate) {
                    FavoriteListImageDTO responseImage = new FavoriteListImageDTO();
                    responseImage.setImageUrl(image.getImageURL());
                    responseImage.setIdOrder(image.getIdOrder());
                    responseImagesList.add(responseImage);
                }
                responseElement.setImages(responseImagesList);
                /*Real estate interest places are added to the response element */
                Set<TypeInterestPlace> interestPlacesOfRealEstate = realEstate.getListEstateInteredDetail();
                ArrayList<FavoriteListInteresPlaceDTO> responseInterestPlacesList = new ArrayList<>();
                for (TypeInterestPlace interestPlace : interestPlacesOfRealEstate) {
                    FavoriteListInteresPlaceDTO responseInterestPlace = new FavoriteListInteresPlaceDTO();
                    responseInterestPlace.setIdTypeInterestPlace(interestPlace.getIdTypeInterestPlace());
                    responseInterestPlace.setNameTypeInterestPlace(interestPlace.getNameTypeInterestPlace());
                    responseInterestPlacesList.add(responseInterestPlace);
                }
                responseElement.setInterestPlaces(responseInterestPlacesList);
                /*Response element added to the real estate response list*/
                executePastSearchRealEstatesDTOS.add(responseElement);
            }
            Collections.sort(executePastSearchRealEstatesDTOS, (o1, o2) ->  o2.getCoincidence() - o1.getCoincidence());
            /*Execute past search response object creation*/
            ExecutePastSearchDTO executePastSearchDTO = new ExecutePastSearchDTO();
            executePastSearchDTO.setOptions(executePastSearchOptionsDTO);
            executePastSearchDTO.setResults(executePastSearchRealEstatesDTOS);
            return new ResponseDTO("success",null,executePastSearchDTO);
        }
    }
}
