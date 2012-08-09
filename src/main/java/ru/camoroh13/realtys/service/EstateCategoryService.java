package ru.camoroh13.realtys.service;

import ru.camoroh13.realtys.domain.EstateCategory;

import java.util.List;

/**
 * User: Konstantin
 * Date: 27.06.11
 * Time: 20:59
 * To change this template use File | Settings | File Templates.
 */
public interface EstateCategoryService {
    
    public EstateCategory get(Integer id);

    public void add(EstateCategory estateCategory);

    public void save(EstateCategory estateCategory);

    public void delete(EstateCategory estateCategory);

    public List<EstateCategory> list();
    
}
