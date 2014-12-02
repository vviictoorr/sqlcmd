package ua.com.juja.study.sqlcmd.database;

/**
 * Created by VICTOR on 02.12.2014.
 */
public class DatabaseException extends Exception {
    private String query;

    public DatabaseException() {
    }

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, String query) {
        super(message);
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
