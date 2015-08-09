package ua.com.juja.study.sqlcmd.engine;

import jline.ConsoleReader;
import ua.com.juja.study.sqlcmd.SqlCmd;
import ua.com.juja.study.sqlcmd.database.DatabaseExecutor;
import ua.com.juja.study.sqlcmd.database.QueryResult;
import ua.com.juja.study.sqlcmd.database.Row;
import ua.com.juja.study.sqlcmd.io.ConsoleFormattedQueryResultWriter;
import ua.com.juja.study.sqlcmd.sql.QueryHistory;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: viktor
 * Date: 10/22/14
 * Time: 11:28 PM
 */
public class KeyboardManager {
    private QueryHistory history;
    private DatabaseExecutor databaseExecutor;

    public KeyboardManager(QueryHistory queryHistory, DatabaseExecutor databaseExecutor) {
        this.history = queryHistory;
        this.databaseExecutor = databaseExecutor;
    }

    public void startListenUserKeyboard() throws IOException {
        String query = null;
        ConsoleFormattedQueryResultWriter queryResultWriter = new ConsoleFormattedQueryResultWriter();
        ConsoleReader consoleReader = new ConsoleReader();
        queryResultWriter.setApplicationContext(SqlCmd.getApplicationContext());
        while ((query = consoleReader.readLine()) != null) {
            if (!query.isEmpty()) {
                queryResultWriter.writeQueryResult(executeQuery(query));
            }
        }
    }

    public QueryResult executeQuery(String query) {
        history.addQueryToTheHead(query);
//        System.out.println("test1 -- " + query);

        if (query.compareTo("q;")==0) {
            System.exit(-1);
        }
        try {
            return databaseExecutor.executeSqlScript(query);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.print("\nGot exception when execute script " + e.getMessage() + "\n\n");
            return new QueryResult(new Row[]{});
        }
    }
}