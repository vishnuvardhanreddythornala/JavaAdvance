package com.cap.hibernateDemo.entity;
<<<<<<< HEAD
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
=======

>>>>>>> c0c9dcb9e4b2268da5804e24bb63b95c50d18b9f
import jakarta.persistence.*;

@Entity
@Table(name = "student")
<<<<<<< HEAD
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
=======
>>>>>>> c0c9dcb9e4b2268da5804e24bb63b95c50d18b9f
public class student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int marks;
    private String department;

    public student() {
    }

    public student(String name, int marks,String department) {
        this.name = name;
        this.marks = marks;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMarks() {
        return marks;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}