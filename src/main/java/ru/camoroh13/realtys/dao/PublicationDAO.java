package ru.camoroh13.realtys.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.camoroh13.realtys.domain.Category;
import ru.camoroh13.realtys.domain.Publication;
import ru.camoroh13.realtys.domain.User;

import java.util.List;

public interface PublicationDAO {

    @Transactional
    public void addPublication(Publication publication);

    @Transactional
    public Publication getPublication(Integer id);

    @Transactional
    public User getUser(Publication publication);

    @Transactional
    public User getUser(Integer id);

    @Transactional
    public List<Category> getCategoryList(Publication publication);

    @Transactional
    public List<Category> getCategoryList(Integer id);

    @Transactional
    public void addCategory(Publication publication, Category category);

    @Transactional
    public void addCategory(Publication publication, List<Category> categoryList);

    @Transactional
    public void addCategory(Integer id, Category category);

    @Transactional
    public void addCategory(Integer id, List<Category> categoryList);

    @Transactional
    public List<Publication> listPublication ();

    @Transactional
    public Publication getByUrl(String url);

    @Transactional
    public void setCategoryList(Publication publication, List<Category> categoryList);

    @Transactional
    public void removePublication(Integer id);

    @Transactional
    public void updatePublication(Publication publication);

}