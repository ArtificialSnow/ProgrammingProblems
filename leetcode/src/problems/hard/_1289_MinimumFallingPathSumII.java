package problems.hard;

public class _1289_MinimumFallingPathSumII {

    // Optimized version
    public int minFallingPathSum(int[][] arr) {
        int smallestVal = Integer.MAX_VALUE;
        int smallestIndex = 0;
        int secondSmallestVal = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[0][i] < smallestVal) {
                secondSmallestVal = smallestVal;

                smallestVal = arr[0][i];
                smallestIndex = i;
                continue;
            }

            if (arr[0][i] < secondSmallestVal) {
                secondSmallestVal = arr[0][i];
            }
        }

        for(int i = 1; i < arr.length; i++){
            int newSmallestVal = Integer.MAX_VALUE;
            int newSmallestIndex = 0;
            int newSecondSmallestVal = Integer.MAX_VALUE;

            for(int j = 0; j < smallestIndex; j++){
                if (smallestVal + arr[i][j] < newSmallestVal) {
                    newSecondSmallestVal = newSmallestVal;

                    newSmallestVal = smallestVal + arr[i][j];
                    newSmallestIndex = j;
                    continue;
                }

                if (smallestVal + arr[i][j] < newSecondSmallestVal) {
                    newSecondSmallestVal = smallestVal + arr[i][j];
                }
            }

            if (secondSmallestVal + arr[i][smallestIndex] < newSmallestVal) {
                newSecondSmallestVal = newSmallestVal;

                newSmallestVal = secondSmallestVal + arr[i][smallestIndex];
                newSmallestIndex = smallestIndex;
            } else if (secondSmallestVal + arr[i][smallestIndex] < newSecondSmallestVal) {
                newSecondSmallestVal = secondSmallestVal + arr[i][smallestIndex];
            }

            for(int j = smallestIndex+1; j < arr.length; j++){
                if (smallestVal + arr[i][j] < newSmallestVal) {
                    newSecondSmallestVal = newSmallestVal;

                    newSmallestVal = smallestVal + arr[i][j];
                    newSmallestIndex = j;
                    continue;
                }

                if (smallestVal + arr[i][j] < newSecondSmallestVal) {
                    newSecondSmallestVal = smallestVal + arr[i][j];
                }
            }

            smallestVal = newSmallestVal;
            smallestIndex = newSmallestIndex;
            secondSmallestVal = newSecondSmallestVal;
        }

        return smallestVal;
    }

    public int minFallingPathSum2(int[][] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++){
            dp[0][i] = arr[0][i];
        }

        for(int i = 1; i < n; i++){
            for(int j = 0; j < n; j++){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i = 1; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < n; k++){
                    if(k == j) continue;
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][k] + arr[i][j]);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            min = Math.min(min, dp[n-1][i]);
        }

        return min;
    }
}
