package by.homework.regauthapp;

import by.homework.regauthapp.configuration.SessionFactoryConfig;
import by.homework.regauthapp.entity.User;
import by.homework.regauthapp.exception.WrongLoginException;
import by.homework.regauthapp.exception.WrongValueException;
import by.homework.regauthapp.service.UserService;
import by.homework.regauthapp.service.impl.SQLUserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Сергей Зубов on 30.05.2019.
 */
public class Main {
    static final Scanner scan = new Scanner(System.in);
    static int choice;
    static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SessionFactoryConfig.class);
    public static void main(String[] args) {

        while(true) {
            selectAction("Select action:\n\t1)Authentication\n\t2)Registration\n\t3)Exit", 3);
            scan.nextLine();
            entrance(choice);
        }
    }

    private static void selectAction(String msg, int maxNum){
        System.out.println(msg);

        while(!scan.hasNextInt()){
            System.err.println("Error!!!");
            System.out.println(msg);
            scan.next();
        }
        choice = scan.nextInt();
        if (choice < 1 || choice > maxNum) {
            try {
                throw new WrongValueException("Error! Wrong value!!!");
            } catch (WrongValueException e) {
                System.err.println(e.getMessage());
                selectAction(msg, maxNum);
            }
        }
    }

    private static UserService selectStorage() {
        selectAction("Select storage:\n\t1) Collection\n\t2) File\n\t3) DataBase\n\t4) Exit", 4);
        scan.nextLine();
        switch (choice) {
            case 1:
                return null;//ServiceFactory.getServiceFactory().getUserService();
            case 2:
                return null;//ServiceFactory.getServiceFactory().getFileUserService();
            case 3:
                return context.getBean(SQLUserService.class);
            case 4:
                System.exit(0);
            default:
                try {
                    throw new WrongValueException("Error! Wrong value!!!");
                } catch (WrongValueException e) {
                    System.err.println(e.getMessage());
                    selectAction("Select storage:\n\t1) Collection\n\t2) File\n\t3) Exit", 3);
                }
        }
        return null;
    }

    private static void enterData(String[] msg, User user){
        System.out.println(msg[0]);
        for(int i=1;i<msg.length;i++){
            switch(msg[i]){
                case "name":
                    System.out.println("Enter your "+msg[i]);
                    user.setName(scan.nextLine());
                    break;
                case "login":
                    System.out.println("Enter your "+msg[i]);
                    user.setLogin(scan.nextLine());
                    break;
                case "password":
                    System.out.println("Enter your "+msg[i]);
                    user.setPassword(scan.nextLine());
                    break;
                case "":
                    break;
                default:
                    System.out.println("Error!");
                    break;
            }
        }
        if(msg[0].equals("Registration")&&user.getLogin().toLowerCase().equals("admin")){
            try {
                throw new WrongLoginException("Login can't be \"admin\"");
            } catch (WrongLoginException e) {
                System.out.println(e.getMessage());
                enterData(msg, user);
            }
        }
    }

    private static void entrance(int select){
        if(select==1){
            UserService userService = selectStorage();
            if(userService!=null) {
                User user = new User();
                enterData(new String[]{"Authentication","login","password" },user);
                List<User> userList = userService.authentication(user);
                if (userList != null) {
                    if (userList.get(0).getRole()) {
                        for (User usr : userList) System.out.print(usr.toString());
                    } else System.out.println("Hello " + userList.get(0).getName() + "!");
                } else System.out.println("User doesn't exist");
            }else {
                System.err.println("Sorry! Crashing the app");
                System.exit(0);
            }
        }else if(select==2) {
            UserService userService = selectStorage();
            if(userService!=null) {
                User user = new User();
                enterData(new String[]{"Registration", "name", "login", "password"}, user);
                if (userService.registration(user)) {
                    System.out.println("Thank you for registration");
                } else System.out.println("Registration fail!");
            }else {
                System.err.println("Sorry! Crashing the app");
                System.exit(0);
            }
        }else {
            scan.close();
            System.exit(0);}
    }
}

