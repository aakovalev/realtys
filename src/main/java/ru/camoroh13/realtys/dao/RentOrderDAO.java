package ru.camoroh13.realtys.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.camoroh13.realtys.domain.RentImage;
import ru.camoroh13.realtys.domain.RentOrder;

import java.util.List;

/**
 * User: Konstantin
 * Date: 01.08.11
 */
public interface RentOrderDAO {
    
    @Transactional
    public RentOrder get(Integer id);

    @Transactional
    public void add(RentOrder deliveryOrder);

    @Transactional
    public void save(RentOrder deliveryOrder);

    @Transactional
    public void save(RentImage image);

    @Transactional
    public void delete(RentOrder rentOrder);

    @Transactional
    public List<RentOrder> list();       
    
}
