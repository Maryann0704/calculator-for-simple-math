package calc;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Parser {

    private static Map<String, Integer> mapPriority = new HashMap<>();
    static {
        mapPriority.put("=", 0);
        mapPriority.put("+", 1);
        mapPriority.put("-", 1);
        mapPriority.put("*", 2);
        mapPriority.put("/", 2);
    }

    private int getIndexOperation(List<String> operations) {
        int priorityValue=-1;
        int index=-1;
        for (int i = 0; i < operations.size(); i++) {
            String op = operations.get(i);
            int current=mapPriority.get(op);
            if (current>priorityValue){
                priorityValue=current;
                index=i;
            }
        }
        return index;
    }

    private static Var oneOperation(String leftOperand, String operation, String rightOperand) throws CalcException {
        Var second = FactoryVar.createVar(rightOperand);
        if (operation.equals("=")) {
            FactoryVar.saveVar(leftOperand, second);
            return second;
        }
        Var first = FactoryVar.createVar(leftOperand);
        if (first == null || second == null)
            throw new CalcException(ConsoleRunner.manager.getString(Msg.OPERAND));
        switch (operation) {
            case "+":
                return first.add(second);
            case "-":
                return first.sub(second);
            case "*":
                return first.mul(second);
            case "/":
                return first.div(second);
            default:
                throw new CalcException(ConsoleRunner.manager.getString(Msg.ABSENT));
        }
    }

    private static boolean checkParentheses(String expr) {
        int opened = 0;
        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == '(')
                opened++;
            else if (expr.charAt(i) == ')') {
                if (opened == 0)
                    return false;
                opened--;
            }
        }
        return opened == 0;
    }


    Var calc(String expr) throws CalcException {
        expr = expr.replaceAll("\\s+", "");
        Var result;
        if (expr.contains("(")){
            if (!checkParentheses(expr))
                throw new CalcException(ConsoleRunner.manager.getString(Msg.PARENTHESIS));
            else {
                Pattern pattern = Pattern.compile(Patterns.PARENTHESIS);
                Matcher matcher = pattern.matcher(expr);
                while (matcher.find()){
                    StringBuilder sb = new StringBuilder();
                    String string = matcher.group();
                    String string1 = sb.append(string).deleteCharAt(0).deleteCharAt(sb.length() - 1).toString();
                    Var result0 = whenExpressionWithoutParenthesis(string1);
                    expr = expr.replace(string, result0.toString());
                    if (expr.contains("(")){
                        matcher = pattern.matcher(expr);
                    }
                }
                result = whenExpressionWithoutParenthesis(expr);
            }
        }
        else {
            result = whenExpressionWithoutParenthesis(expr);
        }
        return result;
    }

    private Var whenExpressionWithoutParenthesis(String expr) throws CalcException {
        List<String> operations=new ArrayList<>();
        List<String> operands= new ArrayList<>(
                Arrays.asList(expr.split(Patterns.OPERATION))
        );

        Pattern pattern = Pattern.compile(Patterns.OPERATION);
        Matcher matcher = pattern.matcher(expr);
        while (matcher.find())
            operations.add(matcher.group());
        while (!operations.isEmpty()) {
            int index = getIndexOperation(operations);
            String operation = operations.remove(index);
            String leftOperand = operands.remove(index);
            String rightOperand = operands.remove(index);
            Var oneOperationResult = oneOperation(leftOperand, operation, rightOperand);
            operands.add(index,oneOperationResult.toString());
        }
        return FactoryVar.createVar(operands.get(0));
    }
}
