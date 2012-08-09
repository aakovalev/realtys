package ru.camoroh13.realtys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.camoroh13.realtys.dao.UserDAO;

import java.util.List;

/**
 * Класс для аутентификации пользователей
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    private String username = "";

    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException {
        List results = userDAO.getUserByName(username);
        this.username = username;

        if (results.size() < 1) {
            throw new UsernameNotFoundException(username + "not found");
        }
        return (UserDetails) results.get(0);
    }

    @Override
    public String toString()  {
        return username;
    }

}
