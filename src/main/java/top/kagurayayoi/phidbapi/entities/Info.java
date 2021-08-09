package top.kagurayayoi.phidbapi.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Info extends BaseEntity{

    public final static String[] columnName = new String[]
            {"id", "Database_Version", "Author", "Phigros_Version",
            "Total", "Reference", "Reference_Author"};
    private String Database_Version;
    private String Author;
    private String Phigros_Version;
    private int Total;
    private String Reference;
    private String Reference_Author;

    @JsonProperty(index = 2)
    public String getDatabase_Version() {
        return Database_Version;
    }

    public void setDatabase_Version(String database_Version) {
        Database_Version = database_Version;
    }

    @JsonProperty(index = 3)
    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    @JsonProperty(index = 3)
    public String getPhigros_Version() {
        return Phigros_Version;
    }

    public void setPhigros_Version(String phigros_Version) {
        Phigros_Version = phigros_Version;
    }

    @JsonProperty(index = 4)
    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    @JsonProperty(index = 5)
    public String getReference() {
        return Reference;
    }

    public void setReference(String reference) {
        Reference = reference;
    }

    @JsonProperty(index = 6)
    public String getReference_Author() {
        return Reference_Author;
    }

    public void setReference_Author(String reference_Author) {
        Reference_Author = reference_Author;
    }
}
