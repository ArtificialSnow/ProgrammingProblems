package problems.hard;

public class _741_CherryPickup {
    public int cherryPickup(int[][] grid) {
        // Oops, solved it for any rectangular grid instead of just squares
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] prevSols = new int[1][1];
        prevSols[0][0] = grid[0][0];
        for (int i = 1; i < cols; i++) {
            int diagLength = Math.min(i+1, rows);
            int[][] currentSols = new int[diagLength][diagLength];
            for (int j = 0; j < diagLength; j++) {
                // Current coord is (j, i-j)
                for (int k = 0; k <= j; k++) {
                    // Current coord is (k, i-k)
                    if (grid[j][i-j] == -1 || grid[k][i-k] == -1) {
                        currentSols[j][k] = -1;
                        continue;
                    }

                    int numPicked = (j == k) ? grid[j][i-j] : grid[j][i-j] + grid[k][i-k];
                    int max = -1;
                    if (j < prevSols.length && k < prevSols.length) {
                        max = Math.max(max, prevSols[j][k]);
                    }
                    if (j-1 >= 0 && k < prevSols.length) {
                        max = Math.max(max, (j-1 > k) ? prevSols[j-1][k] : prevSols[k][j-1]);
                    }
                    if (j < prevSols.length && k-1 >= 0) {
                        max = Math.max(max, prevSols[j][k-1]);
                    }
                    if (j-1 >= 0 && k-1 >= 0) {
                        max = Math.max(max, prevSols[j-1][k-1]);
                    }

                    currentSols[j][k] = (max == -1) ? -1 : max + numPicked;
                }
            }
            prevSols = currentSols;
        }

        for (int i = 1; i < rows; i++) {
            int diagLength = rows-i;
            int[][] currentSols = new int[diagLength][diagLength];
            for (int j = 0; j < diagLength; j++) {
                // Current coord is (i-j,cols-1-j)
                for (int k = 0; k <= j; k++) {
                    // Current coord is (i-k,cols-1-k)
                    if (grid[i+j][cols-1-j] == -1 || grid[i+k][cols-1-k] == -1) {
                        currentSols[j][k] = -1;
                        continue;
                    }

                    int numPicked = (j == k) ? grid[i+j][cols-1-j] : grid[i+j][cols-1-j] + grid[i+k][cols-1-k];
                    int max = -1;
                    max = Math.max(max, prevSols[j][k]);
                    max = Math.max(max, prevSols[j+1][k]);
                    max = Math.max(max, (j > k+1) ? prevSols[j][k+1] : prevSols[k+1][j]);
                    max = Math.max(max, prevSols[j+1][k+1]);

                    currentSols[j][k] = (max == -1) ? -1 : max + numPicked;
                }
            }
            prevSols = currentSols;
        }

        return Math.max(0, prevSols[0][0]);
    }
}
