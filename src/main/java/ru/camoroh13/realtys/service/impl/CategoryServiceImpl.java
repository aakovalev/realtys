package ru.camoroh13.realtys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.camoroh13.realtys.dao.CategoryDAO;
import ru.camoroh13.realtys.domain.Category;
import ru.camoroh13.realtys.domain.Publication;
import ru.camoroh13.realtys.service.CategoryService;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    public void addCategory(Category category) {
        categoryDAO.addCategory(category);
    }

    public void removeCategory(Integer id) {
        categoryDAO.removeCategory(id);
    }

    public void updateCategory(Category category) {
        categoryDAO.updateCategory(category);
    }

    public List<Category> listCategory() {
        return categoryDAO.listCategory();
    }

    public List<Category> listAllCategory() {
        return categoryDAO.listAllCategory();
    }

    public Category getCategory(Integer id) {
        return categoryDAO.getCategory(id);
    }

    public void addSubcategory(Integer parentId, Collection<Category> subcategory) {
        categoryDAO.addSubcategory(parentId, subcategory);
    }

    public void addSubcategory(Integer parentId, Category subcategory) {
        categoryDAO.addSubcategory(parentId, subcategory);
    }

    public Collection<Category> getSubcategories(Integer parentId) {
        return categoryDAO.getSubcategories(parentId);
    }

    public Set<Publication> getPublicationList(Integer categoryId) {
        return categoryDAO.getPublicationList(categoryId);
    }

    public void setPublicationList(Set<Publication> publicationsList) {
        categoryDAO.setPublicationList(publicationsList);
    }

    @Override
    public Category getByUrl(String url) {
        Category category = categoryDAO.getByUrl(url);
        if (category != null) {
            category.getPublicationsList().size();
        }
        return category;
    }


}
