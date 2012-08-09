package ru.camoroh13.realtys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.camoroh13.realtys.dao.DistrictDAO;
import ru.camoroh13.realtys.domain.District;
import ru.camoroh13.realtys.service.DistrictService;

import java.util.List;

/**
 * User: Konstantin
 * Date: 27.06.11
 * Time: 19:25
 * To change this template use File | Settings | File Templates.
 */
@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    DistrictDAO districtDAO;

    @Override
    public District get(Integer id) {
        return districtDAO.get(id);
    }

    @Override
    public void add(District district) {
        districtDAO.add(district);
    }

    @Override
    public void save(District district) {
        districtDAO.save(district);
    }

    @Override
    public void delete(District district) {
        districtDAO.delete(district);
    }

    @Override
    public List<District> list() {
        return districtDAO.list();
    }
}
