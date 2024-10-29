package Service;

import DataBase.Database;
import Model.Author;

import java.util.Scanner;

public class UserManager {
    private final Database db;
    private final Scanner scanner;
    private ArticleManager articleManager;

    public UserManager(Database db, Scanner scanner) {
        this.db = db;
        this.scanner = scanner;
        this.articleManager = new ArticleManager(db, scanner);
    }


    public void loginAsModerator() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (username.equals(db.getModerator().getUsername()) && password.equals(db.getModerator().getPassword())) {
            System.out.println("You are moderator");
            db.managerMenu();

        } else {
            System.out.println("Invalid username or password.");
            loginAsModerator();
        }
    }

    public void loginAsAuthor() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        for(int i =0; i< db.getAuthorCount(); i++) {
            if (db.login(username, password)) {
                System.out.println("Login successful.");
                userMenu();
            }

             else {
                System.out.println("Wrong");
                loginAsAuthor();
            }

        }

    }



    public void register() {

            System.out.print("Enter UserName: ");
            String userName = scanner.nextLine();
            System.out.print("Enter nationalCode: ");
            String nationalCode = scanner.nextLine();
            System.out.print("Enter birthdayYear: ");
            String birthdayYear = scanner.nextLine();
            System.out.print("Enter password: ");
            String Password = scanner.nextLine();
            db.addAuthor(new Author(db.getAuthorCount()+1,userName,nationalCode,birthdayYear,Password));

            System.out.println("Registration successful.");
    }



    private void userMenu() {
        boolean logout = false;
        Author activeAuthor = db.getActiveAuthor();

        while (!logout) {
            System.out.println("Welcome   " + activeAuthor.getUserName());
            System.out.println("1. View My Articles");
            System.out.println("2. Edit My Articles");
            System.out.println("3. Create a New Article");
            System.out.println("4. Logout");
            System.out.print("Please select one");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    activeAuthor.showArticles();
                    break;
                case 2:
                    articleManager.editArticle(activeAuthor);
                case 3:
                    articleManager.createArticle(activeAuthor);
                    break;
                case 4:
                    db.logout();
                    logout = true;
                    break;
                default:
                    System.out.println("Try again.");
            }
        }
    }
}