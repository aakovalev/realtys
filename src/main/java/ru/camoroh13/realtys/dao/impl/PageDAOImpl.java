package ru.camoroh13.realtys.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ru.camoroh13.realtys.dao.PageDAO;
import ru.camoroh13.realtys.domain.Page;

import java.util.List;

/**
 * User: Konstantin
 * Date: 20.07.11
 * Time: 22:51
 */
@Repository
public class PageDAOImpl implements PageDAO {

    @Autowired
    private HibernateTemplate template;

    @Override
    public Page get(Integer id) {
        Page page = template.load(Page.class, id);
        page.getPageId();
        return page;
    }

    @Override
    public Page get(String url) {
        List<Page> pages = template.find("from Page where url = ?",
                new Object[]{url});
        if (pages != null && pages.size() > 0) {
            return pages.get(0);
        }
        return null;
    }

    @Override
    public List<Page> list() {
        return template.find("from Page");
    }

    @Override
    public void add(Page page) {
        template.save(page);
    }

    @Override
    public void save(Page page) {
        template.saveOrUpdate(page);
    }

    @Override
    public void delete(Page page) {
        template.delete(page);
    }
}
