package ru.camoroh13.realtys.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ru.camoroh13.realtys.dao.EstateDAO;
import ru.camoroh13.realtys.domain.District;
import ru.camoroh13.realtys.domain.Estate;
import ru.camoroh13.realtys.domain.EstateCategory;
import ru.camoroh13.realtys.domain.EstateType;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.hibernate.criterion.CriteriaSpecification.DISTINCT_ROOT_ENTITY;

/**
 * User: Konstantin
 * Date: 27.06.11
 * Time: 20:21
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class EstateDAOImpl implements EstateDAO {

    @Autowired
    private HibernateTemplate template;

    @Override
    public Estate get(Integer id) {
        Estate estate = template.load(
		    Estate.class, id);
        estate.getEstateId();
        return estate;
    }

    @Override
    public Estate getByCode(String code) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Estate.class)
            .add(Property.forName("code").eq(code));
        List<Estate> estates = template.findByCriteria(criteria);
        return estates.isEmpty() ? null : estates.get(0);
    }

    @Override
    public void add(Estate estate) {
        template.save(estate);
    }

    @Override
    public void save(Estate estate) {
        template.update(estate);
    }

    @Override
    public void delete(Estate estate) {
        template.delete(estate);
    }

    @Override
    public void delete(List<Estate> estates) {
        template.deleteAll(estates);
    }

    @Override
    public List<Estate> list() {
        return template.find("from Estate");
    }

    @Override
    public List<Estate> find(final EstateCategory estateCategory, final EstateType estateType, final List<District> districts,
                             final Integer rooms, final Integer minPrice, final Integer maxPrice,
                             final Integer desc, final String orderBy,
                             final Integer start, final Integer limit) {

        return (List<Estate>) template.execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Criteria criteria = session.createCriteria(Estate.class);
                if (estateCategory != null) {
                    criteria.add(Property.forName("estateCategory").eq(estateCategory));
                }
                if (estateType != null) {
                    criteria.add(Property.forName("estateType").eq(estateType));
                }
                if (districts != null) {
                    Disjunction hasAnyOfSpecifiedDistricts = Restrictions.disjunction();
                    for (District district: districts) {
                        hasAnyOfSpecifiedDistricts.add(Restrictions.eq("district", district));
                    }
                    criteria.add(hasAnyOfSpecifiedDistricts);
                }
                if (rooms >= 0) {
                    criteria.add(Property.forName("rooms").eq(rooms));
                }
                if (minPrice > 0 && maxPrice > 0) {
                    criteria.add(Property.forName("price").between(minPrice, maxPrice));
                } else  if (minPrice > 0 && maxPrice <= 0) {
                    criteria.add(Property.forName("price").ge(minPrice));
                } else if (minPrice <= 0 && maxPrice > 0) {
                    criteria.add(Property.forName("price").le(maxPrice));
                }

                criteria.setResultTransformer(DISTINCT_ROOT_ENTITY);

                if (desc == 2) {
                    criteria.addOrder(Order.desc(orderBy));
                } else if (desc == 1) {
                    criteria.addOrder(Order.asc(orderBy));
                }

                if (limit > 0) {
                    int foundEntitiesNumber = criteria.list().size();
                    return criteria.list().subList(
                            start,
                            Math.min(foundEntitiesNumber, start + limit - 1));
                }

                return criteria.list();
            }
        });
    }

    @Override
    public Long findLearnerCount() throws DataAccessException {
        List result = template.find("select count(*) from Estate");
        return ((Long)result.get(0));
    }

    @Override
    public List<Estate> listSpecial() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Estate.class);
        criteria.add(Property.forName("special").eq(true));
        return template.findByCriteria(criteria);
    }

    @Override
    public List<Estate> findEstatesAddedEarlierThan(Date cutDate) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Estate.class);
        criteria.add(Property.forName("date").le(cutDate));
        return template.findByCriteria(criteria);
    }
}
