import java.util.*;

public class Parsing {
    static final Map<String, Integer> MAIN_MATH_OPERATORS;

    static {
        MAIN_MATH_OPERATORS = new HashMap<>();
        MAIN_MATH_OPERATORS.put("*", 1);
        MAIN_MATH_OPERATORS.put("/", 1);
        MAIN_MATH_OPERATORS.put("+", 2);
        MAIN_MATH_OPERATORS.put("-", 2);
    }

    static String sortingExpression(String expression) {
        List<String> outputQueue = new ArrayList<>();

        Stack<String> stack = new Stack<>();

        Set<String> operationSymbols = new HashSet<>(Parsing.MAIN_MATH_OPERATORS.keySet());
        operationSymbols.add("(");
        operationSymbols.add(")");

        int lastIterationIndex = 0;

        boolean toFindNext = true;
        while (toFindNext) {
            int nextOperationIndex = expression.length();
            String nextOperation = "";
            for (String operation : operationSymbols) {
                int i = expression.indexOf(operation, lastIterationIndex);
                if (i >= 0 && i < nextOperationIndex) {
                    nextOperation = operation;
                    nextOperationIndex = i;
                }
            }
            if (nextOperationIndex == expression.length()) {
                toFindNext = false;
            } else {
                if (lastIterationIndex != nextOperationIndex) {
                    outputQueue.add(expression.substring(lastIterationIndex, nextOperationIndex));
                }
                if (nextOperation.equals("(")) {
                    stack.push(nextOperation);
                } else if (nextOperation.equals(")")) {
                    while (!stack.peek().equals("(")) {
                        outputQueue.add(stack.pop());
                        if (stack.empty()) {
                            throw new IllegalArgumentException("Missing opening or closing bracket.");
                        }
                    }
                    stack.pop();
                } else {
                    while (!stack.empty() && !stack.peek().equals("(") &&
                            (Parsing.MAIN_MATH_OPERATORS.get(nextOperation) >= Parsing.MAIN_MATH_OPERATORS.get(stack.peek()))) {
                        outputQueue.add(stack.pop());
                    }
                    stack.push(nextOperation);
                }
                lastIterationIndex = nextOperationIndex + nextOperation.length();
            }
        }
        if (lastIterationIndex != expression.length()) {
            outputQueue.add(expression.substring(lastIterationIndex));
        }
        while (!stack.empty()) {
            outputQueue.add(stack.pop());
        }
        StringBuilder reversePolishNotation = new StringBuilder();
        if (!outputQueue.isEmpty()) {
            reversePolishNotation.append(outputQueue.remove(0));
        }
        while (!outputQueue.isEmpty()) {
            reversePolishNotation.append(" ").append(outputQueue.remove(0));
        }
        return reversePolishNotation.toString();
    }
}
