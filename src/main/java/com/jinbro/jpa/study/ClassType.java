package com.jinbro.jpa.study;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// com.jinbro.jpa
public class ClassType {

	// entity type
	@Entity
	class Account {

		@Id
		@GeneratedValue
		private Long id;

		private String name;

		private String password;

		@Embedded
		@AttributeOverrides({
			@AttributeOverride(name = "", column = @Column(name = "home_street"))
		})
		private Address homeAddress;

		@Embedded
		private Address companyAddress;
	}

	// value type
	@Embeddable
	class Address {

		private String city;

		private String state;

		private String street;

		private String zipCode;
	}

}