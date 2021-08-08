package top.kagurayayoi.phidbapi.entities;

public class ExceptionResult {

    private StackTraceElement[] StackTrace;

    public StackTraceElement[] getStackTrace() {
        return StackTrace;
    }

    public ExceptionResult setStackTrace(StackTraceElement[] stackTrace) {
        StackTrace = stackTrace;
        return this;
    }
}
