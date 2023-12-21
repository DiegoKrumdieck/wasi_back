package com.middevs.wasi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExecutePastSearchDistrictDTO {
    private Integer idDistrict;
    private String nameDistrict;
    private boolean selected;
}
