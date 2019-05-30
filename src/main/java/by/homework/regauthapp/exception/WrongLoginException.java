package by.homework.regauthapp.exception;

import org.springframework.stereotype.Component;

/**
 * Created by Сергей Зубов on 11.04.2019.
 */
@Component
public class WrongLoginException extends Exception{

    public WrongLoginException(){}

    public WrongLoginException(String message) {
        super(message);
    }
}
