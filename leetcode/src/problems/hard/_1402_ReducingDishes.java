package problems.hard;

import java.util.Arrays;

public class _1402_ReducingDishes {
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);

        int firstNonNegative = -1;
        for (int i = 0; i < satisfaction.length; i++) {
            if (satisfaction[i] >= 0) {
                firstNonNegative = i;
                break;
            }
        }

        if (firstNonNegative == -1) {
            return 0;
        }

        int ans = 0;
        int sumWithoutWeights = 0;
        for (int i = firstNonNegative; i < satisfaction.length; i++) {
            ans += (i - firstNonNegative + 1) * satisfaction[i];
            sumWithoutWeights += satisfaction[i];
        }

        for (int i = firstNonNegative-1; i >= 0; i--) {
            int poss = sumWithoutWeights + satisfaction[i];
            if (poss <= 0) break;

            sumWithoutWeights += satisfaction[i];
            ans += poss;
        }

        return ans;
    }
}
