package com.accenture.gradetool.domain.student.dto;

import com.accenture.gradetool.domain.exam.dto.ExamDTO;
import com.accenture.gradetool.domain.grade.dto.GradeDTO;
import com.accenture.gradetool.domain.schoolclass.dto.SchoolClassDTO;
import com.accenture.gradetool.domain.schoolclasssemester.dto.SchoolClassSemesterDTO;
import com.accenture.gradetool.domain.schoolclasssemestersubject.dto.SchoolClassSemesterSubjectDTO;
import com.accenture.gradetool.domain.semester.dto.SemesterDTO;
import com.accenture.gradetool.domain.studentschoolclass.dto.StudentSchoolClassDTO;
import com.accenture.gradetool.domain.subject.dto.SubjectDTO;
import com.accenture.gradetool.domain.supervisor.dto.SupervisorDTO;
import java.util.Collection;
import java.util.HashSet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponseDTO extends StudentDTO {

    protected Collection<StudentSchoolClassWithSchoolClass> studentSchoolClasses = new HashSet<>();
    protected Collection<SupervisorDTO> supervisors = new HashSet<>();

    @Getter
    @Setter
    static class StudentSchoolClassWithSchoolClass extends StudentSchoolClassDTO {

        protected SchoolClass schoolClass;
    }

    @Getter
    @Setter
    static class SchoolClass extends SchoolClassDTO {

        protected Collection<SchoolClassSemester> schoolClassSemesters = new HashSet<>();
        protected Collection<StudentSchoolClassWithStudent> studentSchoolClasses = new HashSet<>();
    }

    @Getter
    @Setter
    static class StudentSchoolClassWithStudent extends StudentSchoolClassDTO {

        protected Student student;
    }

    @Getter
    @Setter
    static class Student extends StudentDTO {

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

        protected GradeDTO grade;
    }

}
