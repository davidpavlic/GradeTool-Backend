package com.accenture.gradetool.domain.exam;

import com.accenture.gradetool.core.generic.AbstractEntity;
import com.accenture.gradetool.core.generic.ParentEntity;
import com.accenture.gradetool.domain.grade.Grade;
import com.accenture.gradetool.domain.schoolclasssemestersubject.SchoolClassSemesterSubject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "exam")
public class Exam extends AbstractEntity implements ParentEntity {

    private LocalDate date;

    private String name;

    private double weight;

    @ManyToOne
    @JoinColumn(name = "school_class_semester_subject_id")
    private SchoolClassSemesterSubject schoolClassSemesterSubject;

    @OneToMany(mappedBy = "exam", fetch = FetchType.EAGER)
    private List<Grade> grades = new ArrayList<>();

    @Override
    public Set<Supplier<Collection<? extends AbstractEntity>>> getChildrenSuppliers() {
        return Set.of(this::getGrades);
    }

    @Override
    public Exam setId(String id) {
        this.id = id;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public Exam setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public String getName() {
        return name;
    }

    public Exam setName(String name) {
        this.name = name;
        return this;
    }

    public double getWeight() {
        return weight;
    }

    public Exam setWeight(double weight) {
        this.weight = weight;
        return this;
    }

    public SchoolClassSemesterSubject getSchoolClassSemesterSubject() {
        return schoolClassSemesterSubject;
    }

    public Exam setSchoolClassSemesterSubject(SchoolClassSemesterSubject schoolClassSemesterSubject) {
        this.schoolClassSemesterSubject = schoolClassSemesterSubject;
        return this;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public Exam setGrades(List<Grade> grades) {
        this.grades = grades;
        return this;
    }
}
