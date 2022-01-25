package com.demo.nisum.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import com.demo.nisum.common.exception.BusinessLogicException;
import com.demo.nisum.controller.UserDetailDto;
import com.demo.nisum.domain.UserEntity;
import com.demo.nisum.repository.RoleRepository;
import com.demo.nisum.repository.UserRepository;
import com.demo.nisum.security.JwtTokenProvider;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	
	private static final String BUSINESS_LOGIC_EXCEPTION_DETAIL = "SOME EXCEPTION";

	private static final String PASSWORD = "123qweasd";

	private static final String USER = "francisco@briceno.com";

	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private RoleRepository roleRepository;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@Mock
	private JwtTokenProvider tokenProvider;
	
	@Mock
	private AuthenticationManager authenticationManager;
	
	private static final String TOKEN = "123213546564645454564adfasdfasdf654asdfasdfasdf798asdf";
	
	@BeforeEach
	void init() {
		userService = new UserService(userRepository, roleRepository, passwordEncoder, tokenProvider, authenticationManager) {

			@Override
			public String createToken(String email) {
				return TOKEN;
			}
					
		};

	}
	
    @Test
	void testSignin_whenUserIsFound_shouldReturnToken() {    	
    	Optional<UserEntity> user = Optional.of(getDummieUserEntity());
    	Mockito.when(userRepository.findByEmail(Mockito.anyString()))
    			.thenReturn(user) ;
    	Mockito.when(userRepository.save(Mockito.any())).thenReturn(user.get());
    	
    	 Optional<String> actualToken = userService.signin(USER, PASSWORD);
    	 assertTrue(actualToken.isPresent());
    	 assertSame(user.get().getToken(), TOKEN);
	}
    
    @Test
     void testSignin_whenUserIsNotFound_shouldReturnEmptyString() {
    	Optional<UserEntity> user = Optional.ofNullable(null);
    	Mockito.when(userRepository.findByEmail(Mockito.anyString()))
				.thenReturn(user) ;
    	Optional<String> actualToken = userService.signin(USER, PASSWORD);
    	assertFalse(actualToken.isPresent());
    }
    
    @Test
    void testSignin_whenRepositoryThrowException_shouldThrowsBusinessException() {
    	Optional<UserEntity> user = Optional.of(getDummieUserEntity());
    	Mockito.when(userRepository.findByEmail(Mockito.anyString()))
    			.thenReturn(user) ;
    	Mockito.when(userRepository.save(Mockito.any())).thenThrow(new BusinessLogicException(BUSINESS_LOGIC_EXCEPTION_DETAIL));
    	assertThatExceptionOfType(BusinessLogicException.class).isThrownBy(()->userService.signin(USER, PASSWORD))
    			.withMessage(BUSINESS_LOGIC_EXCEPTION_DETAIL);
    }

	private UserEntity getDummieUserEntity() {
		UserEntity userEntity = new UserEntity();
    	userEntity.setCreated(LocalDateTime.now());
    	userEntity.setEmail(USER);
    	userEntity.setId(UUID.randomUUID());
    	userEntity.setIsActive(true);
    	userEntity.setLastLogin(LocalDateTime.now());
    	userEntity.setName("Francisco");
    	userEntity.setPassword("satelite");
    	return userEntity;
	}
   

	@Test
	void testFindAll_shouldReturnListOfUserDto() {
		List<UserEntity> usersRepo = new ArrayList<UserEntity>();
		usersRepo.add(getDummieUserEntity());
		Mockito.when(userRepository.findAll()).thenReturn(usersRepo);
		List<UserDetailDto> users = userService.findAll();
		assertNotNull(users);
		assertFalse(users.isEmpty());
		assertSame(USER, users.get(0).getEmail());
	}

}
