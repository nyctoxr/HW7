package Service;

import Model.Article;
import DataBase.Database;
import java.time.LocalDateTime;

public class ArticleFilter {
    private final Database database;
    public ArticleFilter(Database database) {
        this.database = database;
    }

    public Article[] filterArticles(Article[] articles, String dateType, String range) {

        LocalDateTime dateLimit = calculateDateLimit(range);


        int count = 0;
        for (Article article : articles) {
            if (article != null) {
                LocalDateTime articleDate = getArticleDateByType(article, dateType);
                if (articleDate != null && articleDate.isAfter(dateLimit)) {
                    count++;
                }
            }
        }


        Article[] filteredArticles = new Article[count];
        int index = 0;
        for (Article article : articles) {
            if (article != null) {
                LocalDateTime articleDate = getArticleDateByType(article, dateType);
                if (articleDate != null && articleDate.isAfter(dateLimit)) {
                    filteredArticles[index++] = article;
                }
            }
        }


        database.showAllArticles();

        return filteredArticles;
    }

    private LocalDateTime getArticleDateByType(Article article, String dateType) {
        if (article == null) return null;
        switch (dateType) {
            case "createdDate":
                return article.getCreateDate();
            case "lastEditedDate":
                return article.getLastUpdateDate();
            case "publishedDate":
                return article.getPublishDate();
            default:
                throw new IllegalArgumentException("Unknown date type: " + dateType);
        }
    }


    private LocalDateTime calculateDateLimit(String range) {
        LocalDateTime now = LocalDateTime.now();
        switch (range) {
            case "24Hours":
                return now.minusHours(24);
            case "1Week":
                return now.minusWeeks(1);
            case "1Month":
                return now.minusMonths(1);
            case "6Months":
                return now.minusMonths(6);
            case "1Year":
                return now.minusYears(1);
            default:
                throw new IllegalArgumentException("Unknown range: " + range);
        }
    }
}