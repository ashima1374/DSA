class Solution {
    public int missingMultiple(int[] nums, int k) 
    {
        HashSet<Integer> num = new HashSet<>();
        for(int i = 0; i < nums.length; i++)
        {
            if(nums[i] % k == 0)
            {
                num.add(nums[i]);
            }
        }

        int i = 1;

        while(true)
        {
            int ans = k * i;
            if(!num.contains(ans))
                return ans;
            i++;
        }
    }
}