package by.homework.regauthapp.dao;

import by.homework.regauthapp.entity.User;
import java.util.List;

/**
 * Created by Сергей Зубов on 30.05.2019.
 */
public interface UserDAO {
    public User authentication(User user);
    public boolean registration(User user);
    public List<User> getAllUsers();
}
