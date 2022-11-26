package problems.hard;

public class _164_MaximumGap {
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }

        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }

        int numBuckets = n + 1;
        double bucketSize = (max-min+1)/(double)numBuckets;
        int[] maxOfBucket = new int[numBuckets];
        int[] minOfBucket = new int[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            maxOfBucket[i] = Integer.MIN_VALUE;
            minOfBucket[i] = Integer.MAX_VALUE;
        }

        boolean[] notEmpty = new boolean[numBuckets];
        for (int i = 0; i < n; i++) {
            int index = (int)((nums[i]-min)/bucketSize);
            maxOfBucket[index] = Math.max(maxOfBucket[index], nums[i]);
            minOfBucket[index] = Math.min(minOfBucket[index], nums[i]);
            notEmpty[index] = true;
        }

        int startIndex = -1;
        int previous = -1;
        for (int i = 0; i < n; i++) {
            if (notEmpty[i]) {
                startIndex = i+1;
                previous = maxOfBucket[i];
                break;
            }
        }

        int ans = 0;
        for (int i = startIndex; i < numBuckets; i++) {
            if (notEmpty[i]) {
                ans = Math.max(ans, minOfBucket[i] - previous);
                previous = maxOfBucket[i];
            }
        }

        return ans;
    }
}
