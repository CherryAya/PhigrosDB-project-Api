package top.kagurayayoi.phidbapi.conf;

import org.springframework.boot.system.ApplicationHome;

// 配置项类
public class Setup {

    // 数据库路径选取
    public static String getDatabasePath(){
        ApplicationHome home = new ApplicationHome(Setup.class);
        return home.getSource().getParentFile().toString() + "\\PhigrosDB-1.0.0-1.6.11.db";
    }

}
