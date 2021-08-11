package top.kagurayayoi.logger;

public class Logger {

    private static loggerLevel level = loggerLevel.Info;
    private static loggerService service;

    public static boolean initLoggerLevel(loggerLevel level){
        if (level.ordinal() < loggerLevel.Debug.ordinal() || level.ordinal() > loggerLevel.None.ordinal()) {
            Logger.Exception(Logger.class, "initLoggerLevel", "loggerLevel out of range.");
            return false;
        }
        Logger.level = level;
        return true;
    }

    public static loggerLevel getLoggerLevel(){
        return Logger.level;
    }

    public static synchronized boolean Debug(Class thisClass, Object type, Object message) {
        Logger.init();
        if (level.ordinal() != loggerLevel.Debug.ordinal())
            return false;
        service.Debug(thisClass, type, message);
        return true;
    }

    public static synchronized boolean Info(Class thisClass, Object type, Object message) {
        Logger.init();
        if (level.ordinal() < loggerLevel.Info.ordinal())
            return false;
        service.Info(thisClass, type, message);
        return true;
    }

    public static synchronized boolean Warn(Class thisClass, Object type, Object message) {
        Logger.init();
        if (level.ordinal() < loggerLevel.Warn.ordinal())
            return false;
        service.Warn(thisClass, type, message);
        return true;
    }

    public static synchronized boolean Exception(Class thisClass, Object type, Object message) {
        Logger.init();
        if (level.ordinal() == loggerLevel.None.ordinal())
            return false;
        service.Exception(thisClass, type, message);
        return true;
    }

    public static synchronized boolean Fatal(Class thisClass, Object type, Object message) {
        Logger.init();
        if (level.ordinal() == loggerLevel.None.ordinal())
            return false;
        service.Fatal(thisClass, type, message);
        return true;
    }

    private static void init(){
        service = new loggerService();
    }
}
