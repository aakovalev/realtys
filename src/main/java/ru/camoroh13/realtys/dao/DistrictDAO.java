package ru.camoroh13.realtys.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.camoroh13.realtys.domain.District;

import java.util.List;

/**
 * User: Konstantin
 * Date: 27.06.11
 * Time: 19:13
 * To change this template use File | Settings | File Templates.
 */
public interface DistrictDAO {

    @Transactional
    public District get(Integer id);

    @Transactional
    public void add(District district);

    @Transactional
    public void save(District district);

    @Transactional
    public void delete(District district);

    @Transactional
    public List<District> list();

}
