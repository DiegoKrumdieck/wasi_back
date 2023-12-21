package com.middevs.wasi.usecase;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.middevs.wasi.dto.DistrictSearchDTO;
import com.middevs.wasi.dto.ResponseBusquedaPasadaDTO;
import com.middevs.wasi.dto.ResponseDTO;
import com.middevs.wasi.repository.DistrictJpaRepository;
import com.middevs.wasi.repository.SearchJpaRepository;
import com.middevs.wasi.table.District;
import com.middevs.wasi.table.Search;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@AllArgsConstructor
@NoArgsConstructor
public class ListPastSearchUseCase {

    SearchJpaRepository searchJpaRepository;
    DistrictJpaRepository districtJpaRepository;

    public ResponseDTO ejecutar(int idUser) {
        List<Search> searchList = new ArrayList<>();
        searchList = searchJpaRepository.findByIdUser(idUser);
        if (searchList.isEmpty()){
            return new ResponseDTO("error", "Doesn't exist User");
        }else{
            List<ResponseBusquedaPasadaDTO> responseBusquedaPasadaDTOList = new ArrayList<ResponseBusquedaPasadaDTO>();
            for (Search searchBus:searchList){
                ResponseBusquedaPasadaDTO responseBusquedaPasadaDTO = new ResponseBusquedaPasadaDTO();
                responseBusquedaPasadaDTO.setIdSearch(searchBus.getIdSearch());
                LocalDateTime dateSearchConvert = searchBus.getDateSearch();
                String dateMonth = dateSearchConvert.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toString().toLowerCase();
                String dateDay = String.valueOf(dateSearchConvert.getDayOfMonth());
                String dateYear = String.valueOf(dateSearchConvert.getYear());
                String dateString = dateDay + " de " + dateMonth + " " + dateYear;
                responseBusquedaPasadaDTO.setDateSearch(dateString);
                District district = new District();
                district = districtJpaRepository.findByIdDistrict(searchBus.getIdDistrict().getIdDistrict());
                DistrictSearchDTO districtSearchDTO = new DistrictSearchDTO();
                districtSearchDTO.setIdDistrict(district.getIdDistrict());
                districtSearchDTO.setNameDistrict(district.getNameDistrict());
                responseBusquedaPasadaDTO.setDistrictSearch(districtSearchDTO);
                responseBusquedaPasadaDTOList.add(responseBusquedaPasadaDTO);
            }
            return new ResponseDTO("success",null, responseBusquedaPasadaDTOList);
        }
    }
}
