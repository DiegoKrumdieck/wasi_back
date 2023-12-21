package com.middevs.wasi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import com.middevs.wasi.usecase.InvitedRegisterContactUseCase;
import com.middevs.wasi.usecase.RegisterContactUseCase;


@RestController
@RequestMapping("/contact")
public class ContactVendorBuyerController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ContactVenderBuyerService contactVendorBuyerService;
	
	@Autowired
	RealEstateService realEstateService;
	
	@Autowired
	MailService mailService; 
	
	@Value("${correo.remitente}")
	private String remitente; 
	
	@Value("${correo.asunto}")
	private String asunto; 
	
	@Value("${correo.contenido}")
	private String contenido; 
	
	@PostMapping("/register")
	public ResponseEntity<ResponseDTO>registrarConsultaPersonalizadaPorEspecialidad(@RequestBody EmailContactDTO dto){
		RegisterContactUseCase useCase = new RegisterContactUseCase(userService, contactVendorBuyerService, realEstateService, mailService); 
		return new ResponseEntity<ResponseDTO>(useCase.ejecutar(dto,remitente,asunto,contenido),HttpStatus.OK);
	}
	
	@PostMapping("/invited-register")
	public ResponseEntity<ResponseDTO>registrarConsultaPersonalizadaPorEspecialidadInvitado(@RequestBody InvitedEmailContactDTO dto){
		InvitedRegisterContactUseCase useCase = new InvitedRegisterContactUseCase(userService, contactVendorBuyerService, realEstateService, mailService); 
		return new ResponseEntity<ResponseDTO>(useCase.ejecutar(dto,remitente,asunto,contenido),HttpStatus.OK);
	}
	
}
