package ru.camoroh13.realtys.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ru.camoroh13.realtys.dao.PublicationDAO;
import ru.camoroh13.realtys.domain.Category;
import ru.camoroh13.realtys.domain.Publication;
import ru.camoroh13.realtys.domain.User;

import java.util.List;

@Repository()
public class PublicationDAOImpl implements PublicationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private HibernateTemplate template;
    public void setTemplate(SessionFactory sessionFactory) {
        template = new HibernateTemplate(sessionFactory);
    }

    public void addPublication(Publication publication) {
        setTemplate(sessionFactory);
        template.save(publication);
    }

    public Publication getPublication(Integer id) {
        setTemplate(sessionFactory);
        //return template.load(Publication.class, id);
        Publication publication = template.load(Publication.class, id);
        List<Category> categories = publication.getCategoriesList();
        categories.size();
        return publication;
    }

    public User getUser(Publication publication) {
        return publication.getUser();
    }

    public User getUser(Integer publicationId) {
        Publication publication = getPublication(publicationId);
        return getUser(publication);
    }

    public List<Category> getCategoryList(Publication publication) {
        return publication.getCategoriesList();
    }

    public List<Category> getCategoryList(Integer publicationId) {
        Publication publication = getPublication(publicationId);
        return getCategoryList(publication);
    }

    public void addCategory(Publication publication, Category category) {
        setTemplate(sessionFactory);
        List<Category> categoryList = getCategoryList(publication);
        System.out.println("catSize = " + categoryList.size());
        categoryList.add(category);
        publication.setCategoriesList(categoryList);
        template.saveOrUpdate(publication);
    }

    public void addCategory(Publication publication, List<Category> categoryList) {
        publication.addCategory(categoryList);
        setTemplate(sessionFactory);
        template.saveOrUpdate(publication);
    }

    public void addCategory(Integer id, Category category) {
        Publication publication = getPublication(id);
        addCategory(publication, category);
    }

    public void addCategory(Integer id, List<Category> categoryList) {
        Publication publication = getPublication(id);
        addCategory(publication, categoryList);
    }

    public List<Publication> listPublication() {
        setTemplate(sessionFactory);
        List<Publication> list = template.find("from Publication");
        System.out.println("Publications : " + list.size());
        return list;
    }

    public Publication getByUrl(String url) {
        setTemplate(sessionFactory);
        DetachedCriteria criteria = DetachedCriteria.forClass(Publication.class)
                                        .add(Property.forName("url").eq(url));
        List<Publication> publicationList = template.findByCriteria(criteria);
        if (publicationList.size() > 0) {
            return publicationList.get(0);
        } else {
            return null;
        }
    }

    public void setCategoryList(Publication publication, List<Category> categoryList) {
        setTemplate(sessionFactory);
        publication.setCategoriesList(categoryList);
        template.saveOrUpdate(publication);
    }

    public void removePublication(Integer id) {
        Publication publication = getPublication(id);
		if (null != publication) {
			template.delete(publication);
		}
    }

    public void updatePublication(Publication publication) {
        setTemplate(sessionFactory);
        template.update(publication);
    }
}