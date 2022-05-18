package com.accenture.gradetool.domain.grade;

import com.accenture.gradetool.core.generic.AbstractEntityRepository;
import com.accenture.gradetool.domain.exam.Exam;

import java.util.Collection;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends AbstractEntityRepository<Grade> {

    boolean existsByExamIdAndStudentIdAndDeletedFalse(String examId, String studentId);

    boolean existsByStudentIdAndIdAndDeletedFalse(String studentId, String id);

    Optional<Grade> findByExamAndIdAndDeletedFalse(Exam exam, String id);

    Collection<Grade> findAllByStudentId(String studentId);

}
