package ru.camoroh13.realtys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.camoroh13.realtys.dao.EstateTypeDAO;
import ru.camoroh13.realtys.domain.EstateType;
import ru.camoroh13.realtys.service.EstateTypeService;

import java.util.List;

/**
 * User: Konstantin
 * Date: 27.06.11
 * Time: 21:04
 * To change this template use File | Settings | File Templates.
 */
@Service
public class EstateTypeServiceImpl implements EstateTypeService {

    @Autowired
    EstateTypeDAO estateTypeDAO;

    @Override
    public EstateType get(Integer id) {
        return estateTypeDAO.get(id);
    }

    @Override
    public void add(EstateType estateType) {
        estateTypeDAO.add(estateType);
    }

    @Override
    public void save(EstateType estateType) {
        estateTypeDAO.save(estateType);
    }

    @Override
    public void delete(EstateType estateType) {
        estateTypeDAO.delete(estateType);
    }

    @Override
    public List<EstateType> list() {
        return estateTypeDAO.list();
    }
}
