class Solution {
    public int countCommas(int n) 
    {
        int num = n;
        int lastDigit = 0;
        int count= 0;
        while(n > 0)
        {
            n = n/10;
            lastDigit = n%10;
            count++;
        }

        int comma= 0;
        if(count > 3)
        {
            for(int i = 1000; i<= num;i++)
            {
                comma++;
            }

            return comma;
        }

        return 0;
    }
}