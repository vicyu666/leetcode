package array;

public class RotateArray {

    /** 189. Rotate RotateArray */
    // Input: [1,2,3,4,5,6,7] and k = 3
    // Output: [5,6,7,1,2,3,4]
    // Explanation:
    // rotate 1 steps to the right: [7,1,2,3,4,5,6]
    // rotate 2 steps to the right: [6,7,1,2,3,4,5]
    // rotate 3 steps to the right: [5,6,7,1,2,3,4]

    public void rotate(int[] nums, int k) {
        k %= nums.length;
        swap(nums,0,nums.length-1);
        swap(nums,0,k-1);
        swap(nums,k,nums.length-1);
    }

    private void swap(int[] nums, int start, int end) {
        while(start<end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }
}
