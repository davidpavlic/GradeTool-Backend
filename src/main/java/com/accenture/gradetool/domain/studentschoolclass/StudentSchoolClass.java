package com.accenture.gradetool.domain.studentschoolclass;

import com.accenture.gradetool.core.generic.AbstractEntity;
import com.accenture.gradetool.domain.schoolclass.SchoolClass;
import com.accenture.gradetool.domain.student.Student;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student_school_class")
public class StudentSchoolClass extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "school_class_id")
    private SchoolClass schoolClass;

    @Override
    public StudentSchoolClass setId(String id) {
        this.id = id;
        return this;
    }

    public Student getStudent() {
        return student;
    }

    public StudentSchoolClass setStudent(Student student) {
        this.student = student;
        return this;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public StudentSchoolClass setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
        return this;
    }

    public String getName() {
        return schoolClass.getName();
    }

}
