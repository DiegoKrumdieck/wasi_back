package com.middevs.wasi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ExecutePastSearchDTO {
    private ExecutePastSearchOptionsDTO options;
    private List<ExecutePastSearchRealEstatesDTO> results;
}
