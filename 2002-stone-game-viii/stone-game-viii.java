class Solution {
    public int stoneGameVIII(int[] stones) {

        int n = stones.length;

        // prefix[i] = sum of stones[0] ... stones[i]
        int[] prefix = new int[n];

        prefix[0] = stones[0];

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + stones[i];
        }

        // Initially, the current player can take all stones.
        int best = prefix[n - 1];

        // We start from n - 2 because the first move
        // must take at least 2 stones.
        for (int i = n - 2; i >= 1; i--) {

            // If we choose prefix[i],
            // we gain prefix[i],
            // but then the opponent gets the advantage "best".
            int current = prefix[i] - best;

            // Choose the better strategy:
            // 1. Keep the previous best
            // 2. Choose prefix[i]
            best = Math.max(best, current);
        }

        return best;
    }
}