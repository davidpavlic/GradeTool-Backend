package com.accenture.gradetool.domain.schoolclasssemestersubject;

import com.accenture.gradetool.core.generic.AbstractEntity;
import com.accenture.gradetool.core.generic.ParentEntity;
import com.accenture.gradetool.domain.exam.Exam;
import com.accenture.gradetool.domain.schoolclasssemester.SchoolClassSemester;
import com.accenture.gradetool.domain.subject.Subject;
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
@Table(name = "school_class_semester_subject")
public class SchoolClassSemesterSubject extends AbstractEntity implements ParentEntity {

    @ManyToOne
    @JoinColumn(name = "school_class_semester_id")
    private SchoolClassSemester schoolClassSemester;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToMany(mappedBy = "schoolClassSemesterSubject", fetch = FetchType.EAGER)
    private List<Exam> exams = new ArrayList<>();

    @Override
    public Set<Supplier<Collection<? extends AbstractEntity>>> getChildrenSuppliers() {
        return Set.of(this::getExams);
    }

    @Override
    public SchoolClassSemesterSubject setId(String id) {
        this.id = id;
        return this;
    }

    public SchoolClassSemester getSchoolClassSemester() {
        return schoolClassSemester;
    }

    public SchoolClassSemesterSubject setSchoolClassSemester(SchoolClassSemester schoolClassSemester) {
        this.schoolClassSemester = schoolClassSemester;
        return this;
    }

    public Subject getSubject() {
        return subject;
    }

    public SchoolClassSemesterSubject setSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public SchoolClassSemesterSubject setExams(List<Exam> exams) {
        this.exams = exams;
        return this;
    }
}
