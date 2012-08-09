package ru.camoroh13.realtys.service;

import ru.camoroh13.realtys.domain.Page;

import java.util.List;

/**
 * User: Konstantin
 * Date: 20.07.11
 * Time: 23:14
 */
public interface PageService {

    public Page get(Integer id);

    public Page get(String url);

    public List<Page> list();

    public void add(Page page);

    public void save(Page page);

    public void delete(Page page);

}
