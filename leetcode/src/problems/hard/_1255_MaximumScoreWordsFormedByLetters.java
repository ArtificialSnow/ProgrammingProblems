package problems.hard;

public class _1255_MaximumScoreWordsFormedByLetters {
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[] givenLetters = new int[26];
        for (int i = 0; i < letters.length; i++) {
            givenLetters[letters[i] - 'a']++;
        }

        int[][] wordCharFreq = new int[words.length][26];
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                wordCharFreq[i][words[i].charAt(j) - 'a']++;
            }
        }

        int[] wordScore = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < 26; j++) {
                wordScore[i] += wordCharFreq[i][j] * score[j];
            }
        }

        return solve(0, givenLetters, wordCharFreq, wordScore, 0);
    }

    public int solve(int i, int[] givenLetters, int[][] wordCharFreq, int[] wordScore, int currentScore) {
        if (i >= wordCharFreq.length) return currentScore;

        for (int j = 0; j < 26; j++) {
            if (wordCharFreq[i][j] > givenLetters[j]) return solve(i+1, givenLetters, wordCharFreq, wordScore, currentScore);
        }

        for (int j = 0; j < 26; j++) {
            givenLetters[j] = givenLetters[j] - wordCharFreq[i][j];
        }

        int maxScore = currentScore;
        maxScore = Math.max(maxScore, solve(i+1, givenLetters, wordCharFreq, wordScore, currentScore + wordScore[i]));

        for (int j = 0; j < 26; j++) {
            givenLetters[j] = givenLetters[j] + wordCharFreq[i][j];
        }

        maxScore = Math.max(maxScore, solve(i+1, givenLetters, wordCharFreq, wordScore, currentScore));
        return maxScore;
    }
}
