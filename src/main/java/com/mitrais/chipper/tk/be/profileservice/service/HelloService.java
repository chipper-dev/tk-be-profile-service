package com.mitrais.chipper.tk.be.profileservice.service;

import com.mitrais.chipper.tk.be.profileservice.interfaces.UserInterface;
import com.mitrais.chipper.tk.be.profileservice.interfaces.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class HelloService {
    @Autowired
    private UserInterface userInterface;

    public UserResponseDto fetchUser(String token, Long id) {
        return userInterface.fetchUserbyId(token, id).orElseThrow(() -> new NoSuchElementException("nothing"));
    }
}
