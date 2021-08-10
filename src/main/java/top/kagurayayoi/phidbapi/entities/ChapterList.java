package top.kagurayayoi.phidbapi.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

// 章节列表模型

public class ChapterList extends BaseEntity{

    public final static String[] columnName = new String[]{"id", "Name", "Title", "Total"};
    private String Name;
    private String Title;
    private String Total;

    @JsonProperty(index = 2)
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @JsonProperty(index = 3)
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    @JsonProperty(index = 4)
    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }
}
