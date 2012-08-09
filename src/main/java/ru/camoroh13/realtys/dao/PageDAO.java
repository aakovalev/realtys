package ru.camoroh13.realtys.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.camoroh13.realtys.domain.Page;

import java.util.List;

/**
 * User: Konstantin
 * Date: 20.07.11
 * Time: 22:48
 */
public interface PageDAO {

    @Transactional
    public Page get(Integer id);

    @Transactional
    public Page get(String url);

    @Transactional
    public List<Page> list();

    @Transactional
    public void add(Page page);

    @Transactional
    public void save(Page page);

    @Transactional
    public void delete(Page page);

}
