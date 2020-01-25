import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Validator {
    boolean isValid (String expression) {

        String toValidate = normalizeString(expression);
        if (toValidate.endsWith("+")||toValidate.endsWith("-")||
                toValidate.endsWith("/") || toValidate.endsWith("*")) {
            throw new IllegalArgumentException("Incorrect expression.");
        }
        if (bracketsAreEmpty(expression)) {
            throw new IllegalArgumentException("Empty brackets.");
        }
        if (!bracketsIsAllRight(expression)) {
            throw new IllegalArgumentException("Missing opening or closing bracket.");
        }
        String[] expressionToValidate = splitString(expression);
        for (String operand : expressionToValidate) {
            try {
                Double.parseDouble(operand);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Incorrect expression.");
            }
        }

        return true;
    }

    private String[] splitString(String expression) {
        return normalizeString(expression).split("[+\\-*/]");
    }

    private String normalizeString(String stringToNormalize) {
        stringToNormalize = stringToNormalize.replaceAll("\\(-|[()]", "");
        return stringToNormalize;
    }

    private boolean bracketsAreEmpty(String expressionToValidate) {
        return expressionToValidate.contains("()");
    }

    private boolean bracketsIsAllRight(String expressionToValidate) {
        Deque<String> stack = new ArrayDeque<>();
        String delimiters = "()";
        StringTokenizer tokenizer = new StringTokenizer(expressionToValidate, delimiters, true);
        String currentToken;
        while (tokenizer.hasMoreTokens()) {
            currentToken = tokenizer.nextToken();
            if (currentToken.equals("(")) stack.push(currentToken);
            else if (currentToken.equals(")")) {
                try {
                    stack.pop();
                } catch (NoSuchElementException e) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
