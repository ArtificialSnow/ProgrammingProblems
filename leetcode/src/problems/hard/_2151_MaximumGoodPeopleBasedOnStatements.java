package problems.hard;

import java.util.HashSet;
import java.util.Set;

public class _2151_MaximumGoodPeopleBasedOnStatements {
    public int maximumGood(int[][] statements) {
        int n = statements.length;

        int[] statementsCount = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (statements[i][j] != 2) {
                    statementsCount[i]++;
                    statementsCount[j]++;
                }
            }
        }

        Set<Integer> triviallyGood = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (statementsCount[i] == 0) {
                triviallyGood.add(i);
            }
        }

        int max = 0;
        SubsetBitMask mask = new SubsetBitMask(n - triviallyGood.size());
        while (mask.hasNext()) {
            Subset current = mask.next();
            if (!contradicts(statements, current.set, triviallyGood)) continue;
            max = Math.max(max, current.numElements);
        }

        return max + triviallyGood.size();
    }

    private boolean contradicts(int[][] statements, boolean[] set, Set<Integer> triviallyGood) {
        int current = 0;
        for (int i = 0; i < statements.length; i++) {
            if (triviallyGood.contains(i)) continue;
            if (!set[current]) {
                current++;
                continue;
            }

            int other = 0;
            for (int j = 0; j < statements.length; j++) {
                if (triviallyGood.contains(j)) continue;
                if (statements[current][other] == 0 && set[other] || statements[current][other] == 1 && !set[other]) return false;
                other++;
            }

            current++;
        }

        return true;
    }

    public static class SubsetBitMask {

        private final int setSize;
        private final long max;
        private long current;

        public SubsetBitMask(int setSize) {
            this(setSize, 0);
        }

        public SubsetBitMask(int setSize, long start) {
            this.setSize = setSize;
            this.current = start;
            this.max = setSize > 0 ? (long)Math.pow(2, setSize) : 0;
        }

        public Subset next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException();
            }

            long num = current++;
            boolean[] arr = new boolean[setSize];
            int numElements = 0;
            for (int i = setSize-1; i >= 0 && num > 0; i--) {
                arr[i] = (num & 1) != 0;
                num = num >> 1;

                if (arr[i]) numElements++;
            }

            return new Subset(arr, numElements);
        }

        public boolean hasNext() {
            return current < max;
        }
    }

    static class Subset {
        boolean[] set;
        int numElements;

        public Subset(boolean[] set, int numElements) {
            this.set = set;
            this.numElements = numElements;
        }
    }
}
