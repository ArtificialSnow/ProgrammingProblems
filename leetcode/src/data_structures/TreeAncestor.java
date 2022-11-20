package data_structures;

public class TreeAncestor {

    private final int[][] ancestors;
    private final int maxPow;

    public TreeAncestor(int n, int[] parent) {
        maxPow = (int) (Math.log(n) / Math.log(2)) + 1;
        ancestors = new int[maxPow][n];
        ancestors[0] = parent;

        for (int i = 1; i < maxPow; i++) {
            for (int j = 0; j < n; j++) {
                if (ancestors[i-1][j] == -1) {
                    ancestors[i][j] = -1;
                } else {
                    ancestors[i][j] = ancestors[i-1][ancestors[i-1][j]];
                }
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        int bitMask = maxPow;
        while (k > 0 && node != -1) {
            if (k >= 1 << bitMask) {
                node = ancestors[bitMask][node];
                k -= 1 << bitMask;
            } else {
                bitMask -= 1;
            }
        }

        return node;
    }
}
