package com.accenture.gradetool.domain.exam;

import com.accenture.gradetool.core.generic.AbstractEntityServiceImpl;
import com.accenture.gradetool.domain.schoolclasssemestersubject.SchoolClassSemesterSubjectService;
import com.accenture.gradetool.domain.schoolclasssemestersubject.SchoolClassSemesterSubject;
import java.util.Collection;
import org.slf4j.Logger;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl extends AbstractEntityServiceImpl<Exam, ExamRepository> implements ExamService {

    private final SchoolClassSemesterSubjectService schoolClassSemesterSubjectService;

    public ExamServiceImpl(Logger logger, ExamRepository repository,
        SchoolClassSemesterSubjectService schoolClassSemesterSubjectService) {
        super(logger, repository);
        this.schoolClassSemesterSubjectService = schoolClassSemesterSubjectService;
    }

    @Override
    public Exam find(String schoolClassId, String semesterId, String subjectId, String examId) {
        SchoolClassSemesterSubject schoolClassSemesterSubject = schoolClassSemesterSubjectService
            .find(schoolClassId, semesterId, subjectId);

        return find(schoolClassSemesterSubject.getId(), examId);
    }

    @Override
    public Exam find(String schoolClassSemesterSubjectId, String examId) {
        return repository.findByIdAndSchoolClassSemesterSubjectIdAndDeletedFalse(examId, schoolClassSemesterSubjectId).orElseThrow();
    }

    @Override
    public Collection<Exam> findAll() {
        return repository.findAll();
    }

    @Override
    public Collection<Exam> findAll(String schoolClassSemesterSubjectId) {
        return repository.findAllBySchoolClassSemesterSubjectId(schoolClassSemesterSubjectId).orElseThrow();
    }

    @Override
    public Exam findById(String examId){
        return repository.findById(examId).orElseThrow();
    }

    @Override
    public Exam create(String schoolClassId, String semesterId, String subjectId, Exam exam) {
        SchoolClassSemesterSubject schoolClassSemesterSubject = schoolClassSemesterSubjectService
            .find(schoolClassId, semesterId, subjectId);

        return create(exam.setSchoolClassSemesterSubject(schoolClassSemesterSubject));
    }

    @Override
    public Exam createByDTO(Exam exam) {
        return repository.save(exam);
    }

    @Override
    public Exam update(Exam exam) {
        Exam old = findById(exam.getId());

        old.setWeight(exam.getWeight());
        old.setName(exam.getName());

        return save(old);
    }

    @Override
    public void delete(String examId) {
        repository.deleteById(examId);
    }
}
