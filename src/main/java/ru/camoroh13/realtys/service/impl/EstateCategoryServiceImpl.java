package ru.camoroh13.realtys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.camoroh13.realtys.dao.EstateCategoryDAO;
import ru.camoroh13.realtys.domain.EstateCategory;
import ru.camoroh13.realtys.service.EstateCategoryService;

import java.util.LinkedList;
import java.util.List;

/**
 * User: Konstantin
 * Date: 27.06.11
 * Time: 21:03
 * To change this template use File | Settings | File Templates.
 */
@Service
public class EstateCategoryServiceImpl implements EstateCategoryService {

    @Autowired
    EstateCategoryDAO estateCategoryDAO;

    @Override
    public EstateCategory get(Integer id) {
        return estateCategoryDAO.get(id);
    }

    @Override
    public void add(EstateCategory estateCategory) {
        estateCategoryDAO.add(estateCategory);
    }

    @Override
    public void save(EstateCategory estateCategory) {
        estateCategoryDAO.save(estateCategory);
    }

    @Override
    public void delete(EstateCategory estateCategory) {
        estateCategoryDAO.delete(estateCategory);
    }

    @Override
    public List<EstateCategory> list() {
        List<EstateCategory> estateCategoryList = estateCategoryDAO.list();
        if (estateCategoryList == null) {
            estateCategoryList = new LinkedList<EstateCategory>();
        }
        return estateCategoryList;
    }
}
