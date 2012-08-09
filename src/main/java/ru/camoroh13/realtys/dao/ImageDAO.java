package ru.camoroh13.realtys.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.camoroh13.realtys.domain.Image;

/**
 * User: Konstantin
 * Date: 23.09.11
 */
public interface ImageDAO {

    @Transactional
    public void save(Image image);

    @Transactional
    public void delete(Image image);

    @Transactional
    public Image get(Integer id);

}
