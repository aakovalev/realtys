package ru.camoroh13.realtys.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ru.camoroh13.realtys.dao.EvaluationOrderDAO;
import ru.camoroh13.realtys.domain.EvaluationImage;
import ru.camoroh13.realtys.domain.EvaluationOrder;

import java.util.List;

/**
 * User: Konstantin
 * Date: 01.08.11
 */
@Repository
public class EvaluationOrderDAOImpl implements EvaluationOrderDAO{

    @Autowired
    private HibernateTemplate template;

    @Override
    public EvaluationOrder get(Integer id) {
        EvaluationOrder order = template.load(
		    EvaluationOrder.class, id);
        order.getEvaluationOrderId();
        return order;
    }

    @Override
    public void add(EvaluationOrder evaluationOrder) {
        template.save(evaluationOrder);
    }

    @Override
    public void save(EvaluationOrder evaluationOrder) {
        template.saveOrUpdate(evaluationOrder);
    }

    @Override
    public void save(EvaluationImage image) {
        template.save(image);
    }

    @Override
    public void delete(EvaluationOrder evaluationOrder) {
        template.delete(evaluationOrder);
    }

    @Override
    public List<EvaluationOrder> list() {
        return template.find("from EvaluationOrder ");
    }
}
