package problems.hard;

public class _1771_MaximizePalidromeLengthFromSubsequence {
    public int longestPalindrome(String word1, String word2) {
        String s = word1 + word2;
        int n = s.length();
        if (n <= 1) { return n; }

        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int i = 0; i < n-1; i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
                dp[i][i+1] = 2;
            } else {
                dp[i][i+1] = 1;
            }
        }

        for (int k = 3; k <= n; k++) {
            for (int i = 0; i + k - 1 < n; i++) {
                if (s.charAt(i) == s.charAt(i + k - 1)) {
                    dp[i][i+k-1] = dp[i+1][i + k - 2] + 2;
                } else {
                    dp[i][i+k-1] = Math.max(dp[i][i+k-2], dp[i+1][i+k-1]);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < word1.length(); i++) {
            for (int j = word1.length(); j < n; j++) {
                if (s.charAt(i) != s.charAt(j)) continue;
                max = Math.max(max, dp[i][j]);
            }
        }

        return max;
    }
}
