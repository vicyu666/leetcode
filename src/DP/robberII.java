package DP;

public class robberII {
    // circle array, start and end cannot be robbed at same time.
    // 拆分法，实现一个api，两次调用
    public int rob(int[] nums) {
        if(nums.length==0) return 0;
        if(nums.length==1) return nums[0];
        return Math.max(rob(nums,0,nums.length-2),rob(nums,1,nums.length-1));
    }
    public int rob(int[] nums,int a,int b) {
        if(b-a<2) return Math.max(nums[a],nums[b]);
        int[] dp = new int[nums.length-1];
        dp[0] = nums[a];
        dp[1] = Math.max(nums[a],nums[a+1]);
        for(int i=2;i<nums.length-1;i++) {
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[a+i]);
        }
        return dp[nums.length-2];
    }
}
