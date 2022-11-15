package problems.hard;

import java.util.*;

//TODO this is slow
public class _472_ConcatenatedWords {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        ArrayList<String> ans = new ArrayList<>();
        int n = words.length;
        if (n < 3) { return ans; }

        HashMap<Character, ArrayList<String>> charMap = new HashMap<>();
        for (String word : words) {
            if (word.equals("")) {
                continue;
            }

            char firstCharacter = word.charAt(0);
            if (charMap.containsKey(firstCharacter)) {
                charMap.get(firstCharacter).add(word);
            } else {
                ArrayList<String> newList = new ArrayList<>();
                newList.add(word);
                charMap.put(word.charAt(0), newList);
            }
        }

        Arrays.sort(words, Comparator.comparingInt(String::length));

        String shortest = words[0];
        String nextShortest = words[1];
        int combinedLength = shortest.length() + nextShortest.length();
        HashSet<String> memo = new HashSet<>();
        for (int i = 2; i < words.length; i++) {
            if (words[i].length() < combinedLength) {
                continue;
            }

            String currentWord = words[i];
            char firstChar = currentWord.charAt(0);

            ArrayList<String> startSameChar = charMap.get(firstChar);
            for (String sameCharWord : startSameChar) {
                if (sameCharWord.length() < currentWord.length() && currentWord.startsWith(sameCharWord)) {
                    if (canConcat(charMap, memo, currentWord.substring(sameCharWord.length()))) {
                        ans.add(currentWord);
                        memo.add(currentWord);
                        break;
                    }
                }
            }
        }

        return ans;
    }

    public boolean canConcat(HashMap<Character, ArrayList<String>> map, HashSet<String> memo, String currentWord) {
        if (currentWord.length() == 0) {
            return true;
        }

        if(memo.contains(currentWord)) {
            return true;
        }

        char firstChar = currentWord.charAt(0);
        ArrayList<String> startSameChar = map.get(firstChar);
        if (startSameChar == null) {
            return false;
        }

        for (String sameCharWord : startSameChar) {
            if (sameCharWord.length() <= currentWord.length() && currentWord.startsWith(sameCharWord)) {
                if (canConcat(map, memo, currentWord.substring(sameCharWord.length()))) {
                    memo.add(currentWord);
                    return true;
                }
            }
        }
        return false;
    }
}
