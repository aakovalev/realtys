package ru.camoroh13.realtys.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.camoroh13.realtys.domain.EstateType;

import java.util.List;

/**
 * User: Konstantin
 * Date: 27.06.11
 * Time: 20:30
 * To change this template use File | Settings | File Templates.
 */
public interface EstateTypeDAO {

    @Transactional
    public EstateType get(Integer id);

    @Transactional
    public void add(EstateType estate);

    @Transactional
    public void save(EstateType estate);

    @Transactional
    public void delete(EstateType estate);

    @Transactional
    public List<EstateType> list();

}
