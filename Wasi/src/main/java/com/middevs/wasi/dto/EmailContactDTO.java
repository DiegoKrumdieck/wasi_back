package com.middevs.wasi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmailContactDTO {
	private String message; 
	private int idRealEstate; 
	private int idBuyer; 
}
