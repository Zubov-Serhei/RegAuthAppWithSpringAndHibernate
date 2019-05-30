package by.homework.regauthapp.exception;

import org.springframework.stereotype.Component;

/**
 * Created by Сергей Зубов on 11.04.2019.
 */
@Component
public class WrongValueException extends Exception {

    public WrongValueException(){}

    public WrongValueException(String message) {
        super(message);
    }

}
