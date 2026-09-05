class Solution {
    public int firstStableIndex(int[] nums, int k) {

        int n = nums.length;

        // suffixMin[i] = minimum value from i to n-1
        int[] suffixMin = new int[n];

        suffixMin[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(nums[i], suffixMin[i + 1]);
        }

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {

            // max(nums[0...i])
            max = Math.max(max, nums[i]);

            // min(nums[i...n-1]) = suffixMin[i]
            int score = max - suffixMin[i];

            if (score <= k) {
                return i;
            }
        }

        return -1;
    }
}