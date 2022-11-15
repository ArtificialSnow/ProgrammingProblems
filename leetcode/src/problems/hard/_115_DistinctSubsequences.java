package problems.hard;

//TODO Make this an iterative approach, recursive too slow
public class _115_DistinctSubsequences {
    public int numDistinct(String s, String t) {
        return solve(new Integer[s.length() + 1][t.length() + 1], s, t, 0, 0);
    }

    private int solve(Integer[][] map, String s, String t, int m, int n) {
        if (n >= t.length()) {
            return 1;
        }

        if (m >= s.length()) {
            return 0;
        }

        if (map[m][n] != null) {
            return map[m][n];
        }

        int count = 0;
        char c = t.charAt(n);
        for (int i = m; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                count += solve(map, s, t, i + 1, n + 1);
            }
        }

        map[m][n] = count;
        return count;
    }
}
