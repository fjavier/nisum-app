/**
 * 
 */
package com.demo.nisum.domain;

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
@Entity(name = "phone")
public class PhoneEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8821784604093933820L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
	private Long id;
	private String number;
	private String citycode;
	private String countrycode;
	
	
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * @return the citycode
	 */
	public String getCitycode() {
		return citycode;
	}
	/**
	 * @param citycode the citycode to set
	 */
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	/**
	 * @return the countrycode
	 */
	public String getCountrycode() {
		return countrycode;
	}
	/**
	 * @param countrycode the countrycode to set
	 */
	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
