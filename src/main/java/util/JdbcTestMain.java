package util;

/**
 * Created by VICTOR on 10.12.2014.
 */
public class JdbcTestMain {

//    public static void main(String[] args) throws DatabaseException {
//        SqlCmdConfig config = new SqlCmdConfig();
//        config.setDbUrl("jdbc:postgresql://localhost:5432/juja");
//        config.setDriverName("org.postgresql.Driver");
//        config.setUserName("juja_core");
//        config.setPassword("juja");
//        JdbcDatabaseExecutor executor = new JdbcDatabaseExecutor(config);
//
//        QueryResult result = executor.executeSqlScript("select * from participations");
//        printResult(result);
//    }
//
//    private static void printResult(QueryResult result) throws DatabaseException {
//        String[] columns = result.getColumnNames();
//        for (String column : columns) {
//            System.out.print(column + " | ");
//        }
//        System.out.println();
//        Row[] rows = result.getRowList();
//        for (Row row : rows) {
//            for (String column : columns) {
//                System.out.print(row.getValue(column) + "|");
//            }
//            System.out.println();
//        }
//    }
}
