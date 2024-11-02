package Controller;

import DataBase.Database;
import Model.Author;
import Service.ArticleManager;

import java.util.Scanner;

public class UserMenu {
    private static Database db;
    private static Scanner sc;
    private static ArticleManager articleManager;

    public UserMenu(Database db , Scanner sc) {
        this.db = db;
        this.sc = sc;
        this.articleManager= new ArticleManager(db , sc);
    }

    public static void userMenu() {
        boolean logout = false;
        Author activeAuthor = db.getActiveAuthor();

        while (!logout) {
            System.out.println("Welcome   " + activeAuthor.getUserName());
            System.out.println("1. View My Articles");
            System.out.println("2. Edit My Articles");
            System.out.println("3. Create a New Article");
            System.out.println("4. Logout");
            System.out.print("Please select one");
            int choice = sc.nextInt();
            sc.nextLine();
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
                    userMenu();
            }
        }
    }
}
