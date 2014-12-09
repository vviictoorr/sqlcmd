package ua.com.juja.study.sqlcmd.io;

import ua.com.juja.study.sqlcmd.database.QueryResult;
import ua.com.juja.study.sqlcmd.di.ApplicationContext;
import ua.com.juja.study.sqlcmd.engine.QueryFormatter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/**
 * Created by VICTOR on 03.12.2014.
 */
public class ConsoleFormattedQueryResultWriter implements ResultWriter {

    private QueryFormatter queryFormatter = new QueryFormatter();
    private Writer writer;
    private ApplicationContext applicationContext;

    public ConsoleFormattedQueryResultWriter() {
        writer = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    public ConsoleFormattedQueryResultWriter(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void writeQueryResult(final QueryResult queryResult) throws IOException {
        if (queryResult.isReady()) {
            writeQueryResultSync(queryResult);
        } else {
            writeQueryResultAsync(queryResult);
        }
    }

    private void writeQueryResultSync(QueryResult queryResult) throws IOException {
        writer.write(queryFormatter.formatQueryResult(queryResult));
        writer.flush();
    }

    private void writeQueryResultAsync(final QueryResult queryResult) {
        ExecutorService executorService = applicationContext.getExecutorService();
        executorService.submit(new Callable() {
            @Override
            public Object call() throws IOException {
                writeQueryResultSync(queryResult);
                return null;
            }
        });
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
