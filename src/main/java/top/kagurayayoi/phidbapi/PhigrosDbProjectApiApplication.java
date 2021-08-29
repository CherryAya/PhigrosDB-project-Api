package top.kagurayayoi.phidbapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import top.kagurayayoi.logger.Logger;
import top.kagurayayoi.logger.loggerLevel;
import top.kagurayayoi.phidbapi.conf.Setup;

@SpringBootApplication
public class PhigrosDbProjectApiApplication {

	public static void main(String[] args) {

		// SpringBoot Run
		ConfigurableApplicationContext cac =
				SpringApplication.run(PhigrosDbProjectApiApplication.class, args);

		// var
		boolean setLoggerLevelFlag = false;

		// check args
		if (args.length != 0) {
			for (int i = 0; i < args.length; i++) {
				Logger.Info(PhigrosDbProjectApiApplication.class, "arg[" + i + "]", args[i]);
				if (args[i].startsWith("--dbpath:")) { // --dbpath:xxx
					String path = args[i].substring(9);
					Setup.setDatabasePath(path);
				}else if (args[i].startsWith("--logger.level:")) { // --dbpath:xxx
					Logger.setLoggerLevel(args[i].substring(15));
					setLoggerLevelFlag = true;
				}
			}
		}else {
			Logger.Info(PhigrosDbProjectApiApplication.class, "args", "没有参数输入,使用默认数据库位置");
		}

		// Start Info
		if (!setLoggerLevelFlag)
			Logger.setLoggerLevel(loggerLevel.Info);
		Logger.Info(Logger.class, "loggerLevel", Logger.getLoggerLevel().toString());
		Logger.Info(PhigrosDbProjectApiApplication.class, "", "=====================");
		Logger.Info(PhigrosDbProjectApiApplication.class, "PhigrosDB-Project", "Api");
		Logger.Info(PhigrosDbProjectApiApplication.class, "Api-version", Setup.getApiVersion());
		if (!Setup.checkDatabaseExists()) {		// Check Database exists
			Logger.Fatal(Setup.class, "checkDatabaseExists", "数据库不存在或设置的数据库地址不正确!");
			Logger.Info(PhigrosDbProjectApiApplication.class, "", "=====================");
			Logger.Info(PhigrosDbProjectApiApplication.class, "Warn", "App exit in 5 sec.");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			SpringApplication.exit(cac);
		}else {
			Setup.setDatabaseVersion();
			Logger.Info(Setup.class, "checkDatabaseExists", "数据库存在");
			Logger.Info(PhigrosDbProjectApiApplication.class, "Database-version", Setup.getDatabaseVersion());
			Logger.Info(PhigrosDbProjectApiApplication.class, "DatabasePath", Setup.getDatabasePath());
			Logger.Info(PhigrosDbProjectApiApplication.class, "By", "CherryAya");
			Logger.Info(PhigrosDbProjectApiApplication.class, "", "=====================");
		}

	}

}
