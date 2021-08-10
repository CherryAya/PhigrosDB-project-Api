package top.kagurayayoi.phidbapi.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Difficulty extends BaseEntity{

    public final static String[] columnName = new String[]{"id", "Grade", "Lv", "FullName"};
    private String Grade;
    private String Lv;
    private String FullName;

    @JsonProperty(index = 2)
    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    @JsonProperty(index = 3)
    public String getLv() {
        return Lv;
    }

    public void setLv(String lv) {
        Lv = lv;
    }

    @JsonProperty(index = 4)
    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }
}
