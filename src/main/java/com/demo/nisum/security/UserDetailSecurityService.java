package com.demo.nisum.security;

import static org.springframework.security.core.userdetails.User.withUsername;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.demo.nisum.domain.UserEntity;
import com.demo.nisum.repository.UserRepository;

@Component
public class UserDetailSecurityService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtTokenProvider tokenProvider;

	/**
	 * Usaremos email en vez de username
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByEmail(email)
					.orElseThrow(() ->
					new UsernameNotFoundException(String.format("User with email %s does not exist", email)));
		
		 return withUsername(user.getEmail())
	            .password(user.getPassword())
	            .authorities(user.getRoles())
	            .accountExpired(false)
	            .accountLocked(false)
	            .credentialsExpired(false)
	            .disabled(false)
	            .build();
	}
	
	 public Optional<UserDetails> loadUserByJwtTokenAndDatabase(String jwtToken) {
	        if (tokenProvider.isValidToken(jwtToken)) {
	            return Optional.of(loadUserByUsername(tokenProvider.getEmail(jwtToken)));
	        } 
	        
	        return Optional.empty();	     
	    }
	 
	 public Optional<UserDetails> loadUserByJwtToken(String jwtToken) {
	        if (tokenProvider.isValidToken(jwtToken)) {
	            return Optional.of(
	                withUsername(tokenProvider.getEmail(jwtToken))
	                .authorities(new SimpleGrantedAuthority("STANDARD_USER"))
	                .password("") //token does not have password but field may not be empty
	                .accountExpired(false)
	                .accountLocked(false)
	                .credentialsExpired(false)
	                .disabled(false)
	                .build());
	        }
	        return Optional.empty();
	    }

}
