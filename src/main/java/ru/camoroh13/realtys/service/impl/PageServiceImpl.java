package ru.camoroh13.realtys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.camoroh13.realtys.dao.PageDAO;
import ru.camoroh13.realtys.domain.Page;
import ru.camoroh13.realtys.service.PageService;

import java.util.List;

/**
 * User: Konstantin
 * Date: 20.07.11
 * Time: 23:15
 */
@Service
public class PageServiceImpl implements PageService {

    @Autowired
    PageDAO pageDAO;

    @Override
    public Page get(Integer id) {
        return pageDAO.get(id);
    }

    @Override
    public Page get(String url) {
        return pageDAO.get(url);
    }

    @Override
    public List<Page> list() {
        return pageDAO.list();
    }

    @Override
    public void add(Page page) {
        pageDAO.add(page);
    }

    @Override
    public void save(Page page) {
        pageDAO.save(page);
    }

    @Override
    public void delete(Page page) {
        pageDAO.delete(page);
    }
}
