package ua.com.juja.study.sqlcmd.database.mock;

import ua.com.juja.study.sqlcmd.config.SqlCmdConfig;
import ua.com.juja.study.sqlcmd.database.DatabaseExecutor;
import ua.com.juja.study.sqlcmd.database.Row;

/**
 * Created by VICTOR on 18.10.2014.
 */
public class MockDatabaseExecutor implements DatabaseExecutor {

    @Override
    public boolean connectToDb(SqlCmdConfig config) {
        System.out.println("Database connection established with config " + config);
        return true;
    }

    @Override
    public Row[] executeSqlScript(String sqlScript) {
        return new Row[0];
    }


}
