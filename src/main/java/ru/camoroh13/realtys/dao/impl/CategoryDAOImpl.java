package ru.camoroh13.realtys.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ru.camoroh13.realtys.dao.CategoryDAO;
import ru.camoroh13.realtys.domain.Category;
import ru.camoroh13.realtys.domain.Publication;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate template;

    /**
     * Добавление категории
     *
     * @param category
     */
    public void addCategory(Category category) {
        sessionFactory.getCurrentSession().save(category);
    }

    /**
     * Удаление категории
     *
     * @param id
     */
    public void removeCategory(Integer id) {
        Category category = (Category) sessionFactory.getCurrentSession().load(
		    Category.class, id);
		if (null != category) {
			sessionFactory.getCurrentSession().delete(category);
		}
    }

    /**
     * Обновление категории
     *
     * @param category
     */
    public void updateCategory(Category category) {
        sessionFactory.getCurrentSession().update(category);        
    }

    /**
     * Список категорий
     *
     * @return
     */
    public List<Category> listCategory() {
        return sessionFactory.getCurrentSession().createQuery("from " + Category.class.getName())
             .setResultTransformer(Transformers.aliasToBean(Category.class))
			.list();
    }

    /**
     * Список всех категорий
     *
     * @return
     */
    public List<Category> listAllCategory() {
        List<Category> categoryList = sessionFactory.getCurrentSession()
            .createSQLQuery("select c.categoryId, c.url, c.name, c.description from categories as c where c.parentId IS NULL")
                .addEntity(Category.class)
            .list();

        searchSubcategories(categoryList);

        return categoryList;
    }

    /**
     * Получение категории по id
     *
     * @param id
     * @return
     */
    public Category getCategory(Integer id) {
        Category category = (Category) sessionFactory.getCurrentSession().load(
		    Category.class, id);
        category.getId();
        return category;
    }

    /**
     * Добавление списка подкатегорий, если известно id родительской категории
     *
     * @param parentId
     * @param subcategory
     */
    public void addSubcategory(Integer parentId, Collection<Category> subcategory) {
        Category category = getCategory(parentId);
        category.setSubcategory(subcategory);
        sessionFactory.getCurrentSession().save(category);
    }

    /**
     * Добавление подкатегории, если известно id родительской категории
     *
     * @param parentId
     * @param subcategory
     */
    public void addSubcategory(Integer parentId, Category subcategory) {
        Category category = getCategory(parentId);
        category.setSubcategory(subcategory);
        sessionFactory.getCurrentSession().save(category);
    }

    /**
     * Получение списка подкатегорий
     *
     * @param parentId
     * @return
     */
    public Collection<Category> getSubcategories(Integer parentId) {
        Category category = getCategory(parentId);
        return category.getSubcategory();
    }

    /**
     * Получение списка публикаций
     *
     * @return
     */
    public Set<Publication> getPublicationList(Integer categoryId) {
        Category category = getCategory(categoryId);
        category.getPublicationsList().size();
        return category.getPublicationsList();
    }

    /**
     * Добавление публикаций в категорию
     *
     * @param publicationsList
     */
    public void setPublicationList(Set<Publication> publicationsList) {
        //
    }

    @Override
    public Category getByUrl(String url) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Category.class)
                                        .add(Property.forName("url").eq(url));
        List<Category> categoryList = template.findByCriteria(criteria);
        if (categoryList.size() > 0) {
            return categoryList.get(0);
        } else {
            return null;
        }
    }

    /**
     * Обход дерева категорий
     *
     * @param categoryList
     */
    private void searchSubcategories(Collection<Category> categoryList) {
        for (Category category:categoryList) {
            Collection<Category> cat = category.getSubcategory();
            if (cat.size() > 0) {
                searchSubcategories(cat);
                System.out.println("name = " + category.getName());
            }
        }
    }

}
