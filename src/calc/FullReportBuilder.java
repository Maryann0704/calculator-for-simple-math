package calc;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

class FullReportBuilder extends ReportBuilder {

    private static final AtomicReference<SimpleDateFormat> sdf = new AtomicReference<>(new SimpleDateFormat("EEE dd MMM yyyy HH:mm:ss"));

    private static HashMap<String, String> fullReport = new HashMap<>();

    void fillReport(String task, String result){
        fullReport.put(task, result);
    }

    @Override
    public void buildHeadline() {
        report.setHeadline(ConsoleRunner.manager.getString(Msg.REPORT3));
    }

    @Override
    public void buildStartTime() {
        report.setStartTime(
                sdf.get().format(ConsoleRunner.start)
        );
    }

    @Override
    public void buildTaskAndResult() {
        report.setTaskAndResult(fullReport);
    }

    @Override
    public void buildEndTime() {
        report.setEndTime(sdf.get().format(ConsoleRunner.end));
    }
}
