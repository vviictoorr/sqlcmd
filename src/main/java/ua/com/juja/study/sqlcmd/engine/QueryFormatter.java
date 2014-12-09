package ua.com.juja.study.sqlcmd.engine;

import ua.com.juja.study.sqlcmd.database.DatabaseException;
import ua.com.juja.study.sqlcmd.database.QueryResult;
import ua.com.juja.study.sqlcmd.database.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VICTOR on 03.12.2014.
 */
public class QueryFormatter {
    public static final int ALIGN_LEFT = -1;
    public static final int ALIGN_CENTER = 0;
    public static final int ALIGN_RIGHT = 1;

    public String formatQueryResult(QueryResult queryResult) {

        StringBuilder tableBuilder = new StringBuilder();
        try {
            String[] header = queryResult.getColumnNames();
            int columnCount = header.length;
            String[][] data = convertRowListToMatrix(header, queryResult.getRowList());

            List<Integer> columnLength = calcLengthColumns(columnCount, header, data);

            if (header != null && header.length > 0) {
                tableBuilder.append(drawLine(columnCount, columnLength));
                tableBuilder.append(drawData(columnCount, columnLength, header, ALIGN_CENTER));
            }
            tableBuilder.append(drawLine(columnCount, columnLength));
            String[] rowData = null;
            for (int i = 0; i < data.length; i++) {
                rowData = new String[columnCount];
                for (int j = 0; j < columnCount; j++) {
                    if (j < data[i].length) {
                        rowData[j] = data[i][j];
                    } else {
                        rowData[j] = "";
                    }
                }
                tableBuilder.append(drawData(columnCount, columnLength, rowData, ALIGN_LEFT));
            }
            tableBuilder.append(drawLine(columnCount, columnLength));

        } catch (DatabaseException e) {
            return e.getMessage();
        }
        return tableBuilder.toString();
    }

    private String drawData(int colCount, List<Integer> colMaxLenList, String[] data, int align) {
        StringBuilder rowBuilder = new StringBuilder();
        String formattedData = null;
        for (int i = 0; i < colCount; i++) {
            formattedData = i < data.length ? data[i] : "";
            formattedData = formattedData.replace("\n", "").replace("\r", "");
            formattedData = "| " + alignData(colMaxLenList.get(i), formattedData, align) + " ";
            if (i + 1 == colCount) {
                formattedData += "|";
            }
            rowBuilder.append(formattedData);
        }
        return rowBuilder.append("\n").toString();
    }

    private String alignData(int columnLength, String dataCell, int alignCell) {
        if (dataCell.length() > columnLength) {
            return dataCell;
        }
        boolean toggle = true;
        while (dataCell.length() < columnLength) {
            if (alignCell == ALIGN_LEFT) {
                dataCell = dataCell + " ";
            } else if (alignCell == ALIGN_RIGHT) {
                dataCell = " " + dataCell;
            } else if (alignCell == ALIGN_CENTER) {
                if (toggle) {
                    dataCell = " " + dataCell;
                    toggle = false;
                } else {
                    dataCell = dataCell + " ";
                    toggle = true;
                }
            }
        }
        return dataCell;
    }

    private String drawLine(int colCount, List<Integer> colMaxLenList) {
        StringBuilder lineBuilder = new StringBuilder();
        int colWidth = 0;
        for (int i = 0; i < colCount; i++) {
            colWidth = colMaxLenList.get(i) + 3;
            for (int j = 0; j < colWidth; j++) {
                if (j == 0) {
                    lineBuilder.append("+");
                } else if ((i + 1 == colCount && j + 1 == colWidth)) {
                    lineBuilder.append("-+");
                } else {
                    lineBuilder.append("-");
                }
            }
        }
        return lineBuilder.append("\n").toString();
    }

    private List<Integer> calcLengthColumns(int colCount, String[] header, String[][] data) {
        List<Integer> colLength = new ArrayList<>(colCount);
        List<String> colData = null;
        int maxLength;
        for (int i = 0; i < colCount; i++) {
            colData = new ArrayList<>();
            if (header != null && i < header.length) {
                colData.add(header[i]);
            }
            for (int j = 0; j < data.length; j++) {
                if (i < data[j].length) {
                    colData.add(data[j][i]);
                } else {
                    colData.add("");
                }
            }
            maxLength = maxCellLength(colData);
            colLength.add(maxLength);
        }
        return colLength;
    }

    private int maxCellLength(List<String> colData) {
        int maxLength = 0;
        for (int i = 0; i < colData.size(); i++) {
            maxLength = Math.max(colData.get(i).length(), maxLength);
        }
        return maxLength;
    }

    private String[][] convertRowListToMatrix(String[] header, Row[] rowList) {
        String[][] rowArray = new String[rowList.length][header.length];
        for (int i = 0; i < rowList.length; i++) {
            for (int j = 0; j < header.length; j++) {
                rowArray[i][j] = rowList[i].getValue(header[j]).toString();
            }
        }
        return rowArray;
    }
}
