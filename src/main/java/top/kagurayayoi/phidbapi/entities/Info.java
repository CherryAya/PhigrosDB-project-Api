package top.kagurayayoi.phidbapi.entities;

public class Info extends BaseEntity{

    public static String[] columnName = new String[]
            {"id", "Database_Version", "Author", "Phigros_Version",
            "Total", "Reference", "Reference_Author"};
    private String Database_Version;
    private String Author;
    private String Phigros_Version;
    private int Total;
    private String Reference;
    private String Reference_Author;

    public String getDatabase_Version() {
        return Database_Version;
    }

    public void setDatabase_Version(String database_Version) {
        Database_Version = database_Version;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getPhigros_Version() {
        return Phigros_Version;
    }

    public void setPhigros_Version(String phigros_Version) {
        Phigros_Version = phigros_Version;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public String getReference() {
        return Reference;
    }

    public void setReference(String reference) {
        Reference = reference;
    }

    public String getReference_Author() {
        return Reference_Author;
    }

    public void setReference_Author(String reference_Author) {
        Reference_Author = reference_Author;
    }
}
