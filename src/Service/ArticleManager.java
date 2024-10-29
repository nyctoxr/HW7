package Service;

import DataBase.Database;
import Model.Article;
import Model.Author;
import Model.Category;
import Model.Tag;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ArticleManager {
    private final Database db;
    private final Scanner scanner;

    public ArticleManager(Database db, Scanner scanner) {
        this.db = db;
        this.scanner = scanner;
    }

    public void createArticle(Author activeAuthor) {
        System.out.println("Select a category:");
        db.showCategories();
        int categoryId = scanner.nextInt();
        scanner.nextLine();
        Category category = db.getCategoryById(categoryId);
        if (category != null) {
        System.out.print("Enter article title: ");
        String title = scanner.nextLine();

        System.out.print("Enter article brief: ");
        String brief = scanner.nextLine();

        System.out.print("Enter article content: ");
        String content = scanner.nextLine();

            System.out.println("Select a tag:");
            TagService tagService = new TagService();
            Tag[] Tags = tagService.getAllTags();
            for (int i = 0; i < Tags.length; i++) {
                Tag tag = Tags[i];
                System.out.println(tag.getTagId() + ". " + tag.getName());
            }

            Tag selectedTag = null;

            System.out.print("Enter the tag ID you want to select: ");
            int tagId = scanner.nextInt();
            selectedTag = tagService.getTagById(tagId);
            if (selectedTag == null) {
                System.out.println("Invalid tag selected.");
                return;
                }
        LocalDateTime createDate = LocalDateTime.now();
        LocalDateTime lastUpdateDate = createDate;
        LocalDateTime publishDate = null;

        Article newArticle = new Article(
                db.getArticleCount()+1, title, brief, content, createDate, false, lastUpdateDate, publishDate, "Unpublished");

        activeAuthor.addArticle(newArticle);
        db.addUnpublishedArticle(newArticle);
        System.out.println("Article created successfully. Waiting for confirmation.");
        }
    }

    public void editArticle(Author activeAuthor) {
        System.out.println("Your articles:");
        activeAuthor.showArticles();

        System.out.print("Enter the ID of the article you want to edit: ");
        int articleId = scanner.nextInt();
        scanner.nextLine();

        Article articleToEdit = db.getArticleById(articleId);

        if (articleToEdit != null) {
            System.out.println("Editing article: " + articleToEdit.getTitle());


            System.out.print("Enter new title: ");
            String newTitle = scanner.nextLine();
                articleToEdit.setTitle(newTitle);

            System.out.print("Enter new brief: ");
            String newBrief = scanner.nextLine();
                articleToEdit.setBrief(newBrief);

            System.out.print("Enter new content: ");
            String newContent = scanner.nextLine();
                articleToEdit.setContent(newContent);

            articleToEdit.setLastUpdateDate(LocalDateTime.now());

            System.out.println("Article edited successfully.");
        } else {
            System.out.println("Article not found.");
        }
    }
}