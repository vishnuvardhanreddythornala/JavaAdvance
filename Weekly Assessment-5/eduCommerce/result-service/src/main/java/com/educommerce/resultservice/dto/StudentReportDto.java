package com.educommerce.resultservice.dto;

import com.educommerce.resultservice.entity.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentReportDto {
    private StudentDto student;
    private List<Result> results;
    private double gpa;
    private String attendanceInfo;
}
