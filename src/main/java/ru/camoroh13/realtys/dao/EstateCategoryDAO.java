package ru.camoroh13.realtys.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.camoroh13.realtys.domain.EstateCategory;

import java.util.List;

/**
 * User: Konstantin
 * Date: 27.06.11
 * Time: 20:36
 * To change this template use File | Settings | File Templates.
 */
public interface EstateCategoryDAO {

    @Transactional
    public EstateCategory get(Integer id);

    @Transactional
    public void add(EstateCategory estateCategory);

    @Transactional
    public void save(EstateCategory estateCategory);

    @Transactional
    public void delete(EstateCategory estateCategory);

    @Transactional
    public List<EstateCategory> list();

}
