package com.example.SpringMvc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.SpringMvc.Model.Student;
import com.example.SpringMvc.Service.StudentService;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@PostMapping("/register")
	public String registerStudent(@ModelAttribute Student student, Model model) {
		
		//1.MVC Flow: @ModelAttribute binds form data to Student object
		studentService.saveStudent(student);
		
		//2.MVC Flow: Model sends data to jsp view
		model.addAttribute("name", student.getName());
		
		return "success"; //Returns view name (success.jsp)
	}
	
	//3.MVC flow Get request to display registration form
	@GetMapping("/register")
	public String showForm() {
		return "register";
	}
}
