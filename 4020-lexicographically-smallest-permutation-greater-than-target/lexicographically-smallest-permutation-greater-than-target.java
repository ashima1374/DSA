class Solution {

    public String lexGreaterPermutation(String s, String target) {

        int n = s.length();

        // Try to make the first difference
        // as far right as possible.
        for (int i = n - 1; i >= 0; i--) {

            int[] freq = new int[26];

            // Count characters of s
            for (char ch : s.toCharArray()) {
                freq[ch - 'a']++;
            }

            // Match target[0 ... i-1]
            boolean possible = true;

            for (int j = 0; j < i; j++) {

                int idx = target.charAt(j) - 'a';

                if (freq[idx] == 0) {
                    possible = false;
                    break;
                }

                freq[idx]--;
            }

            if (!possible) {
                continue;
            }

            // At position i, choose the smallest
            // available character greater than target[i].
            int targetIndex = target.charAt(i) - 'a';

            for (int c = targetIndex + 1; c < 26; c++) {

                if (freq[c] == 0) {
                    continue;
                }

                StringBuilder ans = new StringBuilder();

                // Keep prefix same as target
                for (int j = 0; j < i; j++) {
                    ans.append(target.charAt(j));
                }

                // Make this position greater
                ans.append((char) ('a' + c));
                freq[c]--;

                // Put remaining characters
                // in smallest possible order
                for (int x = 0; x < 26; x++) {
                    while (freq[x] > 0) {
                        ans.append((char) ('a' + x));
                        freq[x]--;
                    }
                }

                return ans.toString();
            }
        }

        return "";
    }
}