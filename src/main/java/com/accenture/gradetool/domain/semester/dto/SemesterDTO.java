package com.accenture.gradetool.domain.semester.dto;

import com.accenture.gradetool.core.generic.dto.DTO;
import com.accenture.gradetool.domain.subject.dto.SubjectDTO;
import java.util.Collection;
import java.util.HashSet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SemesterDTO extends DTO {

    protected String startDate;

    @Getter
    @Setter
    public static class WithSubjects extends SemesterDTO {

        protected Collection<? extends SubjectDTO> subjects = new HashSet<>();
    }

}
