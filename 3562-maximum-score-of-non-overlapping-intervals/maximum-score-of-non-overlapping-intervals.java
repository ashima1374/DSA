import java.util.*;

class Solution {

    int n;
    int[][] arr;
    long[][] dp;
    List<Integer>[][] choice;
    int[] starts;

    public int[] maximumWeight(List<List<Integer>> intervals) {

        n = intervals.size();

        // arr = {start, end, weight, originalIndex}
        arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        // Sort by start time
        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }

            return Integer.compare(a[1], b[1]);
        });

        starts = new int[n];

        for (int i = 0; i < n; i++) {
            starts[i] = arr[i][0];
        }

        dp = new long[n + 1][5];

        choice = new ArrayList[n + 1][5];

        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                choice[i][k] = new ArrayList<>();
            }
        }

        // DP
        for (int i = n - 1; i >= 0; i--) {

            for (int k = 1; k <= 4; k++) {

                // -----------------------
                // 1. DON'T TAKE
                // -----------------------

                long skipScore = dp[i + 1][k];

                List<Integer> skipList =
                        new ArrayList<>(choice[i + 1][k]);


                // -----------------------
                // 2. TAKE
                // -----------------------

                int next = findNext(i);

                long takeScore =
                        arr[i][2] + dp[next][k - 1];

                List<Integer> takeList =
                        new ArrayList<>();

                takeList.add(arr[i][3]);

                takeList.addAll(choice[next][k - 1]);

                // VERY IMPORTANT:
                // Answer must be sorted by original index
                Collections.sort(takeList);


                // -----------------------
                // 3. COMPARE
                // -----------------------

                if (takeScore > skipScore) {

                    dp[i][k] = takeScore;
                    choice[i][k] = takeList;

                }
                else if (takeScore < skipScore) {

                    dp[i][k] = skipScore;
                    choice[i][k] = skipList;

                }
                else {

                    // Same score
                    // Choose lexicographically smaller list

                    if (compare(takeList, skipList) < 0) {

                        dp[i][k] = takeScore;
                        choice[i][k] = takeList;

                    }
                    else {

                        dp[i][k] = skipScore;
                        choice[i][k] = skipList;
                    }
                }
            }
        }

        List<Integer> answer = choice[0][4];

        Collections.sort(answer);

        int[] result = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }


    // Find first interval whose start > current end
    private int findNext(int i) {

        int end = arr[i][1];

        int left = i + 1;
        int right = n;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (starts[mid] > end) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }

        return left;
    }


    // Lexicographical comparison
    private int compare(List<Integer> a, List<Integer> b) {

        int size = Math.min(a.size(), b.size());

        for (int i = 0; i < size; i++) {

            if (!a.get(i).equals(b.get(i))) {

                return Integer.compare(
                        a.get(i),
                        b.get(i)
                );
            }
        }

        return Integer.compare(a.size(), b.size());
    }
}