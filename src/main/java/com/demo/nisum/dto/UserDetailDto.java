package com.demo.nisum.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailDto {
	private String id;
	private String name;
	private String email;
	private LocalDateTime created;
	private LocalDateTime modified;
	private LocalDateTime lastLogin;
	private String token;
	private Boolean isActive;
	
}
