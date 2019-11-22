package calc;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

class FactoryVar {

    private static Map<String, Var> vars = new HashMap<>();

    private static final String VARIABLES = createFile();

    static Map<String, Var> getVars() {
        return vars;
    }

    static void saveVar(String name, Var var){
        vars.put(name, var);
        save();
    }

    static Var createVar(String operand) throws CalcException {
        operand = operand.trim();
        if (operand.matches(Patterns.SCALAR))
            return new Scalar(operand);
        if (operand.matches(Patterns.VECTOR))
            return new Vector(operand);
        if (operand.matches(Patterns.MATRIX))
            return new Matrix(operand);
        if (vars.containsKey(operand))
            return vars.get(operand);
        else throw new CalcException(ConsoleRunner.manager.getString(Msg.ABSENT2));
    }

    private static void save() {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(VARIABLES))){
            for (Map.Entry<String, Var> pair : vars.entrySet()) {
                printWriter.println(pair.getKey() +" = "+ pair.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void load() throws CalcException {
        Parser p = new Parser();
        File file = new File(VARIABLES);
        if (file.exists()) {
            try (BufferedReader in = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = in.readLine()) != null) {
                    p.calc(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String createFile() {
        String src = System.getProperty("user.dir") + File.separator + "src" + File.separator;
        String replacePath = Var.class.getPackage().getName().replace(".", File.separator);
        return src + replacePath + File.separator + "vars.txt";
    }
}
