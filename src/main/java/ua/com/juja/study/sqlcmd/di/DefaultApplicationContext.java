package ua.com.juja.study.sqlcmd.di;

import ua.com.juja.study.sqlcmd.config.SqlCmdConfig;
import ua.com.juja.study.sqlcmd.database.DatabaseExecutor;
import ua.com.juja.study.sqlcmd.database.mock.MockDatabaseExecutor;
import ua.com.juja.study.sqlcmd.engine.KeyboardManager;
import ua.com.juja.study.sqlcmd.sql.ArrayQueryHistory;
import ua.com.juja.study.sqlcmd.sql.QueryHistory;

/**
 * Created by VICTOR on 23.11.2014.
 */
public class DefaultApplicationContext implements ApplicationContext {

    private SqlCmdConfig config;
    private QueryHistory queryHistory;
    private DatabaseExecutor databaseExecutor;
    private KeyboardManager keyboardManager;

    public DefaultApplicationContext(SqlCmdConfig config) {
        this.config = config;
        databaseExecutor = new MockDatabaseExecutor();
        queryHistory = new ArrayQueryHistory();
        keyboardManager = new KeyboardManager(queryHistory, databaseExecutor);
    }

    @Override
    public QueryHistory getQueryHistory() {
        return queryHistory;
    }

    @Override
    public DatabaseExecutor getDatabaseExecutor() {
        return databaseExecutor;
    }

    @Override
    public KeyboardManager getKeyboardManager() {
        return keyboardManager;
    }
}
