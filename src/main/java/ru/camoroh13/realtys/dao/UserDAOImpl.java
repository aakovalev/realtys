package ru.camoroh13.realtys.dao;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.camoroh13.realtys.domain.User;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private HibernateTemplate template;
    public void setTemplate(SessionFactory sessionFactory) {
        template = new HibernateTemplate(sessionFactory);
    }

    /**
     * Добавление пользователя
     *
     * @param user
     */
    public void addUser(User user) {
        setTemplate(sessionFactory);
        template.save(user);
    }

    /**
     * Выборка списка всех пользователей
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<User> listUser() {
        setTemplate(sessionFactory);
        List list = template.find("from User");
        System.out.println("Users : " + list.size());
        return list;
    }

    /**
     * Удаление пользователя
     *
     * @param id
     */
    public void removeUser(Integer id) {
        setTemplate(sessionFactory);
        User user = (User) template.load(
		    User.class, id);
		if (null != user) {
			template.delete(user);
		}
    }

    /**
     * Выбор пользователя по id
     *
     * @param id
     * @return
     */
    @Transactional
    public User getUser(Integer id) {
        setTemplate(sessionFactory);
        User user = template.load(
		    User.class, id);
        //TODO: Если убрать getId(), то вываливается exeption ?!
        //org.hibernate.LazyInitializationException: could not initialize proxy - no Session
        //freemarker.template.TemplateModelException: get(id) failed on instance of ru.camoroh13.cmstika.domain.User$$EnhancerByCGLIB$$a34aac4e        
        user.getId();
        return user;
    }

    /**
     * Обновление пользователя
     *
     * @param user
     */
    public void updateUser(User user) {
        setTemplate(sessionFactory);
        template.update(user);
    }

    /**
     * Поиск пользователей по фамилии
     *
     * @param lastname
     * @return
     */
    public List<User> search(String lastname) {
        setTemplate(sessionFactory);
        MatchMode mmode = MatchMode.START;
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class)
                                        .add(Property.forName("lastname").like(lastname, mmode));
        return template.findByCriteria(criteria);
    }

    /**
     * Поиск пользователей по фамилии, имени и логину
     *
     * @param lastname
     * @param firstname
     * @param username
     * @return
     */
    public List<User> search(String lastname, String firstname, String username) {
        setTemplate(sessionFactory);
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        MatchMode mmode = MatchMode.START;
        if (!"".equals(lastname)) {
            criteria.add(Property.forName("lastname").like(lastname, mmode));
        }
        if (!"".equals(firstname)) {
            criteria.add(Property.forName("firstname").like(firstname, mmode));
        }
        if (!"".equals(username)) {
            criteria.add(Property.forName("username").like(username, mmode));
        }
        return template.findByCriteria(criteria);
    }

    public List getUserByName(String username) {
        setTemplate(sessionFactory);
        return template.find("from User where username = ?",
                new Object[]{username});
    }

}
