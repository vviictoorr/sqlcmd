package ua.com.juja.study.sqlcmd.sql;

import java.util.ArrayList;

/**
 * Created by VICTOR on 31.10.2014.
 */
public class ArrayQueryHistory implements QueryHistory {

    private static ArrayList<String> queryCount = new ArrayList<String>();
    private static final int QUERY_COUNT = 4;

    @Override
    public String getNextQuery() {
        if (!queryCount.isEmpty()) {
            return queryCount.get(QUERY_COUNT - 1);
        }
        return null;
    }

    @Override
    public String getPreviousQuery() {
        if (!queryCount.isEmpty()) {
            return queryCount.get(1);
        }
        return null;
    }

    @Override
    public void addQueryToTheHead(String query) {
        if (queryCount.size() >= QUERY_COUNT) {
            queryCount.remove(QUERY_COUNT - 1);
        }
        queryCount.add(0, query);
    }

}
