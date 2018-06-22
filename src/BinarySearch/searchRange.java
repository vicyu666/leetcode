package BinarySearch;

public class searchRange {

    /** 34. Search for a Range */
    // Example 1:
    //
    // Input: nums = [5,7,7,8,8,10], target = 8
    // Output: [3,4]

    public int[] searchRange(int[] nums, int target) {
        if(nums.length==0) return new int[]{-1,-1};
        int[] res = new int[2];

        int left = 0, right = nums.length-1;
        while(left+1<right) {
            int mid = left + (right-left)/2;
            if(nums[mid]<target) left = mid;
            if(nums[mid]>=target) right = mid;
        }
        if(nums[left]!=target&&nums[right]!=target)
            return new int[]{-1,-1};
        res[0]=nums[left]==target?left:right;

        left = 0; right = nums.length-1;
        while(left+1<right) {
            int mid = left + (right-left)/2;
            if(nums[mid]<=target) left = mid;
            if(nums[mid]>target) right = mid;
        }
        res[1]=nums[right]==target?right:left;
        return res;
    }
}
