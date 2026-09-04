class Solution 
{
    public int firstStableIndex(int[] nums, int k) 
    {
        int max = Integer.MIN_VALUE;
        
        for(int i = 0; i<nums.length;i++)
        {
            if(nums[i] > max)
            {
                max = Math.max(nums[i], max);
            }
            
            int min = Integer.MAX_VALUE;

            for(int m = i; m < nums.length; m++)
            {
                if(nums[m] < min)
                {
                    min = nums[m];
                }
            }

            if((max - min) <= k)
            {
                return i;
            }
        }
        return -1;
    }
}