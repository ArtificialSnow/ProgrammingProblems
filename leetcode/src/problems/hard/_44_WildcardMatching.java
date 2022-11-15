package problems.hard;

public class _44_WildcardMatching {
    private static Boolean[][] map;

    public boolean isMatch(String s, String p) {
        map = new Boolean[s.length()+1][p.length()+1];
        return calcMatch(s,p,0,0);
    }

    private boolean calcMatch(String s, String p, int n, int m) {
        if (n >= s.length() && (m >= p.length() || containsOnlyStars(p,m))) { return true; }
        if (n >= s.length() || m >= p.length()) { return false; }
        if (map[n][m] != null) { return map[n][m]; }

        // If Strings are equal, the pattern is only '*', or the pattern is the same length and only '?'
        if (containsOnlyStars(p,m) || (((s.length() - n) == (p.length() - m)) && containsOnlyQuestionMarks(p,m))) {
            map[n][m] = true;
            return true;
        }

        if (p.charAt(m) == '?' || s.charAt(n) == p.charAt(m)) {
            boolean isMatch = calcMatch(s,p,n+1,m+1);
            map[n][m] = isMatch;
            return isMatch;
        }

        if (p.charAt(m) == '*') {
            for (int i = n; i < s.length(); i++) {
                if (calcMatch(s,p,i,m+1)) {
                    map[n][m] = true;
                    return true;
                }
            }
        }

        map[n][m] = false;
        return false;
    }

    private boolean equals(String s, String p, int n, int m) {
        if (s.length() - n != p.length() - m) {
            return false;
        }

        for (int i = 0; i < s.length() - n; i++) {
            if (s.charAt(n + i) != p.charAt(m + i)) {
                return false;
            }
        }

        return true;
    }

    private boolean containsOnlyStars(String p, int m) {
        int n = p.length();
        if (m >= n) {
            return false;
        }

        char c = '*';
        for (int i = m; i < n; i++) {
            if (c != p.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    private boolean containsOnlyQuestionMarks(String p, int m) {
        int n = p.length();
        if (m >= n) {
            return false;
        }

        char c = '?';
        for (int i = m; i < n; i++) {
            if (c != p.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}
