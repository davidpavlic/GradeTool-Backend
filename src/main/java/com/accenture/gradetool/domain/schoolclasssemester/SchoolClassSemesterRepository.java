package com.accenture.gradetool.domain.schoolclasssemester;

import com.accenture.gradetool.core.generic.AbstractEntityRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolClassSemesterRepository extends AbstractEntityRepository<SchoolClassSemester> {

    Optional<SchoolClassSemester> findBySchoolClassIdAndSemesterId(String schoolClassId, String semesterId);

}
