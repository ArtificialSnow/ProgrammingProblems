package problems.hard;

import java.util.ArrayList;
import java.util.List;

public class _60_PermutationSequence {
    public String getPermutation(int n, int k) {

        int[] factorials = new int[n];
        factorials[0] = 1;
        for (int i = 1; i < n; i++) {
            factorials[i] = factorials[i-1] * i;
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        int current = k;
        StringBuilder sb = new StringBuilder();
        for (int i = n-1; i >= 0; i--) {
            int kthSmallestIndex = (current-1)/factorials[i];
            sb.append(list.get(kthSmallestIndex));
            list.remove(kthSmallestIndex);

            current -= (kthSmallestIndex * factorials[i]);
        }

        return sb.toString();
    }
}
