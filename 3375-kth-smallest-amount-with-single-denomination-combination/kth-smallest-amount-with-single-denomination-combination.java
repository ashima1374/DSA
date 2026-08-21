import java.util.*;

class Solution {

    long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    long count(long x, int[] coins) {
        int n = coins.length;
        long total = 0;

        // All non-empty subsets
        for (int mask = 1; mask < (1 << n); mask++) {

            long common = 1;
            int bits = 0;
            boolean valid = true;

            for (int i = 0; i < n; i++) {

                if ((mask & (1 << i)) != 0) {
                    bits++;

                    common = lcm(common, coins[i]);

                    // We don't need this subset if LCM > x
                    if (common > x) {
                        valid = false;
                        break;
                    }
                }
            }

            if (!valid) {
                continue;
            }

            long multiples = x / common;

            if (bits % 2 == 1) {
                total += multiples;
            } else {
                total -= multiples;
            }
        }

        return total;
    }

    public long findKthSmallest(int[] coins, int k) {

        long minCoin = Integer.MAX_VALUE;

        for (int coin : coins) {
            minCoin = Math.min(minCoin, coin);
        }

        long low = 1;
        long high = minCoin * (long) k;

        while (low < high) {

            long mid = low + (high - low) / 2;

            if (count(mid, coins) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }
}