package ru.camoroh13.realtys.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ru.camoroh13.realtys.dao.EstateTypeDAO;
import ru.camoroh13.realtys.domain.EstateType;

import java.util.List;

/**
 * User: Konstantin
 * Date: 27.06.11
 * Time: 20:31
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class EstateTypeDAOImpl implements EstateTypeDAO {

    @Autowired
    private HibernateTemplate template;

    @Override
    public EstateType get(Integer id) {
        EstateType estateType = template.load(
		    EstateType.class, id);
        estateType.getEstateTypeId();
        return estateType;
    }

    @Override
    public void add(EstateType estateType) {
        template.save(estateType);
    }

    @Override
    public void save(EstateType estateType) {
        template.update(estateType);
    }

    @Override
    public void delete(EstateType estateType) {
        template.delete(estateType);
    }

    @Override
    public List<EstateType> list() {
        List<EstateType> estateTypes = template.find("from EstateType");
        return estateTypes;
    }
}
