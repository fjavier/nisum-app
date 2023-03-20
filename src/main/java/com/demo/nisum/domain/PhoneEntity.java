/**
 * 
 */
package com.demo.nisum.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author cisco505
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "phone")
public class PhoneEntity implements Serializable {
	private static final long serialVersionUID = -8821784604093933820L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
	private Long id;
	private String number;
	private String citycode;
	private String countrycode;
	
}
