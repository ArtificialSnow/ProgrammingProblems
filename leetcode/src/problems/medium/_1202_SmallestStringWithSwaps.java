package problems.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _1202_SmallestStringWithSwaps {
    static class DisjointSet {

        private final int[] sets;
        private final int[] sizes;

        public DisjointSet(int elements) {
            sets = new int[elements];
            sizes = new int[elements];
            for(int i = 0; i < elements; i++) {
                sets[i] = i;
                sizes[i] = 1;
            }
        }

        public boolean connected(int a, int b) {
            return sets[a] == sets[b];
        }

        public void union(int a, int b) {
            int rootA = getRoot(a);
            int rootB = getRoot(b);
            if (rootA == rootB) {
                return;
            }

            if (sizes[rootA] < sizes[rootB]) {
                sets[rootA] = sets[rootB];
                sizes[rootB] += sizes[rootA];
            } else {
                sets[rootB] = sets[rootA];
                sizes[rootA] += sizes[rootB];
            }
        }

        public int getRoot(int a) {
            while(sets[a] != a) {
                sets[a] = sets[sets[a]] ;
                a = sets[a];
            }
            return a;
        }
    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        DisjointSet ds = new DisjointSet(s.length());

        for (List<Integer> pair : pairs) {
            ds.union(pair.get(0), pair.get(1));
        }

        List<Character>[] charSets = new List[s.length()];
        for (int i = 0; i < s.length(); i++) {
            charSets[i] = new ArrayList<>();
        }

        for (int i = 0; i < s.length(); i++) {
            int root = ds.getRoot(i);
            charSets[root].add(s.charAt(i));
        }

        for (int i = 0; i < s.length(); i++) {
            charSets[i].sort(Collections.reverseOrder());
        }

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int root = ds.getRoot(i);
            output.append(charSets[root].remove(charSets[root].size() - 1));
        }

        return output.toString();
    }
}
