package problems.hard;

public class _1092_ShortestCommonSupersequence {
    public String shortestCommonSupersequence(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        if (n == 0) return str2;
        if (m == 0) return str1;

        int[][] dp = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        boolean[] used1 = new boolean[n];
        boolean[] used2 = new boolean[m];

        int x = n;
        int y = m;
        while (x > 0 && y > 0) {
            if (str1.charAt(x-1) == str2.charAt(y-1)) {
                used1[--x] = true;
                used2[--y] = true;
            } else {
                if (dp[x][y-1] > dp[x-1][y]) {
                    y--;
                } else {
                    x--;
                }
            }
        }

        int a = 0;
        int b = 0;
        StringBuilder output = new StringBuilder();
        while (a < n || b < m) {
            while (a < n) {
                if (used1[a]) break;
                output.append(str1.charAt(a++));
            }

            while (b < m) {
                if (used2[b]) break;
                output.append(str2.charAt(b++));
            }

            if (a < n) output.append(str1.charAt(a));
            a++;
            b++;
        }

        return output.toString();
    }
}
