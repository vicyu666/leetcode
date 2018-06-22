package DP;

import java.util.Arrays;

public class coinInLine {

    // 从一侧拿1个或两个，拿到最后一个的人赢
    public boolean firstWinI(int n) {
        // dp[i] means currently remains i coins.
        int[] dp = new int[n+1];
        Arrays.fill(dp,0);
        return helperI(n,dp);
    }
    // i = 0: not visited, 1: lose, 2: win
    private boolean helperI(int n, int[] dp) {
        // memorization improving time complexity
        if(dp[n]!=0) {
            if(dp[n]==1) return false;
            return true;
        }
        // initialize
        if(n<1) dp[n] = 1;
        else if(n==1) dp[n] = 2;
        else if(n==2) dp[n] = 2;
        else if(n==3) dp[n] = 1;
        // skip player b.
        // b get 1 then a can get 1(remains n-2) and 2(n-3).
        // b get 2 then a can get 1(n-3) and 2(n-4).
        else if((helperI(n-2,dp)&& helperI(n-3,dp))||(helperI(n-3,dp)&& helperI(n-4,dp)))
            dp[n] = 2;
        else dp[n] = 1;
        return dp[n]==1?false:true;
    }

    // 从一侧开始拿1个或2个，总值最大的人赢
    public boolean firstWinII(int[] values) {
        // write your code here
        int[] dp = new int[values.length+1];
        Arrays.fill(dp,-1);
        int sum = 0;
        for(int val:values) {
            sum += val;
        }
        return helperII(values,values.length,dp)>=(double)sum/2;
    }
    private int helperII(int[] values, int n, int[] dp) {
        // memorization
        if(dp[n]!=-1) return dp[n];
        // initiate
        int len = values.length;
        if(n<1) dp[0] = 0;
        // 剩一个时a玩家全拿完
        else if(n==1) dp[1] = values[len-1];
        // 剩两个时a玩家全拿完
        else if(n==2) dp[2] = values[len-1]+values[len-2];
        // 剩三个时a玩家拿完前两个
        else if(n==3) dp[3] = values[len-2]+values[len-3];

        else {
            dp[n] = Math.max(Math.min(helperII(values,n-2,dp), helperII(values,n-3,dp))+values[len-n+1],
                             Math.min(helperII(values,n-3,dp), helperII(values,n-3,dp))+values[len-n+1]+values[len-n]);
        }
        return dp[n];
    }

    // 从两侧拿，每次只能选一侧拿一个，总值最大的人赢
    // 区间型dp，dp[i][j]代表还剩下下标从[i,j]的棋子，思路同II
    public boolean PredictTheWinner(int[] nums) {
        int[][] dp = new int[nums.length+1][nums.length+1];
        int[][] flag = new int[nums.length+1][nums.length+1];
        for(int[] f:flag) {
            Arrays.fill(f,-1);
        }
        int sum = 0;
        for(int num:nums) {
            sum += num;
        }
        return helperIII(nums,0,nums.length-1,flag,dp)>=(double)sum/2;
    }
    private int helperIII(int[] nums, int start, int end, int[][] flag, int[][] dp) {
        //
        if(flag[start][end]==1)
            return dp[start][end];
        flag[start][end] = 1;
        //
        if(start>end) dp[start][end] = 0;
        else if(start==end) dp[start][end] = nums[start];
        else if(start+1==end) dp[start][end] = Math.max(nums[start],nums[end]);
        else {
            int left = Math.min(helperIII(nums,start+2,end,flag,dp), helperIII(nums,start+1,end-1,flag,dp))+nums[start];
            int right = Math.min(helperIII(nums,start,end-2,flag,dp), helperIII(nums,start+1,end-1,flag,dp))+nums[end];
            dp[start][end] = Math.max(left,right);
        }
        return dp[start][end];
    }
}
