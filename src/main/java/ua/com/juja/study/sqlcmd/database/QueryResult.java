package ua.com.juja.study.sqlcmd.database;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: viktor
 * Date: 11/27/14
 * Time: 12:30 PM
 */
public class QueryResult {
    private Row[] rowList;
    private Future<Row[]> futureRowList;
    private String[] columnNames;

    public QueryResult(Row[] rowList) {
        this.rowList = rowList;
    }

    public QueryResult(Future<Row[]> futureRowList) {
        this.futureRowList = futureRowList;
    }

    public Row[] getRowList() throws DatabaseException {
        if (futureRowList == null) {
            return rowList;
        }
        try {
            return futureRowList.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new DatabaseException("\nException with async execution " + e.getMessage() + "\n\n");
        }
    }

    public boolean isReady() {
        if (futureRowList == null) {
            return true;
        }
        return futureRowList.isDone();
    }

    public String[] getColumnNames() throws DatabaseException {
        if (columnNames == null) {
            Row[] rows = getRowList();
            if (rows.length > 0) {
                columnNames = rows[0].getColumnNames();
                return columnNames;
            } else
                return new String[0];
        }
        return columnNames;
    }

    public void setColumnNames(String... columnNames) {
        this.columnNames = columnNames;
    }
}