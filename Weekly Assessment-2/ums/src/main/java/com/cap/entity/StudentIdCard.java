package com.cap.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "idcard")
public class StudentIdCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String cardNumber;

    @OneToOne(mappedBy = "idCard")
    private Student student;

    public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public int getID() {
    	return id;
    }
    public void setId(int id) {
    	this.id = id;
    }
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	
}