package ru.camoroh13.realtys.service;

import ru.camoroh13.realtys.domain.District;

import java.util.List;

/**
 * User: Konstantin
 * Date: 27.06.11
 * Time: 19:24
 * To change this template use File | Settings | File Templates.
 */
public interface DistrictService {

    public District get(Integer id);

    public void add(District district);

    public void save(District district);

    public void delete(District district);

    public List<District> list();

}
