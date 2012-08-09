package ru.camoroh13.realtys.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ru.camoroh13.realtys.dao.DeliveryOrderDAO;
import ru.camoroh13.realtys.domain.DeliveryImage;
import ru.camoroh13.realtys.domain.DeliveryOrder;

import java.util.List;

/**
 * User: Konstantin
 * Date: 01.08.11
 */
@Repository
public class DelivaeryOrderDAOImpl implements DeliveryOrderDAO{

    @Autowired
    private HibernateTemplate template;

    @Override
    public DeliveryOrder get(Integer id) {
        DeliveryOrder order = template.load(
		    DeliveryOrder.class, id);
        order.getDeliveryOrderId();
        return order;
    }

    @Override
    public void add(DeliveryOrder deliveryOrder) {
        template.save(deliveryOrder);
        template.flush();
    }

    @Override
    public void save(DeliveryOrder deliveryOrder) {
        template.saveOrUpdate(deliveryOrder);
    }

    @Override
    public void save(DeliveryImage image) {
        template.save(image);
    }

    @Override
    public void delete(DeliveryOrder deliveryOrder) {
        template.delete(deliveryOrder);
    }

    @Override
    public List<DeliveryOrder> list() {
        return template.find("from DeliveryOrder ");
    }    
    
}
