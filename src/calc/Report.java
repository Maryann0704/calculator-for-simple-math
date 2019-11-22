package calc;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Report {
    private String headline = "";
    private String startTime = "";
    private String endTime = "";
    private Map<String, String> taskAndResult = new HashMap<>();

    void setHeadline(String headline) {
        this.headline = headline;
    }
    void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    void setEndTime(String startTime) {
        this.endTime = startTime;
    }
    void setTaskAndResult(Map<String, String> taskAndResult) {
        this.taskAndResult = taskAndResult;
    }


    @Override
    public String toString() {
        StringBuilder elements = new StringBuilder("----------" + headline + "----------").append('\n');
        elements.append('\n').append(ConsoleRunner.manager.getString(Msg.START_TIME)).append(startTime).append("\n\n");
        Set<Map.Entry<String, String>> entries = taskAndResult.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String element = entry.getKey()+ " : "+ entry.getValue();
            elements.append(element).append("\n");
        }
        elements.append('\n').append(ConsoleRunner.manager.getString(Msg.END_TIME)).append(endTime).append('\n');
        return elements.toString();
    }
}


