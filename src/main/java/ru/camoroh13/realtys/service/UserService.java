package ru.camoroh13.realtys.service;

import org.springframework.transaction.annotation.Transactional;
import ru.camoroh13.realtys.domain.User;

import java.util.List;

public interface UserService {
    
	public void addUser(User User);

	public List<User> listUser();

	public void removeUser(Integer id);

    public User getUser(Integer id);

    public void updateUser (User user);

    public void setXz(Boolean x);

    public Boolean getXz();

    public List<User> search(String lastname);

    public List<User> search(String lastname, String firstname, String username);

    public User getUserByName(String username);
    
}
