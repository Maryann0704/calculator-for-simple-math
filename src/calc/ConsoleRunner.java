package calc;

import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class ConsoleRunner {

    static final Scanner scanner = new Scanner(System.in);
    public static final ResourceManager manager = ResourceManager.INSTANCE;
    static Date start = new Date();
    static Date end;

    public static void main(String[] args) {

        System.out.println(manager.getString(Msg.WELCOME));
        Logger logger = Logger.getInstance();

        try {
            FactoryVar.load();
            logger.loadLog();
        } catch (CalcException e) {
            e.printStackTrace();
        }

        Parser parser = new Parser();
        Printer printer = new Printer();
        ShortReportBuilder shortRB = new ShortReportBuilder();
        FullReportBuilder fullRB = new FullReportBuilder();
        String expr;
        Locale locale;
        for (; ; ) {
            expr = scanner.nextLine();
            if (expr.equals("ru")) {
                locale = new Locale("ru", "RU");
                manager.changeLocale(locale);
                System.out.println(manager.getString(Msg.WELCOME2));
                continue;
            }
            if (expr.equals("be")) {
                locale = new Locale("be", "BY");
                manager.changeLocale(locale);
                System.out.println(manager.getString(Msg.WELCOME2));
                continue;
            }
            if (expr.equals("en")) {
                locale = new Locale("en", "US");
                manager.changeLocale(locale);
                System.out.println(manager.getString(Msg.WELCOME2));
                continue;
            }
            if (expr.equals("printvar")) {
                printer.println(FactoryVar.getVars());
                continue;
            }
            if (expr.equals("sortvar")) {
                printer.sortPrint(FactoryVar.getVars());
                continue;
            }
            if (expr.equals("end")) {
                end = new Date();
                System.out.println(manager.getString(Msg.REPORT));
                RunnerBuilder.createReport();
                break;
            }
            Var result = null;
            try {
                result = parser.calc(expr);
            } catch (CalcException e) {
                System.out.println(e.getMessage());
                logger.fillReport(e.getMessage());
                shortRB.fillReport(expr, manager.getString(Msg.ERROR1));
                fullRB.fillReport(expr, e.getMessage());
            }
            printer.print(result);
            if (result != null) {
                shortRB.fillReport(expr, result.toString());
                fullRB.fillReport(expr, result.toString());
            }
        }
    }
}
