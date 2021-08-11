package top.kagurayayoi.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class loggerService {

    private StringBuilder builder;
    private Date date;
    private SimpleDateFormat formatter;
    private String time;

    public boolean Debug(Class thisClass, Object type, Object message) {
        this.init();
        time = formatter.format(date.getTime());
        builder.append(time + " DEBUG -- [ ");
        builder.append(thisClass.toString() + " ] ");
        builder.append(type + " : " + message);
        System.out.println(builder);
        return true;
    }

    public boolean Info(Class thisClass, Object type, Object message) {
        this.init();
        time = formatter.format(date.getTime());
        builder.append(time + " INFO -- [ ");
        builder.append(thisClass.toString() + " ] ");
        builder.append(type + " : " + message);
        System.out.println(builder);
        return true;
    }

    public boolean Warn(Class thisClass, Object type, Object message) {
        this.init();
        time = formatter.format(date.getTime());
        builder.append(time + " WARN -- [ ");
        builder.append(thisClass.toString() + " ] ");
        builder.append(type + " : " + message);
        System.out.println(builder);
        return true;
    }

    public boolean Exception(Class thisClass, Object type, Object message) {
        this.init();
        time = formatter.format(date.getTime());
        builder.append(time + " Exception -- [ ");
        builder.append(thisClass.toString() + " ] ");
        builder.append(type + " : " + message);
        System.out.println(builder);
        return true;
    }

    public boolean Fatal(Class thisClass, Object type, Object message) {
        this.init();
        time = formatter.format(date.getTime());
        builder.append(time + " Fatal -- [ ");
        builder.append(thisClass.toString() + " ] ");
        builder.append(type + " : " + message);
        System.out.println(builder);
        return true;
    }

    private void init(){
        builder = new StringBuilder();
        date = new Date();
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
