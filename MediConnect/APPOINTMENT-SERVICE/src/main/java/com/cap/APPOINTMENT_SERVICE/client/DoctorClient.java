package com.cap.APPOINTMENT_SERVICE.client;

import com.cap.APPOINTMENT_SERVICE.dto.DoctorResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "DOCTOR-SERVICE")
public interface DoctorClient {

        @GetMapping("/doctors/{doctorId}")
        DoctorResponseDTO getDoctorById(@PathVariable Long doctorId);

}
