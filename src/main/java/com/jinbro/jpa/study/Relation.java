package com.jinbro.jpa.study;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Relation {


	@Entity
	public static class Study {

		@Id
		@GeneratedValue
		private Long id;

		private String name;

		@ManyToOne // study(n) -> owner(1), create foreign key, relation owner
		private Account owner;
	}

	@Entity
	public static class Account {

		@Id
		@GeneratedValue
		private Long id;

		private String name;

		private String password;

		// @OneToMany -> create Join(link) table
		private Set<Study> studies;
	}
}
