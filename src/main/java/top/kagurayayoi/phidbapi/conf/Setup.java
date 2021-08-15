package top.kagurayayoi.phidbapi.conf;

import org.springframework.boot.system.ApplicationHome;
import top.kagurayayoi.database.SQLiteHelper;
import top.kagurayayoi.logger.Logger;
import top.kagurayayoi.phidbapi.entities.DatabaseVersion;

import java.io.File;
import java.sql.ResultSet;

// 配置项类
public class Setup {

    // 数据库版本 (默认) (保持最新)
    private static DatabaseVersion dbVersion = new DatabaseVersion(1,0,1);

    // 数据库版本选取
    public static String getDatabaseVersion(){
        return dbVersion.toString();
    }

    // 数据库版本获取 from database select
    public static void setDatabaseVersion(){
        try {
            SQLiteHelper helper = new SQLiteHelper(getDatabasePath());
            helper.connection();
            ResultSet rs = helper.select("main.Info", new String[]{"Database_Version"}, null);
            while (rs.next()){
                String[] version = rs.getString("Database_Version").split("\\.");
                dbVersion.setX(Integer.parseInt(version[0]))
                        .setY(Integer.parseInt(version[1]))
                        .setZ(Integer.parseInt(version[2]));
            }
            helper.close();
        }catch (Exception ex){
            Logger.Warn(Setup.class, "DatabaseVersion", "获取数据库版本失败了，使用默认版本:" + getDatabaseVersion());
        }
    }

    // 数据库名字（默认）(保持最新)
    private static String defaultDB = "PhigrosDB-1.0.1-1.6.11.db";

    // 数据库路径
    private static String dbPath = null;

    // 设置数据库路径
    public static void setDatabasePath(String path){
        dbPath = path;
        Logger.Info(Setup.class, "DatabasePath", "数据库路径设置为:" + path);
        setDatabaseVersion();
    }

    // 数据库路径选取
    public static String getDatabasePath() {
        if (dbPath != null)
            return dbPath;  // 自定义路径
        ApplicationHome home = new ApplicationHome(Setup.class);
        return home.getSource().getParentFile().toString() + "\\" + defaultDB;  // 默认路径
    }

    // 检查数据库是否存在
    public static boolean checkDatabaseExists() {
        File database = new File(Setup.getDatabasePath());
        return database.exists();
    }

}
