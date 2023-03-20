package com.demo.nisum.service;

import com.demo.nisum.dto.UserDetailDto;
import com.demo.nisum.dto.UserDto;

import java.util.Optional;

public interface UserService {
    Optional<String> signin(String email, String password);

    Optional<UserDetailDto> create(UserDto userDto);

    String createToken(String email);
}
