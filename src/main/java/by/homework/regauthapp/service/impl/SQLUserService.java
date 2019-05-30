package by.homework.regauthapp.service.impl;

import by.homework.regauthapp.dao.UserDAO;
import by.homework.regauthapp.entity.User;
import by.homework.regauthapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Сергей Зубов on 30.05.2019.
 */
@Service
public class SQLUserService implements UserService {

    @Autowired
    private UserDAO userDAO;

    public List<User> authentication(User user) {;
        User usr = userDAO.authentication(user);
        List<User> users = null;
        if(usr!=null) {
            if (usr.getRole()) {
                users = new ArrayList();
                users.addAll(userDAO.getAllUsers());
                return users;
            } else {
                users = new ArrayList();
                users.add(usr);
                return users;
            }
        }else return null;
    }

    public boolean registration(User user) {
        return userDAO.registration(user);
    }
}
