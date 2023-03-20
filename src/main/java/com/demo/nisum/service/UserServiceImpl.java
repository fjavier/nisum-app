package com.demo.nisum.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.demo.nisum.mapper.UserDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.nisum.common.exception.BusinessLogicException;
import com.demo.nisum.dto.UserDetailDto;
import com.demo.nisum.dto.UserDto;
import com.demo.nisum.domain.PhoneEntity;
import com.demo.nisum.domain.RoleEntity;
import com.demo.nisum.domain.UserEntity;
import com.demo.nisum.repository.RoleRepository;
import com.demo.nisum.repository.UserRepository;
import com.demo.nisum.security.JwtTokenProvider;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	
	private final RoleRepository roleRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JwtTokenProvider tokenProvider;
	
	private final AuthenticationManager authenticationManager;

	private final UserDetailMapper userDetailMapper;

	@Override
	public Optional<String> signin(String email, String password) {
	        Optional<String> token = Optional.empty();
	        Optional<UserEntity> user = userRepository.findByEmail(email);

	        if (user.isPresent()) {
	            try {
	                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
	                token = Optional.of(createToken(email));
	                user.get().setModified(LocalDateTime.now());
	                user.get().setLastLogin(LocalDateTime.now());
	                user.get().setToken(token.get());
	                user.get().setIsActive(true);
	                userRepository.save(user.get());
	            } catch (AuthenticationException e){
	            	throw new BusinessLogicException(e.getMessage());
	            }
	        }
	        return token;
	    }
	@Override
	public Optional<UserDetailDto> create(UserDto userDto) {
		UserEntity userEntity = saveUser(userDto);
		UserDetailDto detail = convert(userEntity);
		return Optional.of(detail);
	}


	private UserDetailDto convert(UserEntity userEntity) {
		return userDetailMapper.userEntityToUserDetailDto(userEntity);
	}

	@Override
	public String createToken(String email) {
		return tokenProvider.createToken(email);
	}
	
	public List<UserDetailDto> findAll() {
		List<UserDetailDto> users = userRepository.findAll().stream().map(entity -> convert(entity))
				.collect(Collectors.toList());
		return users;
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
	
	
}
