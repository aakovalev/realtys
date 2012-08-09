package ru.camoroh13.realtys.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ru.camoroh13.realtys.dao.ImageDAO;
import ru.camoroh13.realtys.domain.Image;

/**
 * User: Konstantin
 * Date: 23.09.11
 */
@Repository()
public class ImageDAOImpl implements ImageDAO {

    @Autowired
    private HibernateTemplate template;

    @Override
    public void save(Image image) {
        template.save(image);
    }

    @Override
    public void delete(Image image) {
        template.delete(image);
    }

    @Override
    public Image get(Integer id) {
        Image image = template.load(Image.class, id);
        image.getImageId();
        return image;
    }
}
