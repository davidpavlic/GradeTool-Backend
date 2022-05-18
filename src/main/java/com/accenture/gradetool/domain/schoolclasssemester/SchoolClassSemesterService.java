package com.accenture.gradetool.domain.schoolclasssemester;

import com.accenture.gradetool.core.generic.AbstractEntityService;
import java.util.NoSuchElementException;

public interface SchoolClassSemesterService extends AbstractEntityService<SchoolClassSemester> {

    SchoolClassSemester find(String schoolClassId, String semesterId) throws NoSuchElementException;

}
