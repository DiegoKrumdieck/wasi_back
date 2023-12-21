package com.middevs.wasi.controller;

import com.middevs.wasi.dto.ExportCSVDTO;
import com.middevs.wasi.dto.FavoriteRegisterDTO;
import com.middevs.wasi.dto.ResponseDTO;
import com.middevs.wasi.dto.UserRegisterDTO;
import com.middevs.wasi.repository.*;
import com.middevs.wasi.service.MailService;
import com.middevs.wasi.service.RealEstateService;
import com.middevs.wasi.service.UserService;
import com.middevs.wasi.usecase.*;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/realEstate")
public class RealEstateController {
    @Autowired
    RealEstateJpaRepository realEstateRepo;

    @Autowired
    UserService userService;

    @Autowired
    UserJpaRepository userJpaRepository;

    @Autowired
    RealEstateService realEstateService;

    @Autowired
    RealEstateImageJpaRepository realEstateImageJpaRepository;

    @Autowired
    RealEstateServiceRepository realEstateServiceRepository;

    @Autowired
    RealEstateFeatureJPARepository realEstateFeatureJPARepository;

    @PostMapping("/favs/register")
    public ResponseEntity<ResponseDTO> registerFavoriteRealEstate(@RequestBody FavoriteRegisterDTO favoriteRegisterDTO){
        RegisterFavoriteUseCase registerFavoriteUseCase = new RegisterFavoriteUseCase(realEstateRepo, userService);
        return new ResponseEntity<ResponseDTO>(registerFavoriteUseCase.ejecutar(favoriteRegisterDTO), HttpStatus.OK);
    }

    @PutMapping("/favs/remove")
    public ResponseEntity<ResponseDTO> removeFavoriteRealEstate(@RequestBody FavoriteRegisterDTO favoriteRegisterDTO){
        RemoveFavoriteUseCase removeFavoriteUseCase = new RemoveFavoriteUseCase(realEstateRepo, userService);
        return new ResponseEntity<ResponseDTO>(removeFavoriteUseCase.ejecutar(favoriteRegisterDTO), HttpStatus.OK);
    }

    @GetMapping("/favs/{id-user}")
    public ResponseEntity<ResponseDTO> listFavoriteRealEstate(@PathVariable(value = "id-user") int idUser){
        ListFavoriteUseCase listFavoriteUseCase = new ListFavoriteUseCase(realEstateRepo, userService,
                realEstateImageJpaRepository);
        return new ResponseEntity<ResponseDTO>(listFavoriteUseCase.ejecutar(idUser), HttpStatus.OK);
    }

    @GetMapping("/{id-real-estate}")
    public ResponseEntity<ResponseDTO> viewDetailOfRealEstate(@PathVariable(value = "id-real-estate") int idRealEstate){
        ViewDetailRealEstateUseCase viewDetailRealEstateUseCase = new ViewDetailRealEstateUseCase(realEstateService,realEstateImageJpaRepository,realEstateServiceRepository,realEstateFeatureJPARepository);
        return new ResponseEntity<ResponseDTO>(viewDetailRealEstateUseCase.ejecutar(idRealEstate), HttpStatus.OK);
    }

    @PostMapping("/export-csv")
    public ResponseEntity<ResponseDTO> exportCSV(@RequestBody ExportCSVDTO dto){
        ExportRealEstateDataToCSVUseCase useCase = new ExportRealEstateDataToCSVUseCase(realEstateService);
        return new ResponseEntity<ResponseDTO>(useCase.ejecutar(dto.getExportPath()), HttpStatus.OK);
    }
}
