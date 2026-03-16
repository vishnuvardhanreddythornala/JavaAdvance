package com.cap.APPOINTMENT_SERVICE.service;

import com.cap.APPOINTMENT_SERVICE.client.DoctorClient;
import com.cap.APPOINTMENT_SERVICE.dto.AppointmentRequestDTO;
import com.cap.APPOINTMENT_SERVICE.dto.AppointmentResponseDTO;
import com.cap.APPOINTMENT_SERVICE.dto.DoctorResponseDTO;
import com.cap.APPOINTMENT_SERVICE.entity.Appointment;
import com.cap.APPOINTMENT_SERVICE.exception.AppointmentNotFoundException;
import com.cap.APPOINTMENT_SERVICE.exception.DoctorNotFoundException;
import com.cap.APPOINTMENT_SERVICE.repository.AppointmentRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorClient doctorClient;
    private final ModelMapper modelMapper;

    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO request) {

        Long doctorId = request.getDoctorId();

        DoctorResponseDTO doctor;

        try {
            doctor = doctorClient.getDoctorById(doctorId);
        } catch (FeignException.NotFound ex) {
            throw new DoctorNotFoundException("Doctor does not exist");
        }

        Appointment appointment = new Appointment();

        appointment.setPatientName(request.getPatientName());
        appointment.setDoctorId(doctorId);
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setStatus("BOOKED");

        Appointment saved = appointmentRepository.save(appointment);

        AppointmentResponseDTO response =
                modelMapper.map(saved, AppointmentResponseDTO.class);

        response.setDoctor(doctor);

        return response;
    }

    public List<AppointmentResponseDTO> getAllAppointments() {

        List<Appointment> appointments = appointmentRepository.findAll();

        return appointments.stream().map(appointment -> {

            DoctorResponseDTO doctor = doctorClient.getDoctorById(appointment.getDoctorId());
            AppointmentResponseDTO response = modelMapper.map(appointment, AppointmentResponseDTO.class);
            response.setDoctor(doctor);
            return response;

        }).toList();
    }

    public AppointmentResponseDTO getAppointmentById(Long appointmentId) {

        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new AppointmentNotFoundException("Appointment not found"));

        DoctorResponseDTO doctor = doctorClient.getDoctorById(appointment.getDoctorId());

        AppointmentResponseDTO response = modelMapper.map(appointment, AppointmentResponseDTO.class);

        response.setDoctor(doctor);

        return response;
    }

    public AppointmentResponseDTO deleteAppointment(Long appointmentId) {

        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new AppointmentNotFoundException("Appointment not found"));

        DoctorResponseDTO doctor = doctorClient.getDoctorById(appointment.getDoctorId());

        appointmentRepository.delete(appointment);

        AppointmentResponseDTO response = modelMapper.map(appointment, AppointmentResponseDTO.class);

        response.setDoctor(doctor);

        return response;
    }


}
