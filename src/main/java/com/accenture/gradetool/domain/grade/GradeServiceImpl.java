package com.accenture.gradetool.domain.grade;

import com.accenture.gradetool.core.error.NotUniqueException;
import com.accenture.gradetool.core.generic.AbstractEntityServiceImpl;
import com.accenture.gradetool.domain.exam.ExamService;
import com.accenture.gradetool.domain.exam.Exam;
import com.accenture.gradetool.domain.student.Student;
import com.accenture.gradetool.domain.student.StudentService;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service("gradeService")
public class GradeServiceImpl extends AbstractEntityServiceImpl<Grade, GradeRepository> implements GradeService {

    private final ExamService examService;
    private final StudentService studentService;

    public GradeServiceImpl(Logger logger, GradeRepository repository, ExamService examService, StudentService studentService) {
        super(logger, repository);
        this.examService = examService;
        this.studentService = studentService;
    }

    @Override
    public Grade find(String schoolClassId, String semesterId, String subjectId, String examId, String gradeId) {
        Exam exam = examService.find(schoolClassId, semesterId, subjectId, examId);

        return find(exam, gradeId);
    }

    @Override
    public Grade find(Exam exam, String gradeId) {
        return repository.findByExamAndIdAndDeletedFalse(exam, gradeId).orElseThrow();
    }

    @Override
    public Grade findById(String gradeId) {
        return repository.findById(gradeId).orElseThrow();
    }

    @Override
    public Collection<Grade> findAllByStudentId(String studentId){
        return repository.findAllByStudentId(studentId);
    }

    @Override
    public void delete(String gradeId) {
        repository.deleteById(gradeId);
    }

    @Override
    public Grade create(String schoolClassId, String semesterId, String subjectId, String examId, Grade grade, Student student) {
        Exam exam = examService.find(schoolClassId, semesterId, subjectId, examId);

        if (!repository.existsByExamIdAndStudentIdAndDeletedFalse(examId, student.getId())) {
            return create(grade.setExam(exam).setStudent(student));
        } else {
            throw new NotUniqueException("Grade for Exam is already set");
        }
    }

    @Override
    public Grade createByPath(double mark, String examId, String studentId) {
        Grade grade = new Grade();
        grade.setMark(mark);
        grade.setExam(examService.findById(examId));
        grade.setStudent(studentService.findById(studentId));

        return repository.save(grade);
    }

    @Override
    public Grade update(String gradeId, double mark) {
        Grade old = findById(gradeId);

        old.setMark(mark);

        return save(old);
    }

    @Override
    public boolean studentOwnsGrade(Student student, String gradeId) {
        return repository.existsByStudentIdAndIdAndDeletedFalse(student.getId(), gradeId);
    }
}
