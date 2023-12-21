package com.middevs.wasi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InvitedEmailContactDTO {
	private int idRealEstate;  
	private String name;   
	private String email;
	private Integer cellphoneUser;  
	private String message; 
}
