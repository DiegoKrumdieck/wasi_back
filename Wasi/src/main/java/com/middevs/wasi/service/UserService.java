package com.middevs.wasi.service;

import com.middevs.wasi.dto.UserRegisterDTO;
import com.middevs.wasi.repository.UserJpaRepository;
import com.middevs.wasi.table.RealEstate;
import com.middevs.wasi.table.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserJpaRepository userJpaRepository;

    public User registerUser(UserRegisterDTO userDTO){
        User user = new User();
        user.setNameUser(userDTO.getNameUser());
        user.setDniUser(userDTO.getDniUser());
        user.setEmailUser(userDTO.getEmailUser());
        user.setPasswordUser(userDTO.getPasswordUser());
        user.setCellphoneUser(userDTO.getCellphoneUser());
        return userJpaRepository.save(user);
    }

    public List<User> identifyUser(Integer dniUser){
        return userJpaRepository.findByDniUser(dniUser);
    }

    public List<User> findEmailUser(String emailUser){
        return userJpaRepository.findByEmailUser(emailUser);
    }

    public User getById(Integer idUser) {
    	return userJpaRepository.getById(idUser);
    }
    public User findByIdUser(Integer idUser){ return userJpaRepository.findByIdUser(idUser);}

    public User update(User user) { return userJpaRepository.save(user);}

}
