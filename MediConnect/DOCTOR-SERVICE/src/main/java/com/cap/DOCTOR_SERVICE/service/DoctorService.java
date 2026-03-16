package com.cap.DOCTOR_SERVICE.service;

import com.cap.DOCTOR_SERVICE.dto.DoctorRequestDTO;
import com.cap.DOCTOR_SERVICE.dto.DoctorResponseDTO;
import com.cap.DOCTOR_SERVICE.entity.Doctor;
import com.cap.DOCTOR_SERVICE.exceptions.DoctorNotFoundException;
import com.cap.DOCTOR_SERVICE.repository.DoctorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
        this.modelMapper = new ModelMapper();
    }

    public DoctorResponseDTO createDoctor(DoctorRequestDTO doctorRequestDTO) {
        Doctor doctor = modelMapper.map(doctorRequestDTO, Doctor.class);
        doctor = doctorRepository.save(doctor);
        return (modelMapper.map(doctor, DoctorResponseDTO.class));
    }

    public DoctorResponseDTO updateDoctor(Long id, DoctorRequestDTO dto) {

        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new DoctorNotFoundException("Doctor not found"));

        doctor.setName(dto.getName());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setExperience(dto.getExperience());
        doctor.setHospitalName(dto.getHospitalName());
        doctor.setAvailability(dto.getAvailability());

        doctor = doctorRepository.save(doctor);

        return (modelMapper.map(doctor, DoctorResponseDTO.class));
    }

    public DoctorResponseDTO deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new DoctorNotFoundException("Doctor not found"));

        doctorRepository.delete(doctor);

        return (modelMapper.map(doctor, DoctorResponseDTO.class));
    }

    public DoctorResponseDTO getDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new DoctorNotFoundException("Doctor not found"));
        return (modelMapper.map(doctor, DoctorResponseDTO.class));
    }

    public List<DoctorResponseDTO> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();

        return (doctors.stream()
                .map(doctor -> new DoctorResponseDTO(
                        doctor.getDoctorId(),
                        doctor.getName(),
                        doctor.getSpecialization(),
                        doctor.getHospitalName(),
                        doctor.getExperience(),
                        doctor.getAvailability()
                ))
                .toList());
    }
}
