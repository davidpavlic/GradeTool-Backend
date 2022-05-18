package com.accenture.gradetool.domain.schoolclasssemestersubject;

import com.accenture.gradetool.core.error.NotUniqueException;
import com.accenture.gradetool.core.generic.AbstractEntityServiceImpl;
import com.accenture.gradetool.domain.schoolclasssemester.SchoolClassSemesterService;
import com.accenture.gradetool.domain.subject.SubjectService;
import com.accenture.gradetool.domain.schoolclasssemester.SchoolClassSemester;
import com.accenture.gradetool.domain.subject.Subject;
import java.util.Collection;
import java.util.NoSuchElementException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolClassSemesterSubjectServiceImpl extends
    AbstractEntityServiceImpl<SchoolClassSemesterSubject, SchoolClassSemesterSubjectRepository> implements
    SchoolClassSemesterSubjectService {

    private final SubjectService subjectService;
    private final SchoolClassSemesterService schoolClassSemesterService;

    @Autowired
    public SchoolClassSemesterSubjectServiceImpl(Logger logger, SchoolClassSemesterSubjectRepository repository,
        SubjectService subjectService, SchoolClassSemesterService schoolClassSemesterService) {
        super(logger, repository);
        this.subjectService = subjectService;
        this.schoolClassSemesterService = schoolClassSemesterService;
    }

    @Override
    public SchoolClassSemesterSubject find(String subjectId) {
        return repository.findBySubjectId(subjectId);
    }

    @Override
    public SchoolClassSemesterSubject find(String schoolClassId, String semesterId, String subjectId) throws NoSuchElementException {
        SchoolClassSemester schoolClassSemester = schoolClassSemesterService.find(schoolClassId, semesterId);

        return repository.findBySubjectIdAndSchoolClassSemesterId(subjectId, schoolClassSemester.getId()).orElseThrow();
    }

    @Override
    public SchoolClassSemesterSubject addSubjectToSchoolClassSemester(String subjectId, String schoolClassId, String semesterId)
        throws NoSuchElementException {
        Subject subject = subjectService.findById(subjectId);
        SchoolClassSemester schoolClassSemester = schoolClassSemesterService.find(schoolClassId, semesterId);

        if (!repository.existsBySubjectIdAndSchoolClassSemesterId(subjectId, schoolClassSemester.getId())) {
            return save(new SchoolClassSemesterSubject().setSubject(subject).setSchoolClassSemester(schoolClassSemester));
        } else {
            throw new NotUniqueException("Subject is already in SchoolClassSemester");
        }
    }

    @Override
    public void removeSubjectFromSchoolClassSemester(String subjectId, String schoolClassId, String semesterId)
        throws NoSuchElementException {
        SchoolClassSemesterSubject schoolClassSemesterSubject = findBySubjectIdAndSchoolClassIdAndSemesterId(subjectId, schoolClassId,
            semesterId);

        schoolClassSemesterSubject.getSchoolClassSemester().getSchoolClassSemesterSubjects().remove(schoolClassSemesterSubject);
        schoolClassSemesterService.save(schoolClassSemesterSubject.getSchoolClassSemester());
        schoolClassSemesterSubject.setSchoolClassSemester(null);

        repository.delete(schoolClassSemesterSubject);
    }

    private SchoolClassSemesterSubject findBySubjectIdAndSchoolClassIdAndSemesterId(String subjectId, String schoolClassId,
        String semesterId)
        throws NoSuchElementException {
        SchoolClassSemester schoolClassSemester = schoolClassSemesterService.find(schoolClassId, semesterId);

        return repository.findBySubjectIdAndSchoolClassSemesterId(subjectId, schoolClassSemester.getId()).orElseThrow();
    }
}
