package com.iwmn.a1.lead.model.jpa;

import com.iwmn.a1.lead.model.LanguageConstants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Create the model classes with the table prefix according to the project name
 * 
 * @author HP5330
 * 
 */
@Entity
@Table(name = "VIPCITY2")
public class City {
	@Id
	@Column(name = "bfc_id")
	@GeneratedValue
	private Long id;

	private String NameMK;
	private String NameEN;
	private String NameAL;
	private String Email;

	private Integer weight;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameMK() {
		return NameMK;
	}

	public void setNameMK(String s) {
		this.NameMK = s;
	}
	
	public String getNameEN() {
		return NameEN;
	}

	public void setNameEN(String s) {
		this.NameEN = s;
	}
	public String getNameAL() {
		return NameAL;
	}

	public void setNameAL(String s) {
		this.NameAL = s;
	}
	
	public String getEmail() {
		return Email;
	}

	public void setEmail(String s) {
		this.Email = s;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getNameByLocale(String language) {
		if (language.equals(LanguageConstants.mk_MK)) {
			return this.NameMK;
		} else if (language.equals(LanguageConstants.en_EN)) {
			return this.NameEN;
		} else if (language.equals(LanguageConstants.sq_AL)) {
			return this.NameAL;
		} else {
			return this.NameMK;
		}
	}

}
