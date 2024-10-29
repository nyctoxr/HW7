package Model;

public class Author {
    private final int id;;
    private final String userName;
    private final String nationalCode;
    private String birthdayYear;
    private final String password;
    private final Article[] articles;
    private int articleCount;

    public Author(int id, String username, String nationalCode,String birthdayYear, String password) {
        this.id = id;
        this.userName = username;
        this.nationalCode = nationalCode;
        this.birthdayYear = birthdayYear;
        this.password = password;
        this.articles = new Article[10];
        this.articleCount = 0;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }
    public String getNationalCode() {
        return nationalCode;
    }
    public String getBirthdayYear() {
        return birthdayYear;
    }


    public String getPassword() {
        return password;
    }

    public int getArticleCount() {
        return articleCount;
    }

    public void addArticle(Article article) {
        if (articleCount < articles.length) {
            articles[articleCount++] = article;
        } else {
            System.out.println("We have no storage");
        }
    }

    public void showArticles() {
            for (int i = 0; i < articleCount; i++) {
                System.out.println(articles[i]);
        }
    }

}