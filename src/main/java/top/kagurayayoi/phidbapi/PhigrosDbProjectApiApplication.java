package top.kagurayayoi.phidbapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.kagurayayoi.logger.Logger;
import top.kagurayayoi.logger.loggerLevel;
import top.kagurayayoi.phidbapi.conf.Setup;

@SpringBootApplication
public class PhigrosDbProjectApiApplication {

	public static void main(String[] args) {

		// SpringBoot Run
		SpringApplication.run(PhigrosDbProjectApiApplication.class, args);

		// Info
		Logger.initLoggerLevel(loggerLevel.Info);
		Logger.Info(PhigrosDbProjectApiApplication.class, "Api-version", "1.0.0-SNAPSHOT");
		Logger.Info(PhigrosDbProjectApiApplication.class, "Database-version", "1.0.0");
		Logger.Info(PhigrosDbProjectApiApplication.class, "DatabasePath", Setup.getDatabasePath());
		Logger.Info(PhigrosDbProjectApiApplication.class, "By", "CherryAya");
	}

}
