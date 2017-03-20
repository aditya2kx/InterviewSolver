/**
 * Created by adi2ky on 3/19/17.
 * Problem: Given an array of integers, return indices of the two numbers such that they add up to a specific target.

 You may assume that each input would have exactly one solution, and you may not use the same element twice.

 Example:
 Given nums = [2, 7, 11, 15], target = 9,

 Because nums[0] + nums[1] = 2 + 7 = 9,
 return [0, 1].
 */
public class TwoSum {
    /**
     * Approach: Basically, gets two pointers and scans the entire array
     * for the indices adding up to target number.
     *
     * Time Complexity : O(n^2)
     * Storage Capacity : O(1)
     */

    public static int[] bruteForce(int[] nums, int target) {
        for (int firstIndex = 0; firstIndex < nums.length - 1; firstIndex++) {
            for (int secondIndex = firstIndex + 1; secondIndex < nums.length; secondIndex++) {
                if (nums[firstIndex] + nums[secondIndex] == target) {
                    return new int[]{firstIndex, secondIndex};
                }
            }
        }
        throw new IllegalArgumentException("Input Array doesn't have any combinations to add up to target");
    }
}
