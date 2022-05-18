package com.accenture.gradetool.domain.schoolclass;

import com.accenture.gradetool.core.generic.AbstractEntity;
import com.accenture.gradetool.core.generic.ParentEntity;
import com.accenture.gradetool.domain.schoolclasssemester.SchoolClassSemester;
import com.accenture.gradetool.domain.studentschoolclass.StudentSchoolClass;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "school_class")
public class SchoolClass extends AbstractEntity implements ParentEntity {

    private String name;

    @Column(name = "start_year")
    private int startYear;

    @Column(name = "end_year")
    private int endYear;

    @OneToMany(mappedBy = "schoolClass")
    private Set<StudentSchoolClass> studentSchoolClasses = new HashSet<>();

    @OneToMany(mappedBy = "schoolClass", cascade = CascadeType.PERSIST)
    private Set<SchoolClassSemester> schoolClassSemesters = new HashSet<>();

    @Override
    public Set<Supplier<Collection<? extends AbstractEntity>>> getChildrenSuppliers() {
        return Set.of(this::getSchoolClassSemesters, this::getStudentSchoolClasses);
    }

    @Override
    public SchoolClass setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SchoolClass setName(String name) {
        this.name = name;
        return this;
    }

    public int getStartYear() {
        return startYear;
    }

    public SchoolClass setStartYear(int startYear) {
        this.startYear = startYear;
        return this;
    }

    public int getEndYear() {
        return endYear;
    }

    public SchoolClass setEndYear(int endYear) {
        this.endYear = endYear;
        return this;
    }

    public Set<StudentSchoolClass> getStudentSchoolClasses() {
        return studentSchoolClasses;
    }

    public SchoolClass setStudentSchoolClasses(Set<StudentSchoolClass> studentSchoolClasses) {
        this.studentSchoolClasses = studentSchoolClasses;
        return this;
    }

    public Set<SchoolClassSemester> getSchoolClassSemesters() {
        return schoolClassSemesters;
    }

    public SchoolClass setSchoolClassSemesters(Set<SchoolClassSemester> schoolClassSemesters) {
        this.schoolClassSemesters = schoolClassSemesters;
        return this;
    }
}
