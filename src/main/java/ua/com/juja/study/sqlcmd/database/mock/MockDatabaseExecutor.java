package ua.com.juja.study.sqlcmd.database.mock;

import ua.com.juja.study.sqlcmd.database.DatabaseExecutor;
import ua.com.juja.study.sqlcmd.database.QueryResult;
import ua.com.juja.study.sqlcmd.database.Row;

/**
 * Created with IntelliJ IDEA.
 * User: viktor
 * Date: 10/15/14
 * Time: 11:42 PM
 */
public class MockDatabaseExecutor implements DatabaseExecutor {

    @Override
    public QueryResult executeSqlScript(String sqlScript) {
        return new QueryResult(new Row[]{});
    }

    @Override
    public String[] getDatabaseList() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void changeDatabase(String databaseName) {
        throw new UnsupportedOperationException("Not implemented");
    }

}
