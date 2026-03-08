package com.cap.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "person")

public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "person_seq")
	@SequenceGenerator(name = "person_seq", sequenceName = "person_sequence",initialValue = 100, allocationSize =1)
	
	private int personId;
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "passport_id")
	private Passport passport;
	
	public Person() {}
	
	public Person(String name, Passport passport) {
		this.setName(name);
		this.passport = passport;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return personId;
	}
	public void setId(int personId) {
		this.personId = personId;
	}

	public Passport getPassport() {
		return passport;
	}
	public void setPassport(Passport passport) {
		this.passport = passport;
		passport.setPerson(this);
	}
	
	
	

}
