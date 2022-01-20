package com.demo.nisum.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.nisum.controller.UserDetailDto;
import com.demo.nisum.controller.UserDto;
import com.demo.nisum.domain.PhoneEntity;
import com.demo.nisum.domain.UserEntity;
import com.demo.nisum.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public UserDetailDto create(UserDto userDto) {
		List<PhoneEntity> phones = new ArrayList<>();
		
		phones = userDto.getPhones().stream().map(phone->{
			PhoneEntity itemPhone = new PhoneEntity();
			itemPhone.setCitycode(phone.getCitycode());
			itemPhone.setCountrycode(phone.getCountrycode());
			itemPhone.setNumber(phone.getNumber());
			return itemPhone;
		}).collect(Collectors.toList());
		
		UserEntity user = new UserEntity();
		user.setPhones(phones);
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		
		userRepository.save(user);
		
		UserDetailDto detail = new UserDetailDto();
		detail.setId(user.getId().toString());
		detail.setName(user.getName());
		detail.setEmail(user.getEmail());
		detail.setCreated(user.getCreated());
		detail.setModified(user.getModified());
		detail.setLastLogin(user.getLastLogin());
		detail.setToken(user.getToken());
		return detail;
	}
}
