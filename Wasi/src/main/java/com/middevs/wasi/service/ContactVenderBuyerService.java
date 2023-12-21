package com.middevs.wasi.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.middevs.wasi.repository.ContactVendorBuyerJpaRepository;
import com.middevs.wasi.table.ContactVendorBuyer;
import com.middevs.wasi.table.RealEstate;
import com.middevs.wasi.table.User;

@Service
public class ContactVenderBuyerService {
	@Autowired
	ContactVendorBuyerJpaRepository contactRepo;
	
	public ContactVendorBuyer registerContact(User user, RealEstate realEstate, LocalDateTime dateTime, String message) {
		return contactRepo.save(new ContactVendorBuyer(user, realEstate, dateTime, message)); 
	}
}
