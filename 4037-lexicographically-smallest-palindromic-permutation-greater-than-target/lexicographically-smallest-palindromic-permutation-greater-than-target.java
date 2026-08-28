class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];

        // Count characters
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Check whether palindrome is possible
        int odd = 0;
        int middle = -1;

        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 == 1) {
                odd++;
                middle = i;
            }
        }

        if (odd > 1) {
            return "";
        }

        // Build frequency of first half
        int[] halfCount = new int[26];

        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        // Try to construct the smallest answer
        char[] half = new char[n / 2];

        for (int i = 0; i < half.length; i++) {
            int targetChar = target.charAt(i) - 'a';

            // Try to keep the same character as target
            if (targetChar >= 0 && targetChar < 26
                    && halfCount[targetChar] > 0) {

                half[i] = (char) ('a' + targetChar);
                halfCount[targetChar]--;

            } else {
                // Find the smallest character greater than target[i]
                int bigger = targetChar + 1;

                while (bigger < 26 && halfCount[bigger] == 0) {
                    bigger++;
                }

                if (bigger == 26) {
                    // Need to backtrack
                    return findAnswer(half, halfCount, i,
                            middle, target);
                }

                half[i] = (char) ('a' + bigger);
                halfCount[bigger]--;

                // Fill remaining positions with smallest characters
                int pos = i + 1;

                for (int c = 0; c < 26; c++) {
                    while (halfCount[c] > 0) {
                        half[pos++] = (char) ('a' + c);
                        halfCount[c]--;
                    }
                }

                return makePalindrome(half, middle);
            }
        }

        // Same first half as target
        String answer = makePalindrome(half, middle);

        if (answer.compareTo(target) > 0) {
            return answer;
        }

        // Need next permutation
        return findAnswer(half, halfCount, half.length,
                middle, target);
    }

    private String findAnswer(char[] half, int[] count,
                              int pos, int middle, String target) {

        for (int i = pos - 1; i >= 0; i--) {

            count[half[i] - 'a']++;

            int current = half[i] - 'a';

            for (int c = current + 1; c < 26; c++) {

                if (count[c] > 0) {

                    half[i] = (char) ('a' + c);
                    count[c]--;

                    int k = i + 1;

                    for (int x = 0; x < 26; x++) {
                        while (count[x] > 0) {
                            half[k++] = (char) ('a' + x);
                            count[x]--;
                        }
                    }

                    return makePalindrome(half, middle);
                }
            }
        }

        return "";
    }

    private String makePalindrome(char[] half, int middle) {

        StringBuilder result = new StringBuilder();

        for (char c : half) {
            result.append(c);
        }

        if (middle != -1) {
            result.append((char) ('a' + middle));
        }

        for (int i = half.length - 1; i >= 0; i--) {
            result.append(half[i]);
        }

        return result.toString();
    }
}