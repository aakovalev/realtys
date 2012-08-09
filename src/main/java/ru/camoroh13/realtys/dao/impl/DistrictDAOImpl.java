package ru.camoroh13.realtys.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ru.camoroh13.realtys.dao.DistrictDAO;
import ru.camoroh13.realtys.domain.District;

import java.util.List;

/**
 * User: Konstantin
 * Date: 27.06.11
 * Time: 19:17
 * To change this template use File | Settings | File Templates.
 */
@Repository()
public class DistrictDAOImpl implements DistrictDAO{

    @Autowired
    private HibernateTemplate template;

    @Override
    public District get(Integer id) {
        District district = template.load(
		    District.class, id);
        district.getDistrictId();
        return district;
    }

    @Override
    public void add(District district) {
        template.save(district);
    }

    @Override
    public void save(District district) {
        template.update(district);
    }

    @Override
    public void delete(District district) {
        template.delete(district);
    }

    @Override
    public List<District> list() {
        List<District> districts = template.find("from District");
        return districts;
    }
}
