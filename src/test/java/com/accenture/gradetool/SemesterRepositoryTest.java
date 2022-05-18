package com.accenture.gradetool;

import static org.assertj.core.api.Assertions.assertThat;

import com.accenture.gradetool.domain.semester.SemesterRepository;
import com.accenture.gradetool.domain.semester.Semester;
import java.time.LocalDate;
import java.util.Collection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yaml")
public class SemesterRepositoryTest {

    @Autowired
    private SemesterRepository semesterRepository;

    @Test
    public void findAllInRange_expect_inclusive() {

        LocalDate startDate = LocalDate.of(2018, 8, 1);
        LocalDate endDate = LocalDate.of(2022, 2, 1);

        Collection<Semester> semesters = semesterRepository.findAllInRange(startDate, endDate);

        assertThat(semesters).hasSize(8);

    }

}
