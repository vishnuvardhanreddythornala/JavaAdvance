package com.example.SpringMvc.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "StudentTable")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;
	private String email;


	public Student(String name, String email){
		this.name = name;
		this.email =  email;
	}

	public Student() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getName() {

		return name;

	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {

		this.email = email;

	}
	

}
