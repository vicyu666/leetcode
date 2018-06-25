package BinarySearch;

public class SearchRotated {

    /** 33. Search in Rotated Sorted Array */
    // Example 1:
    //
    // Input: nums = [4,5,6,7,0,1,2], target = 0
    // Output: 4

    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while(left + 1 < right) {
            int mid = left + (right - left)/2;
            if(nums[mid] > nums[right]) {
                // 要保证圈在这个范围内，因此左右边界都要判断
                if(nums[mid] >= target && nums[left] <= target) {
                    right = mid;
                } else {
                    left = mid;
                }
            } else {
                if(nums[mid] <= target && nums[right] >= target) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
        }
        if(nums[left] == target) {
            return left;
        } else if(nums[right] == target) {
            return right;
        } else {
            return -1;
        }
    }

    /** 81. Search in Rotated Sorted Array II */
    // exist duplicate elements
    //
    // if mid<right then right part is monotonically increasing
    // if mid>right then left part is monotonically decreasing
    // if mid==right then move right pointer one step left

    public boolean search2(int[] nums, int target) {
        if(nums.length==0) return false;
        int left=0, right=nums.length-1;
        while(left+1<right) {
            int mid = left+(right-left)/2;
            if(nums[mid]<nums[right]) {
                if(nums[mid]<target&&target<=nums[right])  left=mid;
                else right=mid;
            }
            else if(nums[mid]>nums[right]) {
                if(nums[mid]>target&&target>=nums[left]) right=mid;
                else left=mid;
            }
            else right--;
        }
        return nums[left]==target||nums[right]==target;
    }

    /** 153. Find Minimum in Rotated Sorted Array */
    // Example 1:
    //
    // Input: [3,4,5,1,2]
    // Output: 1

    public int findMin(int[] nums) {
        if(nums.length==0) return -1;
        int res = Integer.MAX_VALUE;
        int left=0, right=nums.length-1;
        while(left+1<right) {
            int mid = left+(right-left)/2;
            if(nums[mid]>nums[right]) left = mid;
            else {
                res = Math.min(res,nums[mid]);
                right = mid;
            }
        }
        return Math.min(res,Math.min(nums[left],nums[right]));
    }
}
