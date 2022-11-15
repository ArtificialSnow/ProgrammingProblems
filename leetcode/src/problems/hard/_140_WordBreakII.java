package problems.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class _140_WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        int n = s.length();

        HashSet<String> words = new HashSet<>(wordDict);
        ArrayList<ArrayList<String>> wordLists = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            wordLists.add(new ArrayList<>());
        }

        boolean[] dp = new boolean[n];
        for (int i = 0; i < n; i++) {
            String substr = s.substring(0,i+1);
            if (words.contains(substr)) {
                dp[i] = true;
                wordLists.get(i).add(substr);
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                String substr = s.substring(j+1,i);
                if (dp[j] && words.contains(substr)) {
                    dp[i-1] = true;

                    List<String> wordListFrom = wordLists.get(j);
                    List<String> wordListTo = wordLists.get(i-1);
                    for (String value : wordListFrom) {
                        wordListTo.add(value + " " + substr);
                    }
                }
            }
        }

        return wordLists.get(n-1);
    }
}
