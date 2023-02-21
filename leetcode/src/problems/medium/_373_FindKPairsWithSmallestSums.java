package problems.medium;

import java.util.*;

public class _373_FindKPairsWithSmallestSums {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> pairs = new ArrayList<>();

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(Pair::sum));
        for (int i = 0; i < nums1.length && i < k; i++)  {
            pq.add(new Pair(nums1[i], nums2[0], 0));
        }

        for (int i = 0; i < k && !pq.isEmpty(); i++) {
            Pair current = pq.poll();
            pairs.add(Arrays.asList(current.num1, current.num2));

            if (current.index + 1 < nums2.length) {
                pq.add(new Pair(current.num1, nums2[current.index + 1], current.index + 1));
            }
        }

        return pairs;
    }

    static class Pair {
        int num1, num2, index;

        public Pair(int num1, int num2, int index) {
            this.num1 = num1;
            this.num2 = num2;
            this.index = index;
        }

        public int sum() {
            return num1 + num2;
        }
    }
}
