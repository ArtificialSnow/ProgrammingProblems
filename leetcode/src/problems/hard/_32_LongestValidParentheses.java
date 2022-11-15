package problems.hard;

public class _32_LongestValidParentheses {

    //TODO this is slow
    public int longestValidParentheses(String s) {
        int n = s.length();

        int count = 0;
        for (int i = 0; i < n; i++) {
            int openBrackets = 0;
            int closedBrackets = 0;
            int start = i;
            for (int j = i; j < n; j++) {
                if (s.charAt(j) == '(') {
                    openBrackets++;
                } else if (s.charAt(j) == ')') {
                    closedBrackets++;
                }

                if (closedBrackets > openBrackets) {
                    start = j + 1;
                    closedBrackets = 0;
                    openBrackets = 0;
                } else if (closedBrackets == openBrackets && closedBrackets > 0) {
                    count = Math.max(count, j - start + 1);
                }
            }
        }

        return count;
    }
}
