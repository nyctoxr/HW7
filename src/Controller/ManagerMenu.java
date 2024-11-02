package Controller;

import DataBase.Database;
import Model.Article;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ManagerMenu {
    private final Database database;
    private final Scanner scanner;

    public ManagerMenu(Database database) {
        this.database = database;
        this.scanner = new Scanner(System.in);
    }

    public void managerMenu() {
        while (true) {
            System.out.println("------ Manager Menu ------");
            System.out.println("1. Show All Articles");
            System.out.println("2. Show Unpublished Articles");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showAllArticles();
                    break;
                case 2:
                    showUnpublishedArticles();
                    break;
                case 3:
                    database.logout();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void showAllArticles() {
        database.showAllArticles(); // نمایش مقالات منتشر شده
    }

    private void showUnpublishedArticles() {
        System.out.println("List of Unpublished Articles:");
        Article[] unpublishedArticles = database.getUnpublishedArticles();
        int unpublishedCount = database.getUnpublishedArticleCount();

        if (unpublishedCount == 0) {
            System.out.println("No unpublished articles available.");
        } else {
            for (int i = 0; i < unpublishedCount; i++) {
                Article article = unpublishedArticles[i];
                if (article != null) {
                    System.out.println("ID: " + article.getId() + ", Title: " + article.getTitle() + ", Brief: " + article.getBrief());

                    System.out.print("Enter the ID of the unpublished article to confirm or reject: ");
                    Scanner scanner = new Scanner(System.in);
                    int articleId = scanner.nextInt();
                    if (article.getId() == articleId) {
                        System.out.println("Do you want to confirm or reject? Say yes or no");
                        String answer = scanner.next();
                        if (answer.equalsIgnoreCase("yes")) {
                            article.setPublished(true);
                            article.setStatus("Published");
                            article.setLastUpdateDate(LocalDateTime.now());
                            database.addArticle(article);
                        } else if (answer.equalsIgnoreCase("no")) {
                            managerMenu();
                        }
                    }
                }
            }
        }
    }
}