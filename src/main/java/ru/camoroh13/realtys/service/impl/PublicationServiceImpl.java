package ru.camoroh13.realtys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.camoroh13.realtys.dao.PublicationDAO;
import ru.camoroh13.realtys.domain.Category;
import ru.camoroh13.realtys.domain.Publication;
import ru.camoroh13.realtys.domain.User;
import ru.camoroh13.realtys.service.PublicationService;

import java.util.List;

@Service
public class PublicationServiceImpl implements PublicationService {

    @Autowired
    PublicationDAO publicationDAO;

    public void addPublication(Publication publication) {
        publicationDAO.addPublication(publication);
    }

    public Publication getPublication(Integer id) {
        return publicationDAO.getPublication(id);
    }

    public User getUser(Publication publication) {
        return publicationDAO.getUser(publication);
    }

    public User getUser(Integer id) {
        return publicationDAO.getUser(id);
    }

    public List<Category> getCategoryList(Publication publication) {
        return publicationDAO.getCategoryList(publication);
    }

    public List<Category> getCategoryList(Integer id) {
        return publicationDAO.getCategoryList(id);
    }

    public void addCategory(Publication publication, Category category) {
        publicationDAO.addCategory(publication, category);
    }

    public void addCategory(Publication publication, List<Category> categoryList) {
        publicationDAO.addCategory(publication, categoryList);
    }

    public void addCategory(Integer id, Category category) {
        publicationDAO.addCategory(id, category);
    }

    public void addCategory(Integer id, List<Category> categoryList) {
        publicationDAO.addCategory(id, categoryList);
    }

    public List<Publication> listPublication() {
        return publicationDAO.listPublication();
    }

    public Publication getByUrl(String url) {
        return publicationDAO.getByUrl(url);
    }

    public void setCategoryList(Publication publication, List<Category> categoryList) {
        publicationDAO.setCategoryList(publication, categoryList);
    }

    public void remove(Integer id) {
        publicationDAO.removePublication(id);
    }

    public void update(Publication publication) {
        publicationDAO.updatePublication(publication);
    }
}
