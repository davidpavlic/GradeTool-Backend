package com.accenture.gradetool.domain.semester;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SemesterAutoGenerator implements CommandLineRunner {

    private final SemesterGenerationProperties properties;
    private final SemesterService semesterService;
    private final Logger logger;

    @Autowired
    public SemesterAutoGenerator(SemesterGenerationProperties properties, SemesterService semesterService, Logger logger) {
        this.properties = properties;
        this.semesterService = semesterService;
        this.logger = logger;
    }

    @Override
    public void run(String... args) {

        logger.debug("Attempting to generate Semesters up to {} years in advance and {} years before", properties.getYearsInAdvance(),
            properties.getYearsBefore());

        int currentYear = LocalDate.now().getYear();

        Collection<Semester> semestersToGenerate = new HashSet<>();

        for (int i = 0; i <= properties.getYearsInAdvance(); i++) {
            if (i != 0) {
                semestersToGenerate.add(new Semester().setStartDate(LocalDate.of(currentYear + i, 2, 1)));
            }

            if (i != properties.getYearsInAdvance()) {
                semestersToGenerate.add(new Semester().setStartDate(LocalDate.of(currentYear + i, 8, 1)));
            }
        }

        for (int i = 0; i <= properties.getYearsBefore(); i++) {
            if (i != properties.getYearsBefore()) {
                semestersToGenerate.add(new Semester().setStartDate(LocalDate.of(currentYear - i, 2, 1)));
            }

            if (i != 0) {
                semestersToGenerate.add(new Semester().setStartDate(LocalDate.of(currentYear - i, 8, 1)));
            }
        }

        semesterService.createAll(semestersToGenerate);

        logger.debug("Generated Semesters");
    }
}
