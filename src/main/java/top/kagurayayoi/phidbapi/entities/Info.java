package top.kagurayayoi.phidbapi.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import top.kagurayayoi.version.PublishType;
import top.kagurayayoi.version.Version;

// 数据库信息模型

public class Info extends BaseEntity{

    public final static String[] columnName = new String[]{"id", "Database_Version", "Publish_Type", "Author", "Phigros_Version", "Total", "Reference", "Reference_Author"};
    private Version Api_Version;
    private String Database_Version;
    private PublishType Publish_Type;
    private String Author;
    private String Phigros_Version;
    private Integer Total;
    private String Reference;
    private String Reference_Author;

    @JsonProperty(index = 1)
    public String getApi_Version() {
        return Api_Version.toString();
    }

    public void setApi_Version(Version api_Version) {
        Api_Version = api_Version;
    }

    @JsonProperty(index = 2)
    public String getDatabase_Version() {
        return Database_Version;
    }

    public void setDatabase_Version(String database_Version) {
        Database_Version = database_Version;
    }

    @JsonProperty(index = 3)
    public PublishType getPublish_Type() {
        return Publish_Type;
    }

    public Info setPublish_Type(String publish_Type) {
        if (publish_Type == PublishType.SNAPSHOT.toString())
            Publish_Type = PublishType.SNAPSHOT;
        else if (publish_Type == PublishType.RELEASE.toString())
            Publish_Type = PublishType.RELEASE;
        return this;
    }

    @JsonProperty(index = 4)
    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    @JsonProperty(index = 5)
    public String getPhigros_Version() {
        return Phigros_Version;
    }

    public void setPhigros_Version(String phigros_Version) {
        Phigros_Version = phigros_Version;
    }

    @JsonProperty(index = 6)
    public Integer getTotal() {
        return Total;
    }

    public void setTotal(Integer total) {
        Total = total;
    }

    @JsonProperty(index = 7)
    public String getReference() {
        return Reference;
    }

    public void setReference(String reference) {
        Reference = reference;
    }

    @JsonProperty(index = 8)
    public String getReference_Author() {
        return Reference_Author;
    }

    public void setReference_Author(String reference_Author) {
        Reference_Author = reference_Author;
    }
}
