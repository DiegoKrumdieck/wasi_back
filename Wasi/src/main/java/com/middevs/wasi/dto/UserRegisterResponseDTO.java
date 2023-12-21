package com.middevs.wasi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterResponseDTO {
    private Integer idUser;
    private String nameUser;
    private String emailUser;
    private Integer cellphoneUser;
    private Integer dniUser;
}
