package ru.camoroh13.realtys.service;

import org.springframework.transaction.annotation.Transactional;
import ru.camoroh13.realtys.domain.Category;
import ru.camoroh13.realtys.domain.Publication;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface CategoryService {

    public void addCategory(Category category);

    public void removeCategory(Integer id);

    public void updateCategory(Category category);

    public List<Category> listCategory();
    
    public List<Category> listAllCategory();

    public Category getCategory(Integer id);

    public void addSubcategory(Integer parentId, Collection<Category> subcategory);

    public void addSubcategory(Integer parentId, Category subcategory);    

    public Collection<Category> getSubcategories(Integer parentId);

    public Set<Publication> getPublicationList(Integer categoryId);

    public void setPublicationList(Set<Publication> publicationsList);

    @Transactional
    public Category getByUrl(String url);

}
