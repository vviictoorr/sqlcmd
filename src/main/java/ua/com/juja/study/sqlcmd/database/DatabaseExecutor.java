package ua.com.juja.study.sqlcmd.database;

import ua.com.juja.study.sqlcmd.config.SqlCmdConfig;

/**
 * Created by VICTOR on 18.10.2014.
 */
public interface DatabaseExecutor {

    public abstract boolean connectToDb(SqlCmdConfig config);

    public abstract Row[] executeSqlScript(String sqlScript);
}
