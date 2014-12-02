package ua.com.juja.study.sqlcmd.database.mock;

import ua.com.juja.study.sqlcmd.config.SqlCmdConfig;
import ua.com.juja.study.sqlcmd.database.DatabaseExecutor;
import ua.com.juja.study.sqlcmd.database.QueryResult;
import ua.com.juja.study.sqlcmd.database.Row;

/**
 * Created with IntelliJ IDEA.
 * User: viktor
 * Date: 29/10/14
 * Time: 22:22 PM
 */
public class MockDatabaseExecutor implements DatabaseExecutor {

    private static final String[] MOCK_DATABASES = {"juja", "java", "database1"};
    private static String databases;
    private int currentDatabaseIndex = 0;

    public String getCurrentDatabase() {
        return MOCK_DATABASES[currentDatabaseIndex];
    }

    @Override
    public boolean connectToDb(SqlCmdConfig config) {
        System.out.println("Database connection established with config " + config);
        return true;
    }

    @Override
    public QueryResult executeSqlScript(String sqlScript) {
        System.out.println("Executed script " + sqlScript);
        return new QueryResult(new Row[]{});
    }

    @Override
    public String[] getDatabaseList() {
        return MOCK_DATABASES;
    }

    @Override
    public void changeDatabase(String databaseName) {
        boolean databaseWasFound = false;
        for (int i = 0; i < MOCK_DATABASES.length; i++) {
            if (MOCK_DATABASES[i].equals(databaseName)) {
                currentDatabaseIndex = i;
                databaseWasFound = true;
                break;
            }
        }
        if (!databaseWasFound) {
            System.out.println("Wrong database name " + databaseName);
        }
    }

}