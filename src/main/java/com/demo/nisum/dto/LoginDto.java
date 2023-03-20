package com.demo.nisum.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
	
	@NotEmpty
	private String email;
	@NotEmpty
	private String password;

}
