package problems.medium;

public class _1395_CountNumberOfTeams {
    public int numTeams(int[] rating) {
        int n = rating.length;
        int[] lessThan = new int[n];
        int[] greaterThan = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (rating[i] < rating[j]) {
                    greaterThan[i]++;
                } else if (rating[i] > rating[j]) {
                    lessThan[i]++;
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (rating[i] < rating[j]) {
                    sum += greaterThan[j];
                } else if (rating[i] > rating[j]) {
                    sum += lessThan[j];
                }
            }
        }

        return sum;
    }
}
