package DP;

public class maxSub {

    /** 53. Maximum Subarray */

    public int maxSubArray(int[] nums) {
        if(nums.length==0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];
        for(int i=1;i<nums.length;i++) {
            dp[i] = Math.max(dp[i-1]+nums[i],nums[i]);
            res = Math.max(dp[i],res);
        }
        return nums.length==1?nums[0]:res;
    }

    /** 152. Maximum Product Subarray */
    // 维护i位之前的最大，最小值，
    // 则第i位的最大最小值来自nums[i],max[i-1]*nums[i],min[i-1]*nums[i]，
    public int maxProduct(int[] nums) {
        if(nums.length==0) return 0;
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];
        max[0] = min[0] = nums[0];
        int res = nums[0];
        for(int i=1;i<nums.length;i++) {
            max[i] = Math.max(nums[i],Math.max(max[i-1]*nums[i],min[i-1]*nums[i]));
            min[i] = Math.min(nums[i],Math.min(max[i-1]*nums[i],min[i-1]*nums[i]));
            res = Math.max(res,max[i]);
        }
        return res;
    }

}
