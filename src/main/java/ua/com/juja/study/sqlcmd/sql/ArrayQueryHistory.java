package ua.com.juja.study.sqlcmd.sql;

/**
 * Created by VICTOR on 31.10.2014.
 */
public class ArrayQueryHistory implements QueryHistory {
    private static final int NO_QUARY_IN_ARRAY = -1;
    private int indexQuary = NO_QUARY_IN_ARRAY;
    private static String[] queryCount = new String[QUERY_BUFFER_COUNT];

    @Override
    public String getNextQuery() {
        if (indexQuary < 0) {
            indexQuary = QUERY_BUFFER_COUNT - 1;
            return queryCount[indexQuary--];
        }
        return queryCount[indexQuary--];
    }

    @Override
    public String getPreviousQuery() {
        if (indexQuary == QUERY_BUFFER_COUNT - 2) {
            indexQuary = 0;
            return queryCount[indexQuary++];
        }
        return queryCount[indexQuary++];
    }

    @Override
    public void addQueryToTheHead(String query) {
        if (indexQuary < QUERY_BUFFER_COUNT - 1) {
            indexQuary++;
            queryCount[indexQuary] = query;
        } else {
            queryCount[0] = query;
        }
    }
}
