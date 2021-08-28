package top.kagurayayoi.phidbapi.conf;

import org.springframework.boot.system.ApplicationHome;
import top.kagurayayoi.database.SQLiteHelper;
import top.kagurayayoi.logger.Logger;
import top.kagurayayoi.phidbapi.entities.Info;
import top.kagurayayoi.version.PublishType;
import top.kagurayayoi.version.Version;

import java.io.File;
import java.sql.ResultSet;

// 配置项类
public class Setup {

    // Api版本
    private static Version apiVersion = new Version(1,1,0,PublishType.SNAPSHOT);

    // 选取Api版本
    public static Version getApiVersion(){
        return apiVersion;
    }

    // 数据库版本 (默认) (保持最新)
    private static Version dbVersion = new Version(1,1,0,PublishType.SNAPSHOT);

    // 数据库版本选取
    public static Version getDatabaseVersion(){
        return dbVersion;
    }

    // 数据库版本获取 from database select
    public static void setDatabaseVersion(){
        try {
            SQLiteHelper helper = new SQLiteHelper(getDatabasePath());
            helper.connection();
            ResultSet rs = helper.select("main.Info", new String[]{Info.columnName[1], Info.columnName[2]}, null);
            while (rs.next()){
                String[] version = rs.getString(Info.columnName[1]).split("\\.");
                dbVersion.setX(Integer.parseInt(version[0]))
                        .setY(Integer.parseInt(version[1]))
                        .setZ(Integer.parseInt(version[2]));
                dbVersion.setType(new Info().setPublish_Type(rs.getString(Info.columnName[2])).getPublish_Type());
            }
            helper.close();
        }catch (Exception ex){
            Logger.Warn(Setup.class, "DatabaseVersion", "获取数据库版本失败了，使用默认版本:" + getDatabaseVersion());
        }
    }

    // 数据库名字（默认）(保持最新)
    private static String defaultDB = "PhigrosDB-1.1.0-2.0.0-RELEASE.db";

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
