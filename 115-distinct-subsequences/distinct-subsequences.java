class Solution {
    public int numDistinct(String s, String t) {
        int n = t.length();

        int[] dp = new int[n + 1];

        // Empty t can always be formed
        dp[0] = 1;

        for (int i = 0; i < s.length(); i++) {

            // Traverse backwards
            for (int j = n - 1; j >= 0; j--) {

                if (s.charAt(i) == t.charAt(j)) {
                    dp[j + 1] += dp[j];
                }
            }
        }

        return dp[n];
    }
}