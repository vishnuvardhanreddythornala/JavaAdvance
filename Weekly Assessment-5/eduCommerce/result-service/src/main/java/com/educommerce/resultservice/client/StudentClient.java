package com.educommerce.resultservice.client;

import com.educommerce.resultservice.dto.StudentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "student-service", fallback = StudentClientFallback.class)
public interface StudentClient {

    @GetMapping("/students/{id}")
    StudentDto getStudentById(@PathVariable("id") Long id);
}
