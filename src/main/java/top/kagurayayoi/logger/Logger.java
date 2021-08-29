package top.kagurayayoi.logger;

import top.kagurayayoi.phidbapi.PhigrosDbProjectApiApplication;

public class Logger {

    private static loggerLevel level = loggerLevel.Info;
    private static LoggerService service = new defaultLoggerService();

    public static void setLoggerService(LoggerService newLoggerService) {
        Logger.service = newLoggerService;
    }

    public static boolean setLoggerLevel(loggerLevel level) {
        if (level.ordinal() < loggerLevel.Debug.ordinal() || level.ordinal() > loggerLevel.None.ordinal()) {
            Logger.Exception(Logger.class, "initLoggerLevel", "loggerLevel out of range.");
            return false;
        }
        Logger.level = level;
        Info(Logger.class, "Logger", "Set Logger.Level --> " + level);
        return true;
    }

    public static boolean setLoggerLevel(String level) {
        level = level.toLowerCase();
        switch (level) {
            case "debug":
                setLoggerLevel(loggerLevel.Debug);
                break;
            case "info":
                setLoggerLevel(loggerLevel.Info);
                break;
            case "warn":
                setLoggerLevel(loggerLevel.Warn);
                break;
            case "exception":
                setLoggerLevel(loggerLevel.Exeption);
                break;
            case "fatal":
                setLoggerLevel(loggerLevel.Fatel);
                break;
            case "none":
                setLoggerLevel(loggerLevel.None);
                break;
            default:
                Warn(Logger.class, "Logger", "Unknown Level --> " + level);
                return false;
        }
        return true;
    }

    public static loggerLevel getLoggerLevel() {
        return Logger.level;
    }

    public static synchronized boolean Debug(Class thisClass, Object type, Object message) {
        if (level.ordinal() > loggerLevel.Debug.ordinal())
            return false;
        service.Debug(thisClass, type, message);
        return true;
    }

    public static synchronized boolean Info(Class thisClass, Object type, Object message) {
        if (level.ordinal() > loggerLevel.Info.ordinal())
            return false;
        service.Info(thisClass, type, message);
        return true;
    }

    public static synchronized boolean Warn(Class thisClass, Object type, Object message) {
        if (level.ordinal() > loggerLevel.Warn.ordinal())
            return false;
        service.Warn(thisClass, type, message);
        return true;
    }

    public static synchronized boolean Exception(Class thisClass, Object type, Object message) {
        if (level.ordinal() > loggerLevel.Exeption.ordinal())
            return false;
        service.Exception(thisClass, type, message);
        return true;
    }

    public static synchronized boolean Fatal(Class thisClass, Object type, Object message) {
        if (level.ordinal() > loggerLevel.Fatel.ordinal())
            return false;
        service.Fatal(thisClass, type, message);
        return true;
    }
}
