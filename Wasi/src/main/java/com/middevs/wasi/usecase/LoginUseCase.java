package com.middevs.wasi.usecase;

import com.middevs.wasi.dto.LoginDTO;
import com.middevs.wasi.dto.LoginResponseDTO;
import com.middevs.wasi.dto.ResponseDTO;
import com.middevs.wasi.dto.UserRegisterDTO;
import com.middevs.wasi.service.UserService;
import com.middevs.wasi.table.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Base64;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
public class LoginUseCase {

    @Autowired
    UserService userService;

    public ResponseDTO ejecutar(LoginDTO loginDTO) {
        List<User> users = userService.findEmailUser(loginDTO.getEmailUser());
        if (users.isEmpty()){
            return new ResponseDTO("error", "El email ingresado no existe");
        }
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        // Convertir la contraseña de la base datos a Texto (String)
        String passwordClient = users.get(0).getPasswordUser();
        byte[] decodedBytes = Base64.getDecoder().decode(passwordClient);
        String decodedString = new String(decodedBytes);

        // Se verifica la contraseña
        if (decodedString.equals(loginDTO.getPasswordUser())){
            loginResponseDTO.setEmailUser(users.get(0).getEmailUser());
            loginResponseDTO.setIdUser(users.get(0).getIdUser());
            loginResponseDTO.setCellphoneUser(users.get(0).getCellphoneUser());
            loginResponseDTO.setNameUser(users.get(0).getNameUser());
            loginResponseDTO.setDniUser(users.get(0).getDniUser());
            return new ResponseDTO(loginResponseDTO);
        }else{
            return new ResponseDTO("error", "La contraseña ingresada es incorrecta");
        }
    }
}
