package ru.camoroh13.realtys.dao;

import ru.camoroh13.realtys.domain.User;

import java.util.List;

public interface UserDAO {

	public void addUser(User user);

	public List<User> listUser();

	public void removeUser(Integer id);

    public User getUser(Integer id);

    public void updateUser(User user);

    public List<User> search(String lastname);

    public List<User> search(String lastname, String firstname, String username);

    public List getUserByName(String username);

}
