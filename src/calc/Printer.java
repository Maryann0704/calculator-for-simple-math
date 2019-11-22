package calc;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

class Printer {
    private Logger logger = Logger.getInstance();

    void print(Var result) {
        if (result != null) {
            System.out.println(result);
            logger.fillReport(result.toString());
        }
    }

    void println(Map<String, Var> vars) {
        Set<Map.Entry<String, Var>> entries = vars.entrySet();
        for (Map.Entry<String, Var> entry : entries) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        logger.fillReport(vars.toString());
    }

    void sortPrint(Map<String, Var> vars) {
        Set<Map.Entry<String, Var>> entries = vars.entrySet();
        TreeSet<String> elements = new TreeSet<>();
        for (Map.Entry<String, Var> entry : entries) {
            String element = entry.getKey() + " = " + entry.getValue();
            elements.add(element);
        }
        for (String element : elements) {
            System.out.println(element);
        }
        logger.fillReport(elements.toString());
    }
}
