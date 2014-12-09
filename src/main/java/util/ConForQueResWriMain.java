package util;

/**
 * Created by VICTOR on 10.12.2014.
 */
public class ConForQueResWriMain {

//    public static void main(String[] args) throws IOException {
//        ConsoleFormattedQueryResultWriter consoleWriter = new ConsoleFormattedQueryResultWriter();
//        ApplicationContext applicationContext = new DefaultApplicationContext(null);
//        consoleWriter.setApplicationContext(applicationContext);
//
//        FutureTask<Row[]> task = new FutureTask(new Callable() {
//            @Override
//            public Object call() throws Exception {
//                Thread.sleep(2000);
//                return new Row[]{};
//            }
//        });
//        QueryResult queryResult = new QueryResult(task);
//        consoleWriter.writeQueryResult(queryResult);
//        System.out.println("WriteQueryResult");
//        applicationContext.getExecutorService().submit(task);
//
//        while (!task.isDone()) {
//            Row[] rows = new Row[0];
//            try {
//                rows = task.get(100L, TimeUnit.MILLISECONDS);
//            } catch (Exception e) {
//                continue;
//            }
//        }
//        applicationContext.shutdown();
//    }
}
