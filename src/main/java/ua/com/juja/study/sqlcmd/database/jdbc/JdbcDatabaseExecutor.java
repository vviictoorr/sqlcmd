package ua.com.juja.study.sqlcmd.database.jdbc;

import ua.com.juja.study.sqlcmd.config.SqlCmdConfig;
import ua.com.juja.study.sqlcmd.database.DatabaseException;
import ua.com.juja.study.sqlcmd.database.DatabaseExecutor;
import ua.com.juja.study.sqlcmd.database.QueryResult;
import ua.com.juja.study.sqlcmd.database.Row;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        initConnection(config);
    }

    private void initConnection(SqlCmdConfig config) throws DatabaseException {
        try {
            Class.forName(config.getDriverName());
            connection = DriverManager.getConnection(config.getDbUrl(), config.getUserName(), config.getPassword());
        } catch (ClassNotFoundException | SQLException e) {
            throw new DatabaseException("Problems with init connection " + e.getMessage());
        }
    }

    @Override
    public QueryResult executeSqlScript(String sqlScript) throws DatabaseException {
        String query = sqlScript.toLowerCase().trim();
        PreparedStatement statement = null;
        try {
            if (query.startsWith("select")) {
                statement = connection.prepareStatement(sqlScript);
                ResultSet rs = statement.executeQuery();
                QueryResult result = mapResultSet(rs);
                return result;
            } else {
                statement = connection.prepareStatement(sqlScript);
                int rowsUpdated = statement.executeUpdate();
                QueryResult result = mapUpdatedRows(rowsUpdated);
                return result;
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error when execute script " + e.getMessage(), sqlScript);
        } finally {
            closeStatement(statement);
        }
    }

    private void closeStatement(PreparedStatement statement) throws DatabaseException {
        if (statement == null) return;
        try {
            statement.close();
        } catch (SQLException e) {
            throw new DatabaseException("Error when closing statement " + e.getMessage());
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

    private QueryResult mapResultSet(ResultSet rs) throws SQLException {
        List<Row> rows = new ArrayList<>();
        String[] columnNames = extractColumnsFromResultSet(rs);
        while (rs.next()) {
            Row row = new Row();
            for (String columnName : columnNames) {
                row.addColumnValue(columnName, rs.getObject(columnName));
            }
            rows.add(row);
        }
        Row[] arrayRows = rows.toArray(new Row[rows.size()]);
        QueryResult queryResult = new QueryResult(arrayRows);
        queryResult.setColumnNames(columnNames);
        return queryResult;
    }

    private String[] extractColumnsFromResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        String[] columnNames = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
            columnNames[i] = metaData.getColumnName(i + 1);
        }
        return columnNames;
    }

    @Override
    public String[] getDatabaseList() throws DatabaseException {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void changeDatabase(String databaseName) throws DatabaseException {
        throw new UnsupportedOperationException("Not implemented");
    }
}
