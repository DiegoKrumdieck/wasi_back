package com.middevs.wasi.usecase;

import com.middevs.wasi.util.Utils;

import lombok.AllArgsConstructor;

import com.middevs.wasi.dto.EmailContactDTO;
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
public class RegisterContactUseCase {
	UserService userService;
	ContactVenderBuyerService contactVendorBuyerService;
	RealEstateService realEstateService;
	MailService mailService; 
	
	
	public ResponseDTO ejecutar(EmailContactDTO dto, String remitente, String asunto, String contenido) {
		RealEstate realEstate = realEstateService.findById(dto.getIdRealEstate()); 
		User user = userService.findByIdUser(dto.getIdBuyer()); 
		if(realEstate == null || user == null) {
			return new ResponseDTO("error", "El usuario o el realEstate no existen.");
		}
		contactVendorBuyerService.registerContact(user, realEstate, Utils.obtenerFechaHoraActual(), dto.getMessage());
		mailService.sendMail(remitente, realEstate.getIdUserVendor().getEmailUser(),
				asunto.replace("{direccion-inmueble}", realEstate.getAddressRealEstate())
						.replace("{nombre-interesado}",user.getNameUser()), 
				contenido.replace("{direccion-inmueble}", realEstate.getAddressRealEstate())
						.replace("{dni}", String.valueOf(user.getDniUser()))
						.replace("{nombre}", user.getNameUser())
						.replace("{correo}", user.getEmailUser())
						.replace("{celular}", String.valueOf(user.getCellphoneUser()))
						.replace("{mensaje}", dto.getMessage()));
		return new ResponseDTO(true); 
	}
}
