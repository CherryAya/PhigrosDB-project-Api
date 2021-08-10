package top.kagurayayoi.database;

import org.springframework.lang.Nullable;

import java.sql.*;

public class SQLiteHelper {

    private Connection conn = null;
    private Statement state = null;
    private ResultSet resultSet = null;
    private String dbpath;
    private String sqlCommand = null;
    // 命令
    private final String command_CreateTable = "create table";
    private final String command_DropTable = "drop table";
    private final String command_Insert = "insert into";
    private final String command_Select = "select";
    private final String command_SelectAll = "select *";
    private final String command_Update = "update";
    // 关键字
    private final String PrimaryKey = "primary key";
    private final String Autoincrement = "autoincrement";
    private final String NotNull = "not null";
    // 子句
    private final String From = "from";
    private final String Where = "where";
    private final String Set = "set";

    // Constructor
    public SQLiteHelper(String dbpath) {
        this.dbpath = dbpath;
    }

    // Connection --> Statement
    public Statement connection() throws ClassNotFoundException, SQLException {
        if (conn!=null){
            this.state = conn.createStatement();
            return this.state;
        }
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite://"+dbpath);
        this.state = conn.createStatement();
        this.state.setQueryTimeout(30);
        return this.state;
    }

    // Statement
    public Statement getStatement() throws ClassNotFoundException,SQLException {
        if (state!=null)
            return this.state;
        else
            return this.connection();
    }

    // Create
    public boolean createTable(String dbName, String tableName, String[] columnName, String[] columnType,
                               boolean[] canNull, int isPrimaryKey, boolean isAutoincrement) throws Exception {
        this.check();
        sqlCommand = command_CreateTable + " " + dbName + "." + tableName + "(";
        for (int i = 0; i < columnName.length; i++) {
            sqlCommand += columnName[i] + " " + columnType[i];
            if (i == isPrimaryKey)
                if (isAutoincrement)
                    sqlCommand += " " + PrimaryKey + " " + Autoincrement;
                else
                    sqlCommand += " " + PrimaryKey;
            if (!canNull[i])
                sqlCommand += " " + NotNull;
            if (i!=columnName.length-1)
                sqlCommand += ",";
            else
                sqlCommand += ");";
        }
        this.state.execute(sqlCommand);
        return true;
    }

    public boolean insert(String tableName, String[] columnName, Object[] values) throws Exception {
        this.check();
        sqlCommand = command_Insert + " " + tableName + " (";
        for (int i = 0; i < columnName.length; i++) {
            sqlCommand += columnName[i];
            if (i!=columnName.length-1)
                sqlCommand += ",";
            else
                sqlCommand += ") values (";
        }
        for (int i = 0; i < values.length; i++) {
            if (values[i] instanceof String)
                sqlCommand += "'" + values[i] + "'";
            else
                sqlCommand += values[i];
            if (i!=columnName.length-1)
                sqlCommand += ",";
            else
                sqlCommand += ");";
        }
        state.executeUpdate(sqlCommand);
        return true;
    }

    public boolean insert(String tableName, Object[] values) throws Exception {
        this.check();
        sqlCommand = command_Insert + " " + tableName + " (";
        for (int i = 0; i < values.length; i++) {
            if (values[i] instanceof String)
                sqlCommand += "'" + values[i] + "'";
            else
                sqlCommand += values[i];
            if (i!=values.length-1)
                sqlCommand += ",";
            else
                sqlCommand += ");";
        }
        state.executeUpdate(sqlCommand);
        return true;
    }

    public boolean insertFromTable(String thisTableName, String fromTableName, String[] thisColumnName,
                                   String[] fromColumnName, @Nullable String whereCondition) throws Exception {
        this.check();
        sqlCommand = command_Insert + " " + thisTableName + " (";
        for (int i = 0; i < thisColumnName.length; i++) {
            sqlCommand += thisColumnName[i];
            if (i!=thisColumnName.length-1)
                sqlCommand += ",";
            else
                sqlCommand += ") " + command_Select + " ";
        }
        for (int i = 0; i < fromColumnName.length; i++) {
            sqlCommand += fromColumnName[i];
            if (i!=thisColumnName.length-1)
                sqlCommand += ",";
            else
                sqlCommand += " " + From + " " + fromTableName;
        }
        if (whereCondition!=null)
            sqlCommand += " " + Where + " " + whereCondition + ";";
        else
            sqlCommand += ";";
        state.executeUpdate(sqlCommand);
        return true;
    }

    // Retrieve
    public ResultSet select(String tableName, String[] columnName, @Nullable String whereCondition) throws Exception {
        this.check();
        sqlCommand = command_Select + " ";
        for (int i = 0; i < columnName.length; i++) {
            sqlCommand += columnName[i];
            if (i!=columnName.length-1)
                sqlCommand += ",";
            else
                sqlCommand += " " + From + " " + tableName;
        }
        if (whereCondition!=null)
            sqlCommand += " " + Where + " " + whereCondition + ";";
        else
            sqlCommand += ";";
        this.resultSet = state.executeQuery(sqlCommand);
        return this.resultSet;
    }

    public ResultSet selectAll(String tableName, @Nullable String whereCondition) throws SQLException {
        this.check();
        sqlCommand = command_SelectAll + " " + From + " " + tableName;
        if (whereCondition!=null)
            sqlCommand += " " + Where + " " + whereCondition + ";";
        else
            sqlCommand += ";";
        this.resultSet = state.executeQuery(sqlCommand);
        return this.resultSet;
    }

    // Update
    public boolean update(String tableName, String[] columnName, Object[] values,
                           @Nullable String whereCondition) throws Exception {
        this.check();
        sqlCommand = command_Update + " " + tableName + " " + Set + " ";
        for (int i = 0; i < columnName.length; i++) {
            sqlCommand += columnName[i] + "=" + values[i];
            if (i!=columnName.length-1)
                sqlCommand += ",";
        }
        if (whereCondition!=null)
            sqlCommand += " " + Where + " " + whereCondition + ";";
        else
            sqlCommand += ";";
        state.executeUpdate(sqlCommand);
        return true;
    }

    // Delete
    public boolean dropTable(String dbName, String tableName) throws SQLException {
        this.check();
        sqlCommand = command_DropTable + " " + dbName + "." + tableName + ";";
        state.execute(sqlCommand);
        return true;
    }

    // Check & Init
    private void check() {
        if (this.state==null)
            this.init();
    }

    private void init() {
        try {
            this.connection();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    // Close
    public void close() throws SQLException {
        if (this.state != null && !this.state.isClosed())
            this.state.close();
        if (this.conn != null && !this.conn.isClosed())
            this.conn.close();
    }
}
