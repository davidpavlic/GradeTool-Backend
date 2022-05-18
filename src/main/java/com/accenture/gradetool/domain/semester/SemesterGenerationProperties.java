package com.accenture.gradetool.domain.semester;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("semester.generation")
public class SemesterGenerationProperties {

    private int yearsInAdvance = 4;
    private int yearsBefore = 4;

    public int getYearsInAdvance() {
        return yearsInAdvance;
    }

    public SemesterGenerationProperties setYearsInAdvance(int yearsInAdvance) {
        this.yearsInAdvance = yearsInAdvance;
        return this;
    }

    public int getYearsBefore() {
        return yearsBefore;
    }

    public SemesterGenerationProperties setYearsBefore(int yearsBefore) {
        this.yearsBefore = yearsBefore;
        return this;
    }
}
