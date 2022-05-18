package com.accenture.gradetool.domain.schoolclass;

import com.accenture.gradetool.core.generic.AbstractEntityServiceImpl;
import com.accenture.gradetool.domain.semester.SemesterService;
import com.accenture.gradetool.domain.schoolclasssemester.SchoolClassSemester;
import com.accenture.gradetool.domain.semester.Semester;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolClassServiceImpl extends AbstractEntityServiceImpl<SchoolClass, SchoolClassRepository> implements SchoolClassService {

    private final SemesterService semesterService;

    @Autowired
    public SchoolClassServiceImpl(Logger logger, SchoolClassRepository repository, SemesterService semesterService) {
        super(logger, repository);
        this.semesterService = semesterService;
    }

    @Override
    protected SchoolClass preCreate(SchoolClass schoolClass) {
        LocalDate startDate = LocalDate.of(schoolClass.getStartYear(), 8, 1);
        LocalDate endDate = LocalDate.of(schoolClass.getEndYear(), 2, 1);

        for (Semester semester : semesterService.findAllInRange(startDate, endDate)) {
            schoolClass.getSchoolClassSemesters().add(new SchoolClassSemester().setSchoolClass(schoolClass).setSemester(semester));
        }

        logger.debug("Automatically added semesters starting between {} and {} to {}", startDate, endDate, singleEntityName());

        return schoolClass;
    }
}
