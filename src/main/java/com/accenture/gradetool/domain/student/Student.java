package com.accenture.gradetool.domain.student;

import com.accenture.gradetool.core.generic.AbstractEntity;
import com.accenture.gradetool.core.generic.ParentEntity;
import com.accenture.gradetool.domain.studentschoolclass.StudentSchoolClass;
import com.accenture.gradetool.domain.supervisor.Supervisor;
import com.accenture.gradetool.domain.subject.user.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student extends User implements ParentEntity {

    @Column(name = "first_name")
    private String firstName;

    @OneToMany(mappedBy = "student")
    private Set<StudentSchoolClass> studentSchoolClasses = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "student_id")
    private List<Supervisor> supervisors = new ArrayList<>();

    @Override
    public Set<Supplier<Collection<? extends AbstractEntity>>> getChildrenSuppliers() {
        return Set.of(this::getStudentSchoolClasses, this::getSupervisors);
    }

    @Override
    public Student setId(String id) {
        this.id = id;
        return this;
    }

    public Student setEmail(String email) {
        this.email = email;
        return this;
    }

    public Student setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Student setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Set<StudentSchoolClass> getStudentSchoolClasses() {
        return studentSchoolClasses;
    }

    public Student setStudentSchoolClasses(Set<StudentSchoolClass> studentSchoolClasses) {
        this.studentSchoolClasses = studentSchoolClasses;
        return this;
    }

    public List<Supervisor> getSupervisors() {
        return supervisors;
    }

    public Student setSupervisors(List<Supervisor> supervisors) {
        this.supervisors = supervisors;
        return this;
    }
}
