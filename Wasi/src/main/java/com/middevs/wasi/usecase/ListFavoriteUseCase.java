package com.middevs.wasi.usecase;

import com.middevs.wasi.dto.FavoriteListDTO;
import com.middevs.wasi.dto.FavoriteListImageDTO;
import com.middevs.wasi.dto.FavoriteListInteresPlaceDTO;
import com.middevs.wasi.dto.ResponseDTO;
import com.middevs.wasi.repository.RealEstateImageJpaRepository;
import com.middevs.wasi.repository.RealEstateJpaRepository;
import com.middevs.wasi.service.UserService;
import com.middevs.wasi.table.RealEstate;
import com.middevs.wasi.table.RealEstateImage;
import com.middevs.wasi.table.TypeInterestPlace;
import com.middevs.wasi.table.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
public class ListFavoriteUseCase {

    RealEstateJpaRepository realEstateRepo;
    UserService userService;

    RealEstateImageJpaRepository realEstateImageJpaRepository;

    public ResponseDTO ejecutar(int idUser){
        User user = userService.findByIdUser(idUser);
        if (user == null) {
            return new ResponseDTO("error", "El usuario no existe");
        }else{
            Set<RealEstate> realEstateFavorites = user.getUserFavs();
            ArrayList<FavoriteListDTO> responseList = new ArrayList<>();
            for(RealEstate favorite : realEstateFavorites){
                /*Response element is created with its constructor*/
                FavoriteListDTO responseElement = new FavoriteListDTO(favorite.getIdRealEstate(),
                        favorite.getPriceRealEstate(), favorite.getNumBedrooms(), favorite.getAddressRealEstate());
                /*Real estate favorite images are added to the response element*/
                List<RealEstateImage> imagesOfRealEstate = realEstateImageJpaRepository.findByIdRealEstate(favorite.getIdRealEstate());
                ArrayList<FavoriteListImageDTO> responseImagesList = new ArrayList<>();
                for(RealEstateImage image: imagesOfRealEstate){
                    FavoriteListImageDTO responseImage = new FavoriteListImageDTO();
                    responseImage.setImageUrl(image.getImageURL());
                    responseImage.setIdOrder(image.getIdOrder());
                    responseImagesList.add(responseImage);
                }
                responseElement.setImages(responseImagesList);
                /*Real estate interest places are added to the response element */
                Set<TypeInterestPlace> interestPlacesOfRealEstate = favorite.getListEstateInteredDetail();
                ArrayList<FavoriteListInteresPlaceDTO> responseInterestPlacesList = new ArrayList<>();
                for(TypeInterestPlace interestPlace: interestPlacesOfRealEstate){
                    FavoriteListInteresPlaceDTO responseInterestPlace = new FavoriteListInteresPlaceDTO();
                    responseInterestPlace.setIdTypeInterestPlace(interestPlace.getIdTypeInterestPlace());
                    responseInterestPlace.setNameTypeInterestPlace(interestPlace.getNameTypeInterestPlace());
                    responseInterestPlacesList.add(responseInterestPlace);
                }
                responseElement.setInterestPlaces(responseInterestPlacesList);
                /*Response element added to the real estate response list*/
                responseList.add(responseElement);
            }
            return new ResponseDTO("success",null,responseList);
        }

    }

}
