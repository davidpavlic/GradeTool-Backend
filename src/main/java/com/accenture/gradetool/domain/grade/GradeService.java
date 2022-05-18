package com.accenture.gradetool.domain.grade;

import com.accenture.gradetool.domain.exam.Exam;
import com.accenture.gradetool.domain.student.Student;

import java.util.Collection;

public interface GradeService {

    boolean studentOwnsGrade(Student student, String gradeId);

    Grade create(String schoolClassId, String semesterId, String subjectId, String examId, Grade grade, Student student);

    Grade createByPath(double mark, String examId, String studentId);

    Grade update(String gradeId, double mark);

    Grade find(String schoolClassId, String semesterId, String subjectId, String examId, String gradeId);

    Grade find(Exam exam, String gradeId);

    Grade findById(String gradeId);

    Collection<Grade> findAllByStudentId(String studentId);

    void delete(String gradeId);

}
