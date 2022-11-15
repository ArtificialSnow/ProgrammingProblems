package problems.hard;

import java.util.ArrayList;
import java.util.List;

public class _1106_ParsingABooleanExpression {
    public boolean parseBoolExpr(String expression) {
        int length = expression.length();
        char operator = expression.charAt(0);

        if (operator == 't') {
            return true;
        } else if (operator == 'f') {
            return false;
        } else if (operator == '!') {
            return !parseBoolExpr(expression.substring(2, length-1));
        } else if (operator == '&') {
            String stripped = expression.substring(2, length-1);
            List<String> expressions = getSubExpressions(stripped);
            for (String s : expressions) {
                if (!parseBoolExpr(s)) {
                    return false;
                }
            }
            //System.out.println(expression);
            return true;
        } else if (operator == '|') {
            String stripped = expression.substring(2, length-1);
            List<String> expressions = getSubExpressions(stripped);
            System.out.println(expression);
            for (String s : expressions) {
                if (parseBoolExpr(s)) {
                    //System.out.println(expressions.get(i));
                    return true;
                }
            }

            return false;
        }

        System.out.println("big fuck");
        return false;
    }

    public List<String> getSubExpressions(String expression) {
        ArrayList<String> expressionList = new ArrayList<>();

        int openBracket = 0;
        int closedBracket = 0;
        int start = 0;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            switch(c) {
                case '(':
                    openBracket++;
                    break;
                case ')':
                    closedBracket++;
                    break;
                case',':
                    if (closedBracket == openBracket) {
                        expressionList.add(expression.substring(start, i));
                        start = i+1;
                        break;
                    }
            }
        }

        expressionList.add(expression.substring(start));
        return expressionList;
    }
}
