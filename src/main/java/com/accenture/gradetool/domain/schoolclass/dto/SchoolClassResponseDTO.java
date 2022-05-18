package com.accenture.gradetool.domain.schoolclass.dto;

import com.accenture.gradetool.domain.exam.dto.ExamDTO;
import com.accenture.gradetool.domain.schoolclasssemester.dto.SchoolClassSemesterDTO;
import com.accenture.gradetool.domain.schoolclasssemestersubject.dto.SchoolClassSemesterSubjectDTO;
import com.accenture.gradetool.domain.semester.dto.SemesterDTO;
import com.accenture.gradetool.domain.student.dto.StudentDTO;
import com.accenture.gradetool.domain.studentschoolclass.dto.StudentSchoolClassDTO;
import com.accenture.gradetool.domain.subject.dto.SubjectDTO;
import java.util.Collection;
import java.util.HashSet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolClassResponseDTO extends SchoolClassDTO {

    protected Collection<StudentSchoolClass> studentSchoolClasses = new HashSet<>();
    protected Collection<SchoolClassSemester> schoolClassSemesters = new HashSet<>();

    @Getter
    @Setter
    static class StudentSchoolClass extends StudentSchoolClassDTO {

        protected StudentDTO student;
    }

    @Getter
    @Setter
    static class SchoolClassSemester extends SchoolClassSemesterDTO {

        protected SemesterDTO semester;
        protected Collection<SchoolClassSemesterSubject> schoolClassSemesterSubjects = new HashSet<>();
    }

    @Getter
    @Setter
    static class SchoolClassSemesterSubject extends SchoolClassSemesterSubjectDTO {

        protected SubjectDTO subject;
        protected Collection<Exam> exams = new HashSet<>();
    }

    @Getter
    @Setter
    static class Exam extends ExamDTO {

    }

}
