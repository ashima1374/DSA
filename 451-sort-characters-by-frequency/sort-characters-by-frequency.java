
import java.util.*;

class Solution {
    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        // Step 1: Count frequency of each character
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // Step 2: Sort characters by frequency (descending)
        List<Character> list = new ArrayList<>(map.keySet());

        Collections.sort(list, (a, b) ->
            map.get(b) - map.get(a)
        );

        // Step 3: Build the result
        StringBuilder result = new StringBuilder();

        for (char ch : list) {
            int count = map.get(ch);

            for (int i = 0; i < count; i++) {
                result.append(ch);
            }
        }

        return result.toString();
    }
}
