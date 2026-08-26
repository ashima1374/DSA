class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        String ans = "";

        for (int i = 0; i < s.length(); i++) {

            int count = 0;

            for (int j = i; j < s.length(); j++) {

                if (s.charAt(j) == '1') {
                    count++;
                }

                // Exactly k ones
                if (count == k) {

                    String current = s.substring(i, j + 1);

                    // First valid answer
                    if (ans.equals("")) {
                        ans = current;
                    }
                    // Shorter substring
                    else if (current.length() < ans.length()) {
                        ans = current;
                    }
                    // Same length -> lexicographically smaller
                    else if (current.length() == ans.length()
                            && current.compareTo(ans) < 0) {
                        ans = current;
                    }

                    // Adding more characters will only increase
                    // the number of 1s, so this starting index
                    // cannot give another valid substring.
                    break;
                }
            }
        }

        return ans;
    }
}