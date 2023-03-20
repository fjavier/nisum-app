package com.demo.nisum.dto;

import com.demo.nisum.util.validator.PasswordConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	@NotEmpty
	private String name;
	@NotEmpty
	@Email(regexp = ".+@.+\\..+", message = "El email debe tener un formato valido")
	private String email;
	@PasswordConstraint(message = "El password tiene un formato incorrecto")
	@NotEmpty
	private String password;
	@NotEmpty
	private List<PhoneDto> phones;
	
}
