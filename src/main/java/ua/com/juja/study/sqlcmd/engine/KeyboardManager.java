package ua.com.juja.study.sqlcmd.engine;

import ua.com.juja.study.sqlcmd.database.DatabaseExecutor;
import ua.com.juja.study.sqlcmd.database.QueryResult;
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

    public QueryResult executeQuery(String query) {
        history.addQueryToTheHead(query);
        try {
            return databaseExecutor.executeSqlScript(query);
        } catch (Exception e) {
            System.out.println("Got exception when execute script" + e.getMessage());
            return new QueryResult(new Row[]{});
        }
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
                addQueryAndExecuteIfQueryFinished(query, line);
            }
        }
    }

    private void addQueryAndExecuteIfQueryFinished(StringBuilder query, String line) {
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
