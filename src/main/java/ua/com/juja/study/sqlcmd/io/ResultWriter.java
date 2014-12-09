package ua.com.juja.study.sqlcmd.io;

import ua.com.juja.study.sqlcmd.database.QueryResult;

import java.io.IOException;

/**
 * Created by VICTOR on 03.12.2014.
 */
public interface ResultWriter {
    public void writeQueryResult(QueryResult queryResult) throws IOException;
}
