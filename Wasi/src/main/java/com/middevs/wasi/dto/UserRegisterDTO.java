package com.middevs.wasi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterDTO {
    private String nameUser;
    private String emailUser;
    private String passwordUser;
    private Integer cellphoneUser;
    private Integer dniUser;
}
