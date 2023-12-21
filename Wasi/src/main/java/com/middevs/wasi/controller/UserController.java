package com.middevs.wasi.controller;

import com.middevs.wasi.dto.LoginDTO;
import com.middevs.wasi.dto.ResponseDTO;
import com.middevs.wasi.dto.UserRegisterDTO;
import com.middevs.wasi.repository.UserJpaRepository;
import com.middevs.wasi.service.UserService;
import com.middevs.wasi.usecase.LoginUseCase;
import com.middevs.wasi.usecase.RegisterContactUseCase;
import com.middevs.wasi.usecase.RegisterUserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody UserRegisterDTO userRegisterDTO){
        RegisterUserUseCase registerUserUseCase = new RegisterUserUseCase(userService);
        return new ResponseEntity<ResponseDTO>(registerUserUseCase.ejecutar(userRegisterDTO), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> doLogin(@RequestBody LoginDTO loginDTO){
        LoginUseCase loginCase = new LoginUseCase(userService);
        return new ResponseEntity<ResponseDTO>(loginCase.ejecutar(loginDTO), HttpStatus.OK);
    }
}
