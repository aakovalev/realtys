package ru.camoroh13.realtys.service;

import ru.camoroh13.realtys.domain.EstateType;

import java.util.List;

/**
 * User: Konstantin
 * Date: 27.06.11
 * Time: 20:59
 * To change this template use File | Settings | File Templates.
 */
public interface EstateTypeService {
    
    public EstateType get(Integer id);

    public void add(EstateType estateType);

    public void save(EstateType estateType);

    public void delete(EstateType estateType);

    public List<EstateType> list();    
    
}
