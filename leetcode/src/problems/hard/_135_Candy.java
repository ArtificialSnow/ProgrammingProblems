package problems.hard;

public class _135_Candy {
    public int candy(int[] ratings) {
        int n = ratings.length;

        boolean[] biggerThanL = new boolean[n];
        for (int i = 1; i < n; i++) {
            biggerThanL[i] = (ratings[i] > ratings[i-1]);
        }

        int[] leftConstraint = new int[n];
        leftConstraint[0] = 1;
        for (int i = 1; i < n; i++) {
            if (biggerThanL[i]) leftConstraint[i] = leftConstraint[i-1]+1;
            else leftConstraint[i] = 1;
        }

        boolean[] biggerThanR = new boolean[n];
        for (int i = 0; i < n-1; i++) {
            biggerThanR[i] = (ratings[i] > ratings[i+1]);
        }

        int[] rightConstraint = new int[n];
        rightConstraint[n-1] = 1;
        for (int i = n-2; i >= 0; i--) {
            if (biggerThanR[i]) rightConstraint[i] = rightConstraint[i+1]+1;
            else rightConstraint[i] = 1;
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.max(leftConstraint[i], rightConstraint[i]);
        }

        return sum;
    }
}
