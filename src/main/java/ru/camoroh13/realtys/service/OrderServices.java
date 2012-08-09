package ru.camoroh13.realtys.service;

import ru.camoroh13.realtys.domain.*;

import java.util.List;

/**
 * User: Konstantin
 * Date: 01.08.11
 */
public interface OrderServices {
    
    public EvaluationOrder getEvaluation(Integer id);

    public void add(EvaluationOrder order);

    public void save(EvaluationOrder order);

    public void delete(EvaluationOrder order);

    public List<EvaluationOrder> listEvaluation();
    
    public RentOrder getRent(Integer id);

    public void add(RentOrder order);

    public void save(RentOrder order);

    public void delete(RentOrder order);

    public List<RentOrder> listRent();
    
    public DeliveryOrder getDelivery(Integer id);

    public void add(DeliveryOrder order);

    public void save(DeliveryOrder order);

    public void delete(DeliveryOrder order);

    public List<DeliveryOrder> listDelivery();    

    public void save(DeliveryImage image);

    public void save(EvaluationImage image);

    public void save(RentImage image);

}
