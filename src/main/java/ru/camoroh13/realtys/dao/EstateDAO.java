package ru.camoroh13.realtys.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import ru.camoroh13.realtys.domain.District;
import ru.camoroh13.realtys.domain.Estate;
import ru.camoroh13.realtys.domain.EstateCategory;
import ru.camoroh13.realtys.domain.EstateType;

import java.util.List;

/**
 * User: Konstantin
 * Date: 27.06.11
 * Time: 20:18
 * To change this template use File | Settings | File Templates.
 */
public interface EstateDAO {
    
    @Transactional
    public Estate get(Integer id);

    @Transactional
    public Estate getByCode(String code);

    @Transactional
    public void add(Estate estate);

    @Transactional
    public void save(Estate estate);

    @Transactional
    public void delete(Estate estate);

    @Transactional
    public void delete(List<Estate> estate);

    @Transactional
    public List<Estate> list();

    @Transactional
    public List<Estate> find(EstateCategory estateCategory, EstateType estateType,
                             District district, Integer rooms,
                             Integer minPrice, Integer maxPrice,
                             Integer desc, String orderBy,
                             Integer start, Integer limit
                             );

    @Transactional
    public List<Estate> listSpecial();

    @Transactional
    public Long findLearnerCount() throws DataAccessException;
    
}
