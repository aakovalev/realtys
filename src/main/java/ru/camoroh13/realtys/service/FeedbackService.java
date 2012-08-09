package ru.camoroh13.realtys.service;

import ru.camoroh13.realtys.domain.Feedback;

import javax.mail.MessagingException;
import java.util.List;

/**
 * User: Konstantin
 * Date: 21.08.11
 * Time: 13:39
 */
public interface FeedbackService {

    public void send(Feedback feedback) throws MessagingException;

    public void sendMessage(String topic, String text) throws MessagingException;

    public void save(Feedback feedback);

    public List<Feedback> findAll();

    public void delete(int feedbackId);
}
