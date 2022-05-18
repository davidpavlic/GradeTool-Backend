package com.accenture.gradetool.domain.email;

public interface EmailService {

    CollectionOperationResult<EmailRecipient> sendMail(EmailDetails emailDetails, String subject);

}
