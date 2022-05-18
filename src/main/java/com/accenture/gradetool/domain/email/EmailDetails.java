package com.accenture.gradetool.domain.email;

import java.util.List;

public interface EmailDetails {

    List<? extends EmailRecipient> getRecipients();

    String getPassword();

    String getEmail();

    String getFirstName();

    String getLastName();

    String getComment();

    String getSchoolClass();

    String getSubject();

    String getExam();

    double getWeight();

    double getMark();

}
