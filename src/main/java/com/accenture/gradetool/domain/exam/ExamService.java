package com.accenture.gradetool.domain.exam;

import java.util.Collection;

public interface ExamService {

    Exam find(String schoolClassId, String semesterId, String subjectId, String examId);

    Exam find(String schoolClassSemesterSubjectId, String examId);

    Exam findById(String examId);

    Collection<Exam> findAll(String schoolClassSemesterSubjectId);

    Collection<Exam> findAll();

    Exam create(String schoolClassId, String semesterId, String subjectId, Exam exam);

    Exam createByDTO(Exam exam);

    Exam update(Exam exam);

    void delete(String examId);

}
