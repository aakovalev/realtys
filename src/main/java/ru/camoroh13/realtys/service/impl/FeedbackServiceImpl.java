package ru.camoroh13.realtys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.camoroh13.realtys.dao.FeedbackDAO;
import ru.camoroh13.realtys.domain.Feedback;
import ru.camoroh13.realtys.service.FeedbackService;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.List;

/**
 * User: Konstantin
 * Date: 21.08.11
 * Time: 13:40
 */
@Service
public class FeedbackServiceImpl implements FeedbackService{

    @Autowired
    private FeedbackDAO feedbackDao;

    private static final String SMTP_HOST = "mail.parasamara.ru";
    private static final Integer SMTP_PORT = 25;
    private static final String FROM = "mailer@parasamara.ru";
    private static final String TO = "info@parasamara.ru";
    private static final String SUBJECT = "parasamara.ru";
    private static final String USER = "mailer@parasamara.ru";
    private static final String PASSWORD = "sOa0caL2";

    @Override
    public void send(Feedback feedback) throws MessagingException {

        String text = makeMessage(feedback);
        sendMessage(SUBJECT, text);
    }

    @Override
    public void save(Feedback feedback) {
        feedbackDao.save(feedback);
    }

    @Override
    public List<Feedback> findAll() {
        return feedbackDao.findAll();
    }

    @Override
    public void delete(int feedbackId) {
        Feedback feedback = feedbackDao.findById(feedbackId);
        feedbackDao.delete(feedback);
    }

    private String makeMessage(Feedback feedback) {
        return "Имя: " + feedback.getName() + "\n" +
                        "Почта: " + feedback.getEmail() + "\n" +
                        "Телефон: " + feedback.getPhone() + "\n" +
                        feedback.getText();
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
           return new PasswordAuthentication(USER, PASSWORD);
        }
    }

    @Override
    public void sendMessage(String topic, String text) throws MessagingException {

        java.util.Properties props = System.getProperties();
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", "" + SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.user", USER);
        props.put("mail.smtp.password", PASSWORD);
        props.put("mail.debug", "1");

        Authenticator auth = new SMTPAuthenticator();
        Session session = Session.getDefaultInstance (props, auth);
        // Construct the message
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
        msg.setSubject(topic);
        msg.setText(text, "UTF-8");

        Transport transport = session.getTransport("smtp");
        transport.connect(SMTP_HOST, SMTP_PORT, USER, PASSWORD);

        transport.send(msg);
    }

}
