package com.accenture.gradetool.domain.schoolclasssemestersubject;

import com.accenture.gradetool.core.generic.AbstractEntityRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolClassSemesterSubjectRepository extends AbstractEntityRepository<SchoolClassSemesterSubject> {

    boolean existsBySubjectIdAndSchoolClassSemesterId(String subjectId, String schoolClassSemesterId);

    Optional<SchoolClassSemesterSubject> findBySubjectIdAndSchoolClassSemesterId(String subjectId, String schoolClassSemesterId);

    SchoolClassSemesterSubject findBySubjectId(String subjectId);

}
