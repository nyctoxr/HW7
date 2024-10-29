import DataBase.Database;
import Model.Article;
import Model.Category;
import Service.ArticleManager;
import Service.UserManager;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Database db = new Database();

        db.addCategory(new Category(1, "Java"));
        db.addCategory(new Category(2, "Math"));
        db.addCategory(new Category(3, "Medicine"));
        db.addCategory(new Category(4, "Science"));
        db.addCategory(new Category(5, "English"));

        db.addArticle(new Article(1,"OOP in Java",
                "trigonometry functions","Abstract,Encapsulation,Inherit,Polymorphism",
                LocalDateTime.of(2023,6,8,12,40),true,
                LocalDateTime.of(2023,6,8,22,5),
                LocalDateTime.of(2023,6,8,6,21),"Published"));

        db.addArticle(new Article(2,"Trigonometry",
                "trigonometry functions","sin,cos,tan,cot,sec,csc",
                LocalDateTime.of(2009,5,25,5,30),true,
                LocalDateTime.of(2009,5,25,14,23),
                LocalDateTime.of(2009,5,25,20,30),"Published"));

        db.addUnpublishedArticle(new Article(3,"Git","Commit","we can commit our projects",
                LocalDateTime.of(2018,05,30,12,34),false,
                LocalDateTime.of(2018,05,30,12,34),
                null,"Unpublished"));


        ArticleManager articleManager = new ArticleManager(db, scanner);
        UserManager userManager = new UserManager(db, scanner);


        boolean exit = false;
        while (!exit) {
            System.out.println("Welcome to the Article Management System");
            System.out.println("1. View All Articles");
            System.out.println("2. Login as Author");
            System.out.println("3. Login as Moderator");
            System.out.println("4. Register");
            System.out.println("5. Exit");
            System.out.print("Please select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    db.showAllArticles();
                    break;
                case 2:
                    userManager.loginAsAuthor();
                    break;
                case 3:
                    userManager.loginAsModerator();
                    break;
                case 4:
                    userManager.register();
                    break;
                case 5:
                    exit = true;
                    System.out.println("See you soon!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}