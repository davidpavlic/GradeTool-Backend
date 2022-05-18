package com.accenture.gradetool.domain.schoolclasssemester;

import com.accenture.gradetool.core.generic.AbstractEntity;
import com.accenture.gradetool.core.generic.ParentEntity;
import com.accenture.gradetool.domain.schoolclass.SchoolClass;
import com.accenture.gradetool.domain.schoolclasssemestersubject.SchoolClassSemesterSubject;
import com.accenture.gradetool.domain.semester.Semester;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "school_class_semester")
public class SchoolClassSemester extends AbstractEntity implements ParentEntity {

    @ManyToOne
    @JoinColumn(name = "school_class_id")
    private SchoolClass schoolClass;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @OneToMany(mappedBy = "schoolClassSemester", fetch = FetchType.EAGER)
    private Set<SchoolClassSemesterSubject> schoolClassSemesterSubjects = new HashSet<>();

    @Override
    public Set<Supplier<Collection<? extends AbstractEntity>>> getChildrenSuppliers() {
        return Set.of(this::getSchoolClassSemesterSubjects);
    }

    @Override
    public SchoolClassSemester setId(String id) {
        this.id = id;
        return this;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public SchoolClassSemester setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
        return this;
    }

    public Semester getSemester() {
        return semester;
    }

    public SchoolClassSemester setSemester(Semester semester) {
        this.semester = semester;
        return this;
    }

    public Set<SchoolClassSemesterSubject> getSchoolClassSemesterSubjects() {
        return schoolClassSemesterSubjects;
    }

    public SchoolClassSemester setSchoolClassSemesterSubjects(Set<SchoolClassSemesterSubject> schoolClassSemesterSubjects) {
        this.schoolClassSemesterSubjects = schoolClassSemesterSubjects;
        return this;
    }
}
