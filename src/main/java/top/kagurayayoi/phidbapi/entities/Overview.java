package top.kagurayayoi.phidbapi.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

// 收录曲总览表模型

public class Overview extends BaseEntity {

    public final static String[] columnName = new String[]{"id", "Chapter", "Name", "Version", "Author", "Illustration"};
    private String Chapter;
    private String Name;
    private String Version;
    private String Author;
    private String Illustration;

    @JsonProperty(index = 2)
    public String getChapter() {
        return Chapter;
    }

    public void setChapter(String chapter) {
        Chapter = chapter;
    }

    @JsonProperty(index = 3)
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @JsonProperty(index = 4)
    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    @JsonProperty(index = 5)
    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    @JsonProperty(index = 6)
    public String getIllustration() {
        return Illustration;
    }

    public void setIllustration(String illustration) {
        Illustration = illustration;
    }
}
