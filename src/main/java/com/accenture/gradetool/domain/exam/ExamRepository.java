package com.accenture.gradetool.domain.exam;

import com.accenture.gradetool.core.generic.AbstractEntityRepository;
import java.util.Optional;
import java.util.Collection;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends AbstractEntityRepository<Exam> {

    Optional<Exam> findByIdAndSchoolClassSemesterSubjectIdAndDeletedFalse(String id, String schoolClassSemesterSubjectId);

    Optional<Collection<Exam>> findAllBySchoolClassSemesterSubjectId(String id);

}
