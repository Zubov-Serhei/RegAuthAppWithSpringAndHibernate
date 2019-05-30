package by.homework.regauthapp.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Сергей Зубов on 30.05.2019.
 */
@Entity
@Table(name = "users")
@DiscriminatorValue(value="Y")
public class Admin extends User{
    public Admin(){}

    public Admin(String login, String name, String password, boolean role) {
        super(login, name, password);
        this.role = role;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                "}\n";
    }
}
