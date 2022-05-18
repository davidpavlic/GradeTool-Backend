package com.accenture.gradetool.domain.email;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;

@Configuration
@Service
public class EmailServiceImpl implements EmailService {

    private final Logger logger;
    private final MailProperties mailProperties;
    private final Properties smtpProperties;

    @Autowired
    public EmailServiceImpl(Logger logger, MailProperties mailProperties) {
        this.logger = logger;
        this.mailProperties = mailProperties;

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", mailProperties.getHost());
        properties.setProperty("mail.smtp.port", "" + mailProperties.getPort());
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");

        this.smtpProperties = properties;
    }


    @Override
    public CollectionOperationResult<EmailRecipient> sendMail(EmailDetails emailDetails, String subject) {

        CollectionOperationResult<EmailRecipient> operationResult = new CollectionOperationResult<>();

        try {
            Session session = Session.getDefaultInstance(smtpProperties);

            Transport transport = session.getTransport("smtp");

            transport.connect(mailProperties.getHost(), emailDetails.getEmail(), emailDetails.getPassword());

            for (EmailRecipient emailRecipient : emailDetails.getRecipients()) {

                try {

                    Message mimeMessage = new MimeMessage(session);
                    InternetAddress[] address = {new InternetAddress(emailRecipient.getEmail())};

                    mimeMessage.setRecipients(Message.RecipientType.TO, address);

                    mimeMessage.setFrom(
                        new InternetAddress(emailDetails.getEmail(), emailDetails.getFirstName() + " " + emailDetails.getLastName()));

                    mimeMessage.setSubject(subject);

                    mimeMessage.setContent(createHTMLBody(emailDetails, emailRecipient.getFirstName()), "text/html");

                    mimeMessage.saveChanges();
                    transport.sendMessage(mimeMessage, address);

                    operationResult.addSuccess(emailRecipient);

                } catch (MailParseException | MessagingException | MailSendException e) {
                    logger.error("Unable to send mails to {}", emailRecipient.getEmail());
                    logger.error("{}", e.toString());
                    operationResult.addError(new ResultError<>(emailRecipient));
                } catch (UnsupportedEncodingException e) {
                    logger.error("Name couldn't be added to mail address");
                    operationResult.addError(new ResultError<>(emailRecipient));
                }
            }

            transport.close();

        } catch (NoSuchProviderException e) {
            logger.error("Session error: {}", e.toString());
            emailDetails.getRecipients().forEach(emailRecipient -> operationResult.addError(new ResultError<>(emailRecipient)));
        } catch (MessagingException e) {
            logger.error("Error sending mail: {}", e.toString());
            emailDetails.getRecipients().forEach(emailRecipient -> operationResult.addError(new ResultError<>(emailRecipient)));

        }

        return operationResult;
    }

    public String createHTMLBody(EmailDetails emailDetails, String superVisorFirstName) {
        StringBuilder htmlStringBuilder = new StringBuilder();
        Scanner htmlBody = new Scanner(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("html/mail.html")),
            StandardCharsets.UTF_8);

        while (htmlBody.hasNextLine()) {
            htmlStringBuilder.append(htmlBody.nextLine()).append("\n");
        }

        String htmlBodyString = htmlStringBuilder.toString();

        htmlBodyString = htmlBodyString.replaceAll("SUPER_VISOR_FIRST_NAME", superVisorFirstName);
        htmlBodyString = htmlBodyString.replaceAll("SCHOOL_CLASS_NAME", emailDetails.getSchoolClass());
        htmlBodyString = htmlBodyString.replaceAll("SCHOOL_CURRENT_SUBJECT", emailDetails.getSubject());
        htmlBodyString = htmlBodyString.replaceAll("SCHOOL_CURRENT_GRADE_MARK", String.valueOf(emailDetails.getMark()));
        htmlBodyString = htmlBodyString
            .replaceAll("SCHOOL_CURRENT_GRADE_WEIGHT", String.valueOf(emailDetails.getWeight()));
        htmlBodyString = htmlBodyString.replaceAll("SCHOOL_CURRENT_GRADE_NAME", emailDetails.getExam());
        htmlBodyString = htmlBodyString.replaceAll("CURRENT_USER_FIRST_NAME", emailDetails.getFirstName());
        htmlBodyString = htmlBodyString.replaceAll("SCHOOL_CURRENT_COMMENT", emailDetails.getComment());

        return htmlBodyString;
    }

}
