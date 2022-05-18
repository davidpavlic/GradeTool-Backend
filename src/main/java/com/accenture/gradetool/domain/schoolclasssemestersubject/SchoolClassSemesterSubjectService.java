package com.accenture.gradetool.domain.schoolclasssemestersubject;

import com.accenture.gradetool.domain.exam.Exam;
import java.util.Collection;
import java.util.NoSuchElementException;

public interface SchoolClassSemesterSubjectService {

    SchoolClassSemesterSubject find(String schoolClassSemesterSubjectId);

    SchoolClassSemesterSubject find(String schoolClassId, String semesterId, String subjectId) throws NoSuchElementException;

    SchoolClassSemesterSubject addSubjectToSchoolClassSemester(String subjectId, String schoolClassId, String semesterId)
        throws NoSuchElementException;

    void removeSubjectFromSchoolClassSemester(String subjectId, String schoolClassId, String semesterId) throws NoSuchElementException;

}
