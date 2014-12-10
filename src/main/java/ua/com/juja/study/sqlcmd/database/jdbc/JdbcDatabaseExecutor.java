package ua.com.juja.study.sqlcmd.database.jdbc;

import ua.com.juja.study.sqlcmd.config.SqlCmdConfig;
import ua.com.juja.study.sqlcmd.database.DatabaseException;
import ua.com.juja.study.sqlcmd.database.DatabaseExecutor;
import ua.com.juja.study.sqlcmd.database.QueryResult;
import ua.com.juja.study.sqlcmd.database.Row;

import java.sql.*;

import static ua.com.juja.study.sqlcmd.database.Row.ROWS_AFFECTED;

/**
 * Created with IntelliJ IDEA.
 * User: viktor
 * Date: 12/3/14
 * Time: 12:05 PM
 */
public class JdbcDatabaseExecutor implements DatabaseExecutor {

    private Connection connection;

    public JdbcDatabaseExecutor(SqlCmdConfig config) throws DatabaseException {
        //TODO implement init db
    }

    @Override
    public QueryResult executeSqlScript(String sqlScript) throws DatabaseException {
        String query = sqlScript.toLowerCase().trim();
        try {
            if (query.startsWith("select")) {
                ResultSet rs = executeQuery(sqlScript);
                QueryResult result = mapResultSet(rs);
                return result;
            } else {
                int rowsUpdated = executeUpdate(sqlScript);
                QueryResult result = mapUpdatedRows(rowsUpdated);
                return result;
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error when execute script " + e.getMessage(), sqlScript);
        }
    }

    private QueryResult mapUpdatedRows(int rowsUpdated) {
        Row[] rows = new Row[1];
        Row row = new Row();
        row.addColumnValue(ROWS_AFFECTED, rowsUpdated);
        rows[0] = row;
        QueryResult result = new QueryResult(rows);
        result.setColumnNames(ROWS_AFFECTED);
        return result;
    }

    private int executeUpdate(String sqlScript) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlScript);
            return statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    private QueryResult mapResultSet(ResultSet rs) throws SQLException {
        Row[] rows = new Row[getResultSetSize(rs)];
        String[] columnNames = extractColumnsFromResultSet(rs);
        int counter = 0;
        while (rs.next()) {
            Row row = new Row();
            for (String columnName : columnNames) {
                row.addColumnValue(columnName, rs.getObject(columnName));
            }
            rows[counter++] = row;
        }
        QueryResult queryResult = new QueryResult(rows);
        queryResult.setColumnNames(columnNames);
        return queryResult;
    }

    private String[] extractColumnsFromResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        String[] columnNames = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
            columnNames[i] = metaData.getColumnName(i);
        }
        return columnNames;
    }

    private int getResultSetSize(ResultSet rs) throws SQLException {
        int rowCount = 0;
        if (rs.last()) {
            rowCount = rs.getRow();
            rs.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
        }
        return rowCount;
    }

    private ResultSet executeQuery(String query) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }

    }

    @Override
    public String[] getDatabaseList() throws DatabaseException {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void changeDatabase(String databaseName) throws DatabaseException {
        throw new UnsupportedOperationException("Not implemented");
    }

    public static void main(String[] args) throws DatabaseException {
        SqlCmdConfig config = new SqlCmdConfig();
        config.setDbUrl("jdbc:postgresql://localhost:5432/juja");
        config.setDriverName("org.postgresql.Driver");
        config.setUserName("juja_core");
        config.setPassword("juja");
        JdbcDatabaseExecutor executor = new JdbcDatabaseExecutor(config);

        executor.executeSqlScript("select * from participants");
    }
}
