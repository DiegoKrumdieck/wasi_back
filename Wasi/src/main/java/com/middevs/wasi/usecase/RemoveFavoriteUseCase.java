package com.middevs.wasi.usecase;

import com.middevs.wasi.dto.FavoriteRegisterDTO;
import com.middevs.wasi.dto.ResponseDTO;
import com.middevs.wasi.repository.RealEstateJpaRepository;
import com.middevs.wasi.service.UserService;
import com.middevs.wasi.table.RealEstate;
import com.middevs.wasi.table.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class RemoveFavoriteUseCase {

    RealEstateJpaRepository realEstateRepo;
    UserService userService;

    public ResponseDTO ejecutar(FavoriteRegisterDTO favoriteRegisterDTO){
        User user = userService.findByIdUser(favoriteRegisterDTO.getIdUser());
        if (user == null) {
            return new ResponseDTO("error", "El usuario no existe");
        }else{
            RealEstate realEstate = realEstateRepo.findById(favoriteRegisterDTO.getIdRealEstate()).orElse(null);
            realEstate.getListUserFavs().remove(user);
            realEstateRepo.save(realEstate);
            return new ResponseDTO("success",null,true);
        }

    }
}
