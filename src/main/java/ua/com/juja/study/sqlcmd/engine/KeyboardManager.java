package ua.com.juja.study.sqlcmd.engine;

import ua.com.juja.study.sqlcmd.database.DatabaseExecutor;
import ua.com.juja.study.sqlcmd.database.Row;
import ua.com.juja.study.sqlcmd.sql.QueryHistory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: viktor
 * Date: 10/22/14
 * Time: 11:28 PM
 */
public class KeyboardManager {
    private QueryHistory history;
    private DatabaseExecutor databaseExecutor;

    public KeyboardManager(QueryHistory history, DatabaseExecutor databaseExecutor) {
        this.history = history;
        this.databaseExecutor = databaseExecutor;
    }

    public Row[] executeQuery(String query) {
        history.addQueryToTheHead(query);
        return databaseExecutor.executeSqlScript(query);
    }

    public void startListenUserKeyboard() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean quit = false;
        StringBuilder query = new StringBuilder();
        while (!quit) {
            String line = reader.readLine();
            if ("\\q".equals(line)) {
                quit = true;
            } else if (!line.isEmpty()) {
                addQuery(query, line);
            }
        }
    }

    private void addQuery(StringBuilder query, String line) {
        query.append(line);
        char endChar = line.charAt(line.length() - 1);
        if (endChar == ';') {
            executeQuery(query.toString());
            query.delete(0, query.length());
        } else {
            query.append(" ");
        }
    }
}
