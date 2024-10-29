package DataBase;
import Model.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Database {
    private final Author[] authors;
    private Author activeAuthor;
    private final Category[] categories;
    private final Article[] articles;
    private final Article[] unpublishedArticle;
    private Moderator moderator;
    private int authorCount;
    private int categoryCount;
    private int articleCount;


    public Database() {
        authors = new Author[10];
        categories = new Category[10];
        articles = new Article[10];
        unpublishedArticle = new Article[10];
        this.moderator = new Moderator(1,"Admin","Admin");
        authorCount = 0;
        categoryCount = 0;
        articleCount = 0;
    }

    public void addAuthor(Author author) {
        if (authorCount < authors.length) {
            authors[authorCount++] = author;
        }
    }

    public void addArticle(Article article) {
        if (articleCount < articles.length) {
            articles[articleCount++] = article;
        }
    }

    public void addUnpublishedArticle(Article article) {
        if (articleCount < articles.length) {
            unpublishedArticle[articleCount++] = article;
        }
    }

    public void addCategory(Category category) {
        if (categoryCount < categories.length) {
            categories[categoryCount++] = category;
        }
    }

    public boolean login(String username, String password) {
        for (int i = 0; i < authorCount; i++) {
            if (authors[i].getUserName().equals(username) && authors[i].getPassword().equals(password)) {
                activeAuthor = authors[i];
                return true;
            }
        }
        return false;
    }

    public void logout() {
        activeAuthor = null;
        System.out.println("Logged out successfully.");
    }

    public Author getActiveAuthor() {
        return activeAuthor;
    }

    public void showCategories() {
        for (int i = 0; i < categoryCount; i++) {
            System.out.println(categories[i]);
        }
    }


    public void showAllArticles() {
        System.out.println("List of Published Articles:");
        for (int i = 0; i < articleCount; i++) {
            Article article = articles[i];
            if (article != null) {
                System.out.println("ID: " + article.getId() + ", Title: " + article.getTitle() + ", Brief: " + article.getBrief());
            }
        }

        System.out.print("Enter the ID of the article to view details: ");

        Scanner scanner = new Scanner(System.in);
        int articleId = scanner.nextInt();
        Article article = getArticleById(articleId);


        if (article == null) {
            System.out.println("Article not found with ID: " + articleId);
            return;
        }

        System.out.println("Title: " + article.getTitle() + "\n" +
                "Brief: " + article.getBrief() + "\n" +
                "Content: " + article.getContent() + "\n" +
                "Last Updated: " + article.getLastUpdateDate());
    }

    public void showAllUnpublishedArticles() {
        if (unpublishedArticle == null || unpublishedArticle.length == 0) {
            System.out.println("No unpublished articles found.");
            managerMenu();
            return;
        }
        System.out.println("List of Unpublished Articles:");
        for (int i = 0; i < articleCount; i++) {
            Article unpublishedArticle = this.unpublishedArticle[i];
            if (unpublishedArticle != null) {
                System.out.println("ID: " + unpublishedArticle.getId() + ", Title: " + unpublishedArticle.getTitle() + ", Brief: " + unpublishedArticle.getBrief());
                System.out.print("Enter the ID of the unpublished article to confirm or reject: ");
                Scanner scanner = new Scanner(System.in);
                int articleId = scanner.nextInt();
                if (unpublishedArticle.getId() == articleId) {
                    System.out.println("Do you want to confirm or reject? Say yes or no");
                    String answer = scanner.next();
                    if (answer.equalsIgnoreCase("yes")) {
                        unpublishedArticle.setPublished(true);
                        unpublishedArticle.setStatus("Published");
                        unpublishedArticle.setLastUpdateDate(LocalDateTime.now());
                        addArticle(unpublishedArticle);
                    } else if (answer.equalsIgnoreCase("no")) {
                        managerMenu();
                    }
                }
            }
        }
        System.out.print("No unpublished articles found. ");
        managerMenu();
    }

    public  void managerMenu(){
        Scanner scanner = new Scanner(System.in);
        boolean logout= false;
        while(!logout){
            System.out.println("1. Show all Articles");
            System.out.println("2. Show unpublished Articles for approve");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            switch(choice){
                case 1:
                    showAllArticles();
                    break;
                case 2:
                    showAllUnpublishedArticles();
                    break;
                case 3:
                    logout=true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");

            }
        }
    }


    public Category getCategoryById(int categoryId) {
        for (int i = 0; i < categoryCount; i++) {
            if (categories[i].getId() == categoryId) {
                return categories[i];
            }
        }
        return null;
    }

    public Article getArticleById(int articleId) {
        for (int i = 0; i < articleCount; i++) {
            Article article = articles[i];
            if (article != null && article.getId() == articleId) {
                return article;
            }
        }
        return null;
    }

        public int getAuthorCount () {
            return authorCount;
        }

        public int getArticleCount () {
            return articleCount;
        }
        public Moderator getModerator() {
        return moderator;
        }
}