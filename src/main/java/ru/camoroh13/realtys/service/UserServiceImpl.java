package ru.camoroh13.realtys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.camoroh13.realtys.dao.UserDAO;
import ru.camoroh13.realtys.domain.User;
import ru.camoroh13.realtys.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    private Boolean xz = new Boolean(false);

    @Transactional
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Transactional
    public void setXz(Boolean x) {
        xz = x;
    }

    @Transactional
    public List<User> listUser() {
        return userDAO.listUser();
    }

    @Transactional
    public void removeUser(Integer id) {
        userDAO.removeUser(id);
    }

    @Transactional
    public User getUser(Integer id) {
        return userDAO.getUser(id);
    }

    @Transactional
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public Boolean getXz() {
        return xz;
    }

    public List<User> search(String lastname) {
        return userDAO.search(lastname);
    }

    public List<User> search(String lastname, String firstname, String username) {
        return userDAO.search(lastname, firstname, username);
    }

    public User getUserByName(String username) {
        List<User> users = userDAO.getUserByName(username);
        return users.isEmpty() ? null : users.get(0);
    }
}
