package ru.camoroh13.realtys.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.camoroh13.realtys.domain.Category;
import ru.camoroh13.realtys.domain.Publication;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface CategoryDAO {

    @Transactional
    public void addCategory(Category category);

    @Transactional
    public void removeCategory(Integer id);

    @Transactional
    public void updateCategory(Category category);

    @Transactional
    public List<Category> listCategory();

    @Transactional
    public List<Category> listAllCategory();    

    @Transactional
    public Category getCategory(Integer id);

    @Transactional
    public void addSubcategory(Integer parentId, Collection<Category> subcategory);

    @Transactional
    public void addSubcategory(Integer parentId, Category subcategory);

    @Transactional
    public Collection<Category> getSubcategories(Integer parentId);

    @Transactional
    public Set<Publication> getPublicationList(Integer categoryId);

    @Transactional
    public void setPublicationList(Set<Publication> publicationsList);

    @Transactional
    public Category getByUrl(String url);

}
