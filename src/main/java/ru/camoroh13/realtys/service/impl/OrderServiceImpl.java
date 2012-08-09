package ru.camoroh13.realtys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.camoroh13.realtys.dao.DeliveryOrderDAO;
import ru.camoroh13.realtys.dao.EvaluationOrderDAO;
import ru.camoroh13.realtys.dao.RentOrderDAO;
import ru.camoroh13.realtys.domain.*;
import ru.camoroh13.realtys.service.OrderServices;

import java.util.List;

/**
 * User: Konstantin
 * Date: 01.08.11
 */
@Service
public class OrderServiceImpl implements OrderServices {

    @Autowired
    RentOrderDAO rentOrderDAO;

    @Autowired
    EvaluationOrderDAO evaluationOrderDAO;

    @Autowired
    DeliveryOrderDAO deliveryOrderDAO;

    @Override
    public EvaluationOrder getEvaluation(Integer id) {
        return evaluationOrderDAO.get(id);
    }

    @Override
    public void add(EvaluationOrder order) {
        evaluationOrderDAO.add(order);
    }

    @Override
    public void save(EvaluationOrder order) {
        evaluationOrderDAO.save(order);
    }

    @Override
    public void delete(EvaluationOrder order) {
        evaluationOrderDAO.delete(order);
    }

    @Override
    public List<EvaluationOrder> listEvaluation() {
        return evaluationOrderDAO.list();
    }

    @Override
    public RentOrder getRent(Integer id) {
        return rentOrderDAO.get(id);
    }

    @Override
    public void add(RentOrder order) {
        rentOrderDAO.add(order);
    }

    @Override
    public void save(RentOrder order) {
        rentOrderDAO.save(order);
    }

    @Override
    public void delete(RentOrder order) {
        rentOrderDAO.delete(order);
    }

    @Override
    public List<RentOrder> listRent() {
        return rentOrderDAO.list();
    }

    @Override
    public DeliveryOrder getDelivery(Integer id) {
        return deliveryOrderDAO.get(id);
    }

    @Override
    public void add(DeliveryOrder order) {
        deliveryOrderDAO.add(order);
    }

    @Override
    public void save(DeliveryOrder order) {
        deliveryOrderDAO.save(order);
    }

    @Override
    public void delete(DeliveryOrder order) {
        deliveryOrderDAO.delete(order);
    }

    @Override
    public List<DeliveryOrder> listDelivery() {
        return deliveryOrderDAO.list();
    }

    @Override
    public void save(DeliveryImage image) {
        deliveryOrderDAO.save(image);
    }

    @Override
    public void save(EvaluationImage image) {
        evaluationOrderDAO.save(image);
    }

    @Override
    public void save(RentImage image) {
        rentOrderDAO.save(image);
    }
}
