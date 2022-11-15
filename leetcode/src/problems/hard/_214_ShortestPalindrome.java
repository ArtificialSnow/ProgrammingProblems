package problems.hard;

public class _214_ShortestPalindrome {

    private static final int mod = 1000000007;

    public String shortestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }

        long base = 27;
        long[] powers = new long[s.length()+1];
        powers[0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            powers[i] = (powers[i-1] * base) % mod;
        }

        int[] forwards = new int[s.length()+1];
        int[] backwards = new int[s.length()+1];
        for (int i = 1; i <= s.length(); i++) {
            forwards[i] = (int)((((forwards[i-1] * base) % mod) + s.charAt(i-1) - 'a' + 1) % mod);
            backwards[i] = (int)((backwards[i-1] + ((powers[i-1] * (s.charAt(i-1) - 'a' + 1)) % mod)) % mod);
        }

        for (int i = s.length(); i > 0; i--) {
            if (forwards[i] == backwards[i]) {
                return new StringBuilder(s.substring(i)).reverse().append(s).toString();
            }
        }

        return "";
    }
}
