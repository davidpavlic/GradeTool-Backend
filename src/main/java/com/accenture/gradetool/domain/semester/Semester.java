package com.accenture.gradetool.domain.semester;

import com.accenture.gradetool.core.generic.AbstractEntity;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "semester")
public class Semester extends AbstractEntity {

    @Column(name = "start_date")
    private LocalDate startDate;

    @Override
    public Semester setId(String id) {
        this.id = id;
        return this;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Semester setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }
}
