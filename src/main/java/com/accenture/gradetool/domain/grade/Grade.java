package com.accenture.gradetool.domain.grade;

import com.accenture.gradetool.core.generic.AbstractEntity;
import com.accenture.gradetool.domain.exam.Exam;
import com.accenture.gradetool.domain.student.Student;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "grade")
public class Grade extends AbstractEntity {

    private double mark;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @Override
    public Grade setId(String id) {
        this.id = id;
        return this;
    }

    public double getMark() {
        return mark;
    }

    public Grade setMark(double mark) {
        this.mark = mark;
        return this;
    }

    public Student getStudent() {
        return student;
    }

    public Grade setStudent(Student student) {
        this.student = student;
        return this;
    }

    public Exam getExam() {
        return exam;
    }

    public Grade setExam(Exam exam) {
        this.exam = exam;
        return this;
    }
}
