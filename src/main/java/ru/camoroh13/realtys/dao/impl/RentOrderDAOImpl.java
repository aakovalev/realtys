package ru.camoroh13.realtys.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ru.camoroh13.realtys.dao.RentOrderDAO;
import ru.camoroh13.realtys.domain.RentImage;
import ru.camoroh13.realtys.domain.RentOrder;

import java.util.List;

/**
 * User: Konstantin
 * Date: 01.08.11
 */
@Repository
public class RentOrderDAOImpl implements RentOrderDAO{

    @Autowired
    private HibernateTemplate template;

    @Override
    public RentOrder get(Integer id) {
        RentOrder order = template.load(
		    RentOrder.class, id);
        order.getRentOrderId();
        return order;
    }

    @Override
    public void add(RentOrder rentOrder) {
        template.save(rentOrder);
    }

    @Override
    public void save(RentOrder rentOrder) {
        template.saveOrUpdate(rentOrder);
    }

    @Override
    public void save(RentImage image) {
        template.save(image);
    }

    @Override
    public void delete(RentOrder rentOrder) {
        template.delete(rentOrder);
    }

    @Override
    public List<RentOrder> list() {
        return template.find("from RentOrder ");
    }    
    
}
