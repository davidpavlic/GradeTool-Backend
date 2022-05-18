package com.accenture.gradetool.domain.student.dto;

import com.accenture.gradetool.core.generic.dto.DTOMapper;
import com.accenture.gradetool.domain.exam.Exam;
import com.accenture.gradetool.domain.grade.Grade;
import com.accenture.gradetool.domain.grade.dto.GradeDTO;
import com.accenture.gradetool.domain.student.Student;
import com.accenture.gradetool.domain.supervisor.dto.SupervisorMapper;
import java.util.List;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {SupervisorMapper.class})
public interface StudentMapper extends DTOMapper<StudentResponseDTO, Student> {

    Student fromDTO(StudentDTO.Registration dto);

    StudentResponseDTO toResponseDTO(Student student, @Context Student authenticated);

    default StudentResponseDTO toResponseDTO(Student student) {
        return toResponseDTO(student, student);
    }

    GradeDTO gradeToGradeDTO(Grade grade);

    @Mapping(target = "grade", source = "grades")
    StudentResponseDTO.Exam examToExam(Exam exam, @Context Student authenticated);

    default GradeDTO gradeListToGradeDTO(List<Grade> grades, @Context Student authenticated) {
        return grades.stream().filter(grade -> studentOwnsGrade(authenticated, grade)).map(this::gradeToGradeDTO).findFirst().orElse(null);
    }

    default boolean studentOwnsGrade(Student student, Grade grade) {
        return grade.getStudent() != null && grade.getStudent().getId().equals(student.getId());
    }

}