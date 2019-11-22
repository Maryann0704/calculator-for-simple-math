package calc;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

class ShortReportBuilder extends ReportBuilder {

    private static final AtomicReference<SimpleDateFormat> sdf = new AtomicReference<>(new SimpleDateFormat("dd.MM.yy hh:mm"));

    @Override
    public void buildHeadline() {
        report.setHeadline(ConsoleRunner.manager.getString(Msg.REPORT2));
    }

    @Override
    public void buildStartTime() {
        report.setStartTime(
                sdf.get().format(ConsoleRunner.start)
        );
    }

    private static HashMap<String, String> shortReport = new HashMap<>();

    void fillReport(String task, String result){
        shortReport.put(task, result);
    }

    @Override
    public void buildTaskAndResult() {
        report.setTaskAndResult(shortReport);
    }

    @Override
    public void buildEndTime() {
        report.setEndTime(sdf.get().format(ConsoleRunner.end));
    }
}
