package problems.hard;

public class _87_ScrambleString {
    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        if (n == 0) return true;

        boolean[][][] dp = new boolean[n][n][n+1];
        for (int s1Start = 0; s1Start < n; s1Start++) {
            for (int s2Start = 0; s2Start < n; s2Start++) {
                dp[s1Start][s2Start][1] = s1.charAt(s1Start) == s2.charAt(s2Start);
            }
        }


        for (int size = 2; size <= n; size++) {
            for (int s1Start = 0; s1Start + size <= n; s1Start++) {
                s2Start:
                for (int s2Start = 0; s2Start + size <= n; s2Start++) {
                    for (int split = 0; split < size-1; split++) {
                        if ((dp[s1Start][s2Start][split+1] && dp[s1Start+split+1][s2Start+split+1][size-split-1]) ||
                                (dp[s1Start][s2Start+size-split-1][split+1] && dp[s1Start+split+1][s2Start][size-split-1])) {
                            dp[s1Start][s2Start][size] = true;
                            continue s2Start;
                        }
                    }
                }
            }
        }

        return dp[0][0][n];
    }
}
