package com.middevs.wasi.usecase;


import com.middevs.wasi.dto.ResponseDTO;
import com.middevs.wasi.dto.UserRegisterDTO;
import com.middevs.wasi.dto.UserRegisterResponseDTO;
import com.middevs.wasi.repository.UserJpaRepository;
import com.middevs.wasi.service.UserService;
import com.middevs.wasi.table.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Base64;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserUseCase {

    @Autowired
    UserService userService;

    public ResponseDTO ejecutar(UserRegisterDTO userRegisterDTO){

        List<User> user = userService.identifyUser(userRegisterDTO.getDniUser());
        if (user.isEmpty()) {
            UserRegisterResponseDTO userRegisterResponseDTO = new UserRegisterResponseDTO();
            String originalInput = userRegisterDTO.getPasswordUser();
            String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
            userRegisterDTO.setPasswordUser(encodedString);
            User userNew = userService.registerUser(userRegisterDTO);
            userRegisterResponseDTO.setIdUser(userNew.getIdUser());
            userRegisterResponseDTO.setDniUser(userNew.getDniUser());
            userRegisterResponseDTO.setCellphoneUser(userNew.getCellphoneUser());
            userRegisterResponseDTO.setEmailUser(userNew.getEmailUser());
            userRegisterResponseDTO.setNameUser(userNew.getNameUser());
            return new ResponseDTO(userRegisterResponseDTO);
        }else{
            return new ResponseDTO("error", "El usuario ya existe");
        }
    }
}
