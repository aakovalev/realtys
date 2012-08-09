package ru.camoroh13.realtys.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ru.camoroh13.realtys.dao.FeedbackDAO;
import ru.camoroh13.realtys.domain.Feedback;

import java.util.List;

@Repository
public class FeedbackDAOImpl implements FeedbackDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public List<Feedback> findAll() {
        return hibernateTemplate.find("from Feedback order by feedbackId");
    }

    @Override
    public void save(Feedback feedback) {
        hibernateTemplate.save(feedback);
    }

    @Override
    public void delete(Feedback feedback) {
        hibernateTemplate.delete(feedback);
    }

    @Override
    public Feedback findById(int id) {
        return hibernateTemplate.get(Feedback.class, id);
    }
}
