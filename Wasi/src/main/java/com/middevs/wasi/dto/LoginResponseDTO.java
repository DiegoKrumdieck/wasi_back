package com.middevs.wasi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    private Integer idUser;
    private String nameUser;
    private String emailUser;
    private Integer dniUser;
    private Integer cellphoneUser;
}
