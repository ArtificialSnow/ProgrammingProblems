package problems.hard;

public class _10_RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        return solve(0, s.toCharArray(), 0, p.toCharArray());
    }

    public boolean solve(int sIndex, char[] s, int pIndex, char[] p) {
        if (sIndex >= s.length && pIndex >= p.length) return true;
        if (pIndex >= p.length) return false;
        if (sIndex >= s.length) {
            if ((p.length - pIndex) % 2 == 1) return false;
            for (int i = pIndex+1; i < p.length; i = i+2) {
                if (p[i] != '*') return false;
            }

            return true;
        }

        if (pIndex + 1 < p.length) {
            if (p[pIndex+1] == '*') {
                int offset = 0;

                boolean anyMatch = solve(sIndex, s, pIndex+2, p);
                while (sIndex + offset < s.length && (p[pIndex] == '.' || s[sIndex + offset] == p[pIndex])) {
                    anyMatch = anyMatch || solve(sIndex+offset+1, s, pIndex+2, p);
                    offset++;
                }

                return anyMatch;
            }
        }

        if (p[pIndex] == '.' || s[sIndex] == p[pIndex]) {
            return solve(sIndex+1, s, pIndex+1, p);
        }

        return false;
    }
}
