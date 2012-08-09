package ru.camoroh13.realtys.service;



import ru.camoroh13.realtys.domain.Category;
import ru.camoroh13.realtys.domain.Publication;
import ru.camoroh13.realtys.domain.User;

import java.util.List;

public interface PublicationService {

    public void addPublication(Publication publication);

    public Publication getPublication(Integer id);

    public User getUser(Publication publication);

    public User getUser(Integer id);

    public List<Category> getCategoryList(Publication publication);

    public List<Category> getCategoryList(Integer id);

    public void addCategory(Publication publication, Category category);

    public void addCategory(Publication publication, List<Category> categoryList);

    public void addCategory(Integer id, Category category);

    public void addCategory(Integer id, List<Category> categoryList);

    public List<Publication> listPublication();

    public Publication getByUrl(String url);

    public void setCategoryList(Publication publication, List<Category> categoryList);

    public void remove(Integer id);

    public void update(Publication publication);

}
