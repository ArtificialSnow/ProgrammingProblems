package problems.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class _336_PalindromePairs {

    private static final int mod = 1000000007;

    private static class TrieNode {
        TrieNode[] next;
        int index;
        List<Integer> list;

        TrieNode() {
            next = new TrieNode[26];
            index = -1;
            list = new ArrayList<>();
        }
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();

        TrieNode root = new TrieNode();

        for (int i = 0; i < words.length; i++) {
            addWord(root, words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            search(words, i, root, res);
        }

        return res;
    }

    private void addWord(TrieNode root, String word, int index) {
        for (int i = word.length() - 1; i >= 0; i--) {
            int j = word.charAt(i) - 'a';

            if (root.next[j] == null) {
                root.next[j] = new TrieNode();
            }

            if (isPalindrome(word, 0, i)) {
                root.list.add(index);
            }

            root = root.next[j];
        }

        root.list.add(index);
        root.index = index;
    }

    private void search(String[] words, int i, TrieNode root, List<List<Integer>> res) {
        for (int j = 0; j < words[i].length(); j++) {
            if (root.index >= 0 && root.index != i && isPalindrome(words[i], j, words[i].length() - 1)) {
                res.add(Arrays.asList(i, root.index));
            }

            root = root.next[words[i].charAt(j) - 'a'];
            if (root == null) return;
        }

        for (int j : root.list) {
            if (i == j) continue;
            res.add(Arrays.asList(i, j));
        }
    }

    private boolean isPalindrome(String word, int i, int j) {
        while (i < j) {
            if (word.charAt(i++) != word.charAt(j--)) return false;
        }

        return true;
    }

    //TLE
    public List<List<Integer>> palindromePairs2(String[] words) {
        List<List<Integer>> ans = new ArrayList<>();
        if (words.length == 0) {
            return ans;
        }

        int[] powers = preComputeBasePowers(27, 300, mod);

        HashMap<String, Integer> forwards = new HashMap<>();
        for (String word : words) {
            forwards.put(word, hash(word));
        }

        HashMap<String, Integer> backwards = new HashMap<>();
        for (String word : words) {
            StringBuilder sb = new StringBuilder(word);
            sb.reverse();
            backwards.put(word, hash(sb.toString()));
        }

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i == j) {
                    continue;
                }

                int forwardsHash1 = forwards.get(words[i]);
                int forwardsHash2 = forwards.get(words[j]);
                long forwardsCombinedHash = (((long)forwardsHash1) * powers[words[j].length()] + forwardsHash2) % mod;

                int reverseHash1 = backwards.get(words[i]);
                int reverseHash2 = backwards.get(words[j]);
                long reverseCombinedHash = (((long)reverseHash2) * powers[words[i].length()] + reverseHash1) % mod;
                if (forwardsCombinedHash != reverseCombinedHash) {
                    continue;
                }

                List<Integer> newList = new ArrayList<>();
                newList.add(i);
                newList.add(j);
                ans.add(newList);
            }
        }

        return ans;
    }

    private int[] preComputeBasePowers(int base, int maxLength, int MOD){
        int[] basePowers = new int[maxLength+1];
        basePowers[0] = 1;
        for(int i = 1; i < maxLength+1; i++){
            long next = ((long)basePowers[i-1]*(long)base);
            while(next > MOD){
                next -= MOD;
            }
            basePowers[i] = (int)next;
        }
        return basePowers;
    }

    private int hash(String s) {
        long hash = 0;
        for (int i = 0; i < s.length(); i++) {
            hash = (((hash * 27) % mod) + s.charAt(i) - 'a' + 1) % mod;
        }

        return (int)hash;
    }
}
