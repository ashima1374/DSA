class Solution {
    public boolean sumGame(String num) {

        int n = num.length();

        int leftSum = 0;
        int rightSum = 0;

        int leftQ = 0;
        int rightQ = 0;

        for (int i = 0; i < n / 2; i++) {
            if (num.charAt(i) == '?') {
                leftQ++;
            } else {
                leftSum += num.charAt(i) - '0';
            }
        }

        for (int i = n / 2; i < n; i++) {
            if (num.charAt(i) == '?') {
                rightQ++;
            } else {
                rightSum += num.charAt(i) - '0';
            }
        }

        int qDiff = leftQ - rightQ;
        int sumDiff = leftSum - rightSum;

        // If the number of '?' is odd between the halves,
        // Alice can force a win.
        if (qDiff % 2 != 0) {
            return true;
        }

        // Bob wins only if the difference can be perfectly cancelled.
        return sumDiff != 9 * (-qDiff) / 2;
    }
}