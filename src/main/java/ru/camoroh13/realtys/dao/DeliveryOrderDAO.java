package ru.camoroh13.realtys.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.camoroh13.realtys.domain.DeliveryImage;
import ru.camoroh13.realtys.domain.DeliveryOrder;

import java.util.List;

/**
 * User: Konstantin
 * Date: 01.08.11
 */
public interface DeliveryOrderDAO {
    
    @Transactional
    public DeliveryOrder get(Integer id);

    @Transactional
    public void add(DeliveryOrder deliveryOrder);

    @Transactional
    public void save(DeliveryOrder deliveryOrder);

    @Transactional
    public void save(DeliveryImage image);

    @Transactional
    public void delete(DeliveryOrder deliveryOrder);

    @Transactional
    public List<DeliveryOrder> list();    
    
}
