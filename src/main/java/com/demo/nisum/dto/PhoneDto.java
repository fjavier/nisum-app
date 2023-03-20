package com.demo.nisum.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDto {
	
	@NotEmpty
	private String number;
	@NotEmpty
	private String citycode;
	@NotEmpty
	private String countrycode;
	
}
