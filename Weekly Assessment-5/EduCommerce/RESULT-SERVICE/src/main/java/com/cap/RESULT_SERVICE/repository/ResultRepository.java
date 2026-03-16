package com.cap.RESULT_SERVICE.repository;

import com.cap.RESULT_SERVICE.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    List<Result> findByStudentId(Long studentId);

    List<Result> findByCourseId(Long courseId);
}