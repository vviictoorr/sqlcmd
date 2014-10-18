package ua.com.juja.study.sqlcmd.database.mock;

import ua.com.juja.study.sqlcmd.config.SqlCmdConfig;
import ua.com.juja.study.sqlcmd.database.DatabaseConnection;
import ua.com.juja.study.sqlcmd.database.DatabaseExecutor;

/**
 * Created by VICTOR on 18.10.2014.
 */
public class MockDatabaseExecutor implements DatabaseExecutor {
    @Override
    public DatabaseConnection connectToDb(SqlCmdConfig config) {
        System.out.println("Database connection established with config " + config);
        return new DatabaseConnection();
    }
}
