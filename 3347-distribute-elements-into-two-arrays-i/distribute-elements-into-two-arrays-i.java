class Solution {
    public int[] resultArray(int[] nums) {

        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();

        // First operation
        arr1.add(nums[0]);

        // Second operation
        arr2.add(nums[1]);

        // Remaining elements
        for (int i = 2; i < nums.length; i++) {

            if (arr1.get(arr1.size() - 1) > arr2.get(arr2.size() - 1)) {
                arr1.add(nums[i]);
            } 
            else {
                arr2.add(nums[i]);
            }
        }

        // Combine arr1 and arr2
        arr1.addAll(arr2);

        int[] result = new int[arr1.size()];

        for (int i = 0; i < arr1.size(); i++) {
            result[i] = arr1.get(i);
        }

        return result;
    }
}