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

		// Start Info
		Logger.setLoggerLevel(loggerLevel.Info);
		Logger.Info(Logger.class, "loggerLevel", Logger.getLoggerLevel().toString());
		Logger.Info(PhigrosDbProjectApiApplication.class, "", "=====================");
		Logger.Info(PhigrosDbProjectApiApplication.class, "PhigrosDB-Project", "Api");
		Logger.Info(PhigrosDbProjectApiApplication.class, "Api-version", "1.0.0-SNAPSHOT");
		if (!Setup.checkDatabaseExists()) {		// Check Database exists
			Logger.Fatal(Setup.class, "checkDatabaseExists", "Database is no exisits!");
			Logger.Info(PhigrosDbProjectApiApplication.class, "", "=====================");
			Logger.Info(PhigrosDbProjectApiApplication.class, "Warn", "App exit in 5 sec.");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			SpringApplication.exit(cac);
		}else {
			Logger.Info(Setup.class, "checkDatabaseExists", "Database is exisits.");
			Logger.Info(PhigrosDbProjectApiApplication.class, "Database-version", "1.0.0");
			Logger.Info(PhigrosDbProjectApiApplication.class, "DatabasePath", Setup.getDatabasePath());
			Logger.Info(PhigrosDbProjectApiApplication.class, "By", "CherryAya");
			Logger.Info(PhigrosDbProjectApiApplication.class, "", "=====================");
		}

	}

}
