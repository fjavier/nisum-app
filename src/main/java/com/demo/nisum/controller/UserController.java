package com.demo.nisum.controller;

import java.util.List;

import javax.validation.Valid;

import com.demo.nisum.dto.LoginDto;
import com.demo.nisum.dto.UserDetailDto;
import com.demo.nisum.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.nisum.common.exception.BusinessLogicException;
import com.demo.nisum.service.UserServiceImpl;

@RequiredArgsConstructor
@RestController()
@RequestMapping("/users")
public class UserController {

	private final UserServiceImpl userService;

	@PostMapping(path = "/signup", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public UserDetailDto signup(@RequestBody @Valid UserDto user) {
		return this.userService.create(user)
				 .orElseThrow(() -> new BusinessLogicException("email ya se encuentra registrado"));
		
	}
	
	@GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public List<UserDetailDto> getAll(){
		return this.userService.findAll();
	}
	
	@PostMapping(path = "/signin")
	@ResponseStatus(code = HttpStatus.OK)
	public String signin(@RequestBody @Valid LoginDto loginDto) {
		return this.userService.signin(loginDto.getEmail(), loginDto.getPassword()).orElseThrow(
				()->new BusinessLogicException("Credenciales invalidas"));
	}
}
