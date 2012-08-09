package ru.camoroh13.realtys.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ru.camoroh13.realtys.dao.EstateCategoryDAO;
import ru.camoroh13.realtys.domain.EstateCategory;

import java.util.List;

/**
 * User: Konstantin
 * Date: 27.06.11
 * Time: 20:37
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class EstateCategoryDAOImpl implements EstateCategoryDAO {

    @Autowired
    private HibernateTemplate template;

    @Override
    public EstateCategory get(Integer id) {
        EstateCategory estateCategory = template.load(
		    EstateCategory.class, id);
        estateCategory.getEstateCategoryId();
        return estateCategory;
    }

    @Override
    public void add(EstateCategory estateCategory) {
        template.save(estateCategory);
    }

    @Override
    public void save(EstateCategory estateCategory) {
        template.update(estateCategory);
    }

    @Override
    public void delete(EstateCategory estateCategory) {
        template.delete(estateCategory);
    }

    @Override
    public List<EstateCategory> list() {
        List<EstateCategory> estateCategoryes = template.find("from  EstateCategory");
        return estateCategoryes;
    }
}
