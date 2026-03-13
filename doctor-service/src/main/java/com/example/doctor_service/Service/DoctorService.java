package com.example.doctor_service.Service;

import com.example.doctor_service.Dto.DoctorRequestDTO;
import com.example.doctor_service.Dto.DoctorResponseDTO;
import com.example.doctor_service.Entity.Doctor;
import com.example.doctor_service.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repository;

    public DoctorResponseDTO createDoctor(DoctorRequestDTO request){

        Doctor doctor = new Doctor();

        doctor.setName(request.getName());
        doctor.setSpecialization(request.getSpecialization());
        doctor.setExperience(request.getExperience());
        doctor.setHospitalName(request.getHospitalName());
        doctor.setAvailability(request.getAvailability());

        Doctor savedDoctor = repository.save(doctor);

        DoctorResponseDTO response = new DoctorResponseDTO();

        response.setDoctorId(savedDoctor.getDoctorId());
        response.setName(savedDoctor.getName());
        response.setSpecialization(savedDoctor.getSpecialization());
        response.setExperience(savedDoctor.getExperience());
        response.setHospitalName(savedDoctor.getHospitalName());
        response.setAvailability(savedDoctor.getAvailability());

        return response;
    }

    public List<DoctorResponseDTO> getAllDoctors(){

        return repository.findAll()
                .stream()
                .map(doctor -> {
                    DoctorResponseDTO dto = new DoctorResponseDTO();
                    dto.setDoctorId(doctor.getDoctorId());
                    dto.setName(doctor.getName());
                    dto.setSpecialization(doctor.getSpecialization());
                    dto.setExperience(doctor.getExperience());
                    dto.setHospitalName(doctor.getHospitalName());
                    dto.setAvailability(doctor.getAvailability());
                    return dto;
                }).collect(Collectors.toList());
    }

}