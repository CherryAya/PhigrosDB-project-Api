package top.kagurayayoi.phidbapi.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchSong {

    private String Chapter, Name, Add_version, Author, Illustration;
    private Object EZ, HD, IN, AT, Legacy;

    @JsonProperty(index = 1)
    public String getChapter() {
        return Chapter;
    }

    public void setChapter(String chapter) {
        Chapter = chapter;
    }

    @JsonProperty(index = 2)
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @JsonProperty(index = 3)
    public String getAdd_version() {
        return Add_version;
    }

    public void setAdd_version(String add_version) {
        Add_version = add_version;
    }

    @JsonProperty(index = 4)
    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    @JsonProperty(index = 5)
    public String getIllustration() {
        return Illustration;
    }

    public void setIllustration(String illustration) {
        Illustration = illustration;
    }

    @JsonProperty(index = 6)
    public Object getEZ() {
        return EZ;
    }

    public void setEZ(Object EZ) {
        this.EZ = EZ;
    }

    @JsonProperty(index = 7)
    public Object getHD() {
        return HD;
    }

    public void setHD(Object HD) {
        this.HD = HD;
    }

    @JsonProperty(index = 8)
    public Object getIN() {
        return IN;
    }

    public void setIN(Object IN) {
        this.IN = IN;
    }

    @JsonProperty(index = 9)
    public Object getAT() {
        return AT;
    }

    public void setAT(Object AT) {
        this.AT = AT;
    }

    @JsonProperty(index = 10)
    public Object getLegacy() {
        return Legacy;
    }

    public void setLegacy(Object legacy) {
        Legacy = legacy;
    }
}
