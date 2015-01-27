package ru.camoroh13.realtys.service;

import ru.camoroh13.realtys.domain.Estate;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * User: Konstantin
 * Date: 28.06.11
 * Time: 23:40
 */
public interface EstateService {
    
    public Estate get(Integer id);

    public void add(Estate estate);

    public void save(Estate estate);

    public void delete(Estate estate);

    public List<Estate> list();

    /**
     * Поиск квартир по параметрам. Если параметр равен 0, то по нему поиск не идет
     *
     * @param categoryId ID категории
     * @param typeId ID типа
     * @param districtId ID района
     * @param rooms количество комнат
     * @param minPrice минимальная цена
     * @param maxPrice максимальная цена
     * @return
     */
    public List<Estate> find(Integer categoryId, Integer typeId, List<Integer> districtIds,
                             Integer rooms, Integer minPrice, Integer maxPrice,
                             Integer desc, String orderBy,
                             Integer start, Integer limit);

    public List<Estate> listSpecial();

    public void importEstate(File file);

    public Long count();

    public Estate getByCode(String code);

    void deleteEstatesAddedEarlierThan(Date cutDate);
}
