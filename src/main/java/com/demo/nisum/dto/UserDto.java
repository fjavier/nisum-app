package com.demo.nisum.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	@NotEmpty
	private String name;
	@NotEmpty
	@Email(regexp = ".+@.+\\..+")
	private String email;
	@NotEmpty
	private String password;
	@NotEmpty
	private List<PhoneDto> phones;
	
}
