package ua.com.juja.study.sqlcmd.di;

import ua.com.juja.study.sqlcmd.database.DatabaseExecutor;
import ua.com.juja.study.sqlcmd.engine.KeyboardManager;
import ua.com.juja.study.sqlcmd.sql.QueryHistory;

/**
 * Created by VICTOR on 23.11.2014.
 */
public interface ApplicationContext {

    public QueryHistory getQueryHistory();

    public DatabaseExecutor getDatabaseExecutor();

    public KeyboardManager getKeyboardManager();
}
