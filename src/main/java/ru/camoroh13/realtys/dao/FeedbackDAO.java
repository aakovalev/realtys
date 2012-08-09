package ru.camoroh13.realtys.dao;

import ru.camoroh13.realtys.domain.Feedback;

import java.util.List;

public interface FeedbackDAO {
    List<Feedback> findAll();

    public void save(Feedback feedback);

    void delete(Feedback feedback);

    public Feedback findById(int id);
}
