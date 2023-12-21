package com.middevs.wasi.usecase;

import com.middevs.wasi.util.Utils;

import lombok.AllArgsConstructor;

import com.middevs.wasi.dto.EmailContactDTO;
import com.middevs.wasi.dto.InvitedEmailContactDTO;
import com.middevs.wasi.dto.ResponseDTO;
import com.middevs.wasi.repository.ContactVendorBuyerJpaRepository;
import com.middevs.wasi.repository.RealEstateJpaRepository;
import com.middevs.wasi.repository.UserJpaRepository;
import com.middevs.wasi.service.ContactVenderBuyerService;
import com.middevs.wasi.service.MailService;
import com.middevs.wasi.service.RealEstateService;
import com.middevs.wasi.service.UserService;
import com.middevs.wasi.table.ContactVendorBuyer;
import com.middevs.wasi.table.RealEstate;
import com.middevs.wasi.table.User;


@AllArgsConstructor
public class InvitedRegisterContactUseCase {
	UserService userService;
	ContactVenderBuyerService contactVendorBuyerService;
	RealEstateService realEstateService;
	MailService mailService; 
	
	
	public ResponseDTO ejecutar(InvitedEmailContactDTO dto, String remitente, String asunto, String contenido) {
		RealEstate realEstate = realEstateService.findById(dto.getIdRealEstate()); 
		if(realEstate == null) {
			return new ResponseDTO("error", "El realEstate no existe.");
		}
		contactVendorBuyerService.registerContact(null, realEstate, Utils.obtenerFechaHoraActual(), dto.getMessage());
		mailService.sendMail(remitente, realEstate.getIdUserVendor().getEmailUser(),
				asunto.replace("{direccion-inmueble}", realEstate.getAddressRealEstate())
						.replace("{nombre-interesado}",dto.getName()), 
				contenido.replace("{direccion-inmueble}", realEstate.getAddressRealEstate())
						.replace("{dni}", "-")
						.replace("{nombre}", dto.getName())
						.replace("{correo}", dto.getEmail())
						.replace("{celular}", String.valueOf(dto.getCellphoneUser()))
						.replace("{mensaje}", dto.getMessage()));
		return new ResponseDTO(true); 
	}
}
