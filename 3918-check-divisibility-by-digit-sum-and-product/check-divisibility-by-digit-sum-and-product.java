class Solution {
    public boolean checkDivisibility(int n) 
    {
        int num = n;
        int sum = 0;
        int prod = 1;

        while(n > 0)
        {
            int lastDigit = n%10;
            n = n / 10;

            sum += lastDigit;
            prod *= lastDigit;
        }

        int sumOfTwo = sum + prod;

        if(num % sumOfTwo == 0)
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
}