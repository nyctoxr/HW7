package Controller;

import DataBase.Database;
import Model.Author;

import java.util.Scanner;

public class LoginAndRegisteration {
    private Database db;
    private Scanner sc;
    public LoginAndRegisteration(Database db) {
        this.db = db;
        sc = new Scanner(System.in);
    }


    public void loginAsModerator() {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (username.equals(db.getModerator().getUsername()) && password.equals(db.getModerator().getPassword())) {
            System.out.println("You are moderator");
            ManagerMenu managerMenu = new ManagerMenu(db);
            managerMenu.managerMenu();

        } else {
            System.out.println("Invalid username or password.");
            loginAsModerator();
        }
    }

    public void loginAsAuthor() {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        for(int i =0; i< db.getAuthorCount(); i++) {
            if (db.login(username, password)) {
                System.out.println("Login successful.");
                UserMenu.userMenu();
            }

            else {
                System.out.println("Wrong");
                loginAsAuthor();
            }

        }

    }

    public void register() {
        System.out.print("Enter UserName: ");
        String userName = sc.nextLine();
        System.out.print("Enter nationalCode: ");
        String nationalCode = sc.nextLine();
        System.out.print("Enter birthdayYear: ");
        String birthdayYear = sc.nextLine();
        System.out.print("Enter password: ");
        String Password = sc.nextLine();
        db.addAuthor(new Author(db.getAuthorCount()+1,userName,nationalCode,birthdayYear,Password));
        System.out.println("Registration successful.");
    }

}
