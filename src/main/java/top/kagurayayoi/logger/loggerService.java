package top.kagurayayoi.logger;

public interface LoggerService {
    boolean Debug(Class thisClass, Object type, Object message);
    boolean Info(Class thisClass, Object type, Object message);
    boolean Warn(Class thisClass, Object type, Object message);
    boolean Exception(Class thisClass, Object type, Object message);
    boolean Fatal(Class thisClass, Object type, Object message);
}
