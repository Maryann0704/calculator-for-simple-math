package calc;

abstract class ReportBuilder {
    Report report;

    Report getReport() { return report; }

    void createNewReport() { report = new Report(); }

    public abstract void buildHeadline();
    public abstract void buildStartTime();
    public abstract void buildTaskAndResult();
    public abstract void buildEndTime();
}

