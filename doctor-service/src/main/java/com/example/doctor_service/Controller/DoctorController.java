package com.example.doctor_service.Controller;

import com.example.doctor_service.Entity.Doctor;
import com.example.doctor_service.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService service;

    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor){
        return service.saveDoctor(doctor);
    }

    @GetMapping
    public List<Doctor> getAllDoctors(){
        return service.getAllDoctors();
    }

    @GetMapping("/{id}")
    public Doctor getDoctor(@PathVariable Long id){
        return service.getDoctor(id);
    }

    @DeleteMapping("/{id}")
    public String deleteDoctor(@PathVariable Long id){
        service.deleteDoctor(id);
        return "Doctor deleted";
    }

}
