package com.demo.nisum.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.nisum.common.exception.BusinessLogicException;
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
	
	
	public Optional<UserDetailDto> create(UserDto userDto) {
		List<PhoneEntity> phones = new ArrayList<>();
		if(!userRepository.findByEmail(userDto.getEmail()).isPresent()) {
			phones = userDto.getPhones().stream().map(phone->{
				PhoneEntity itemPhone = new PhoneEntity();
				itemPhone.setCitycode(phone.getCitycode());
				itemPhone.setCountrycode(phone.getCountrycode());
				itemPhone.setNumber(phone.getNumber());
				return itemPhone;
			}).collect(Collectors.toList());
			
			UserEntity userEntity = new UserEntity();
			userEntity.setPhones(phones);
			userEntity.setName(userDto.getName());
			userEntity.setEmail(userDto.getEmail());
			userEntity.setPassword(userDto.getPassword());
			
			userRepository.save(userEntity);
			
			UserDetailDto detail = new UserDetailDto();
			detail.setId(userEntity.getId().toString());
			detail.setName(userEntity.getName());
			detail.setEmail(userEntity.getEmail());
			detail.setCreated(userEntity.getCreated());
			detail.setModified(userEntity.getModified());
			detail.setLastLogin(userEntity.getLastLogin());
			detail.setToken(userEntity.getToken());
			return Optional.of(detail);
		}
		return Optional.empty();
	}
}
