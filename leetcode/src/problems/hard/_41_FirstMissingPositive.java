package problems.hard;

public class _41_FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        if (n == 0) { return 1; }
        if (n == 1) { return nums[0] == 1 ? 2 : 1; }

        for(int i = 0; i < n; i++) {
            if(nums[i] > 0 && nums[i] <= n && nums[i] != i+1 && nums[nums[i]-1] != nums[i]) {
                swap(nums, i, nums[i]-1);
                i--;
            }
        }

        for(int i = 0; i < n; i++) {
            if(nums[i] != i+1) {
                return i+1;
            }
        }

        return n+1;
    }

    void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
