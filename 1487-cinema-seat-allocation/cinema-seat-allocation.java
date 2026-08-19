import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        Map<Integer, Integer> map = new HashMap<>();

        // Store reserved seats using bitmask
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            // IMPORTANT: col - 1
            map.put(row, map.getOrDefault(row, 0) | (1 << (col - 1)));
        }

        // Rows with no reservations can always have 2 groups
        int ans = (n - map.size()) * 2;

        for (int mask : map.values()) {

            // Seats 2,3,4,5
            int left = (1 << 1) | (1 << 2) | (1 << 3) | (1 << 4);

            // Seats 4,5,6,7
            int middle = (1 << 3) | (1 << 4) | (1 << 5) | (1 << 6);

            // Seats 6,7,8,9
            int right = (1 << 5) | (1 << 6) | (1 << 7) | (1 << 8);

            boolean canLeft = (mask & left) == 0;
            boolean canMiddle = (mask & middle) == 0;
            boolean canRight = (mask & right) == 0;

            if (canLeft && canRight) {
                ans += 2;
            } 
            else if (canLeft || canMiddle || canRight) {
                ans += 1;
            }
        }

        return ans;
    }
}