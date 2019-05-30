package by.homework.regauthapp.service;

import by.homework.regauthapp.entity.User;

import java.util.List;

/**
 * Created by Сергей Зубов on 30.05.2019.
 */
public interface UserService {
    public List<User> authentication(User user);
    public boolean registration(User user);
}
