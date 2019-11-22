package calc;

class Reporter {
    private ReportBuilder reportBuilder;

    void setReportBuilder(ReportBuilder rb) { reportBuilder = rb; }
    Report getReport() { return reportBuilder.getReport(); }

    void constructReport() {
        reportBuilder.createNewReport();
        reportBuilder.buildHeadline();
        reportBuilder.buildStartTime();
        reportBuilder.buildTaskAndResult();
        reportBuilder.buildEndTime();
    }
}
