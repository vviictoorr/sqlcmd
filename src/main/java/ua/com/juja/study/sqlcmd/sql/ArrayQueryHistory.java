package ua.com.juja.study.sqlcmd.sql;

/**
 * Created by VICTOR on 31.10.2014.
 */
public class ArrayQueryHistory implements QueryHistory {
    private static final int NO_QUERY_IN_ARRAY = -1;
    private int indexQuery = NO_QUERY_IN_ARRAY;
    private static String[] queryCount = new String[QUERY_BUFFER_COUNT];

    @Override
    public String getNextQuery() {
        if (queryCount.length != 0 && indexQuery >= 0) {
            return queryCount[indexQuery--];
        }
        return null;
    }

    @Override
    public String getPreviousQuery() {
        if (queryCount.length != 0 && indexQuery < QUERY_BUFFER_COUNT) {
            return queryCount[++indexQuery];
        }
        return null;
    }

    @Override
    public void addQueryToTheHead(String query) {
        if (indexQuery < QUERY_BUFFER_COUNT - 1) {
            queryCount[++indexQuery] = query;
        } else {
            System.arraycopy(queryCount, 0, queryCount, 1,
                    QUERY_BUFFER_COUNT - 1);
            queryCount[0] = query;
        }
    }
}
