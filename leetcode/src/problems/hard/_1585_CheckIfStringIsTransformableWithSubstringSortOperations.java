package problems.hard;

//TODO this is really slow
public class _1585_CheckIfStringIsTransformableWithSubstringSortOperations {
    public boolean isTransformable(String s, String t) {
        int n = s.length();
        if (n != t.length()) return false;

        int[] schars = new int[10];
        for (int i = 0; i < n; i++) {
            schars[s.charAt(i) - '0']++;
        }

        int[] tchars = new int[10];
        for (int i = 0; i < n; i++) {
            tchars[t.charAt(i) - '0']++;
        }

        for (int i = 0; i < 10; i++) {
            if (schars[i] != tchars[i]) {
                return false;
            }
        }

        char[] sa = s.toCharArray();
        char[] ta = t.toCharArray();
        for (int i = 0; i < n; i++) {
            if (sa[i] == ta[i]) {
                continue;
            }

            int index = -1;
            for (int j = i+1; j < n; j++) {
                if (ta[i] == sa[j]) {
                    index = j;
                    break;
                }
            }

            for (int j = index; j > i; j--) {
                if (sa[j] > sa[j-1]) {
                    return false;
                }

                char temp = sa[j];
                sa[j] = sa[j-1];
                sa[j-1] = temp;
            }
        }

        return true;
    }
}
