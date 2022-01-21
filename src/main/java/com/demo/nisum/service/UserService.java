package com.demo.nisum.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.nisum.controller.UserDetailDto;
import com.demo.nisum.controller.UserDto;
import com.demo.nisum.domain.PhoneEntity;
import com.demo.nisum.domain.RoleEntity;
import com.demo.nisum.domain.UserEntity;
import com.demo.nisum.repository.RoleRepository;
import com.demo.nisum.repository.UserRepository;
import com.demo.nisum.security.JwtTokenProvider;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	private RoleRepository roleRepository;
	
	private PasswordEncoder passwordEncoder;
	
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.tokenProvider = tokenProvider;
	}
	
	
	public Optional<UserDetailDto> create(UserDto userDto) {
		if(!userRepository.findByEmail(userDto.getEmail()).isPresent()) {
			UserEntity userEntity = saveUser(userDto);
			UserDetailDto detail = convert(userEntity);
			return Optional.of(detail);
		}
		return Optional.empty();
	}


	private UserDetailDto convert(UserEntity userEntity) {
		UserDetailDto detail = new UserDetailDto();
		detail.setId(userEntity.getId().toString());
		detail.setName(userEntity.getName());
		detail.setEmail(userEntity.getEmail());
		detail.setCreated(userEntity.getCreated());
		detail.setModified(userEntity.getModified());
		detail.setLastLogin(userEntity.getLastLogin());
		detail.setToken(userEntity.getToken());
		detail.setIsActive(userEntity.getIsActive());
		return detail;
	}


	private UserEntity saveUser(UserDto userDto) {
		List<PhoneEntity> phones = userDto.getPhones().stream().map(phone->{
			PhoneEntity itemPhone = new PhoneEntity();
			itemPhone.setCitycode(phone.getCitycode());
			itemPhone.setCountrycode(phone.getCountrycode());
			itemPhone.setNumber(phone.getNumber());
			return itemPhone;
		}).collect(Collectors.toList());
		
		UserEntity userEntity = new UserEntity();
		userEntity.setToken(createToken(userDto.getEmail()));
		userEntity.setPhones(phones);
		userEntity.setName(userDto.getName());
		userEntity.setEmail(userDto.getEmail());
		userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()) );
		userEntity.setLastLogin(LocalDateTime.now());
		userEntity.setIsActive(true);
		
		Optional<RoleEntity> role = roleRepository.findByRoleName("ROLE_STANDARD");
		List<RoleEntity> roles = new ArrayList<RoleEntity>();
		roles.add(role.get());
		userEntity.setRoles(roles); ;
		userRepository.save(userEntity);
		return userEntity;
	}
	
	public String createToken(String email) {
		return tokenProvider.createToken(email);
	}
	
	public List<UserDetailDto> findAll() {
		List<UserDetailDto> users = userRepository.findAll().stream().map(entity -> {
			return convert(entity);
		}).collect(Collectors.toList());
		return users;
	}
}
