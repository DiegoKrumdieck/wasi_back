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
public class ResponseBusquedaPasadaDTO {

    private Integer idSearch;

    private DistrictSearchDTO districtSearch;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMMM yyyy", timezone="America/Lima")
    private String dateSearch;
}
