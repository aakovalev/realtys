package ru.camoroh13.realtys.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.camoroh13.realtys.domain.EvaluationImage;
import ru.camoroh13.realtys.domain.EvaluationOrder;
import ru.camoroh13.realtys.domain.RentImage;

import java.util.List;

/**
 * User: Konstantin
 * Date: 01.08.11
 */
public interface EvaluationOrderDAO {
    
    @Transactional
    public EvaluationOrder get(Integer id);

    @Transactional
    public void add(EvaluationOrder evaluationOrder);

    @Transactional
    public void save(EvaluationOrder evaluationOrder);

    @Transactional
    public void save(EvaluationImage image);

    @Transactional
    public void delete(EvaluationOrder evaluationOrder);

    @Transactional
    public List<EvaluationOrder> list();       
    
}
