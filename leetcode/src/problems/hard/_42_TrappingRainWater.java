package problems.hard;

public class _42_TrappingRainWater {
    public int trap(int[] height) {
        int n = height.length;

        int[] fromLeft = new int[n];
        fromLeft[0] = height[0];
        for (int i = 1; i < n; i++) {
            fromLeft[i] = Math.max(height[i], fromLeft[i-1]);
        }

        int[] fromRight = new int[n];
        fromRight[n-1] = height[n-1];
        for (int i = n-2; i >= 0; i--) {
            fromRight[i] = Math.max(height[i], fromRight[i+1]);
        }

        int sum = 0;
        for (int i = 1; i < n-1; i++) {
            sum += Math.max(0, Math.min(fromLeft[i-1], fromRight[i+1]) - height[i]);
        }

        return sum;
    }
}
