package DP;

public class burstBalloon {

    // dp[i][j]代表从i到j已经都打爆了的最大得分值
    // 新数组首尾手动设置为1避免边界检查
    public int maxCoins(int[] nums) {
        if(nums.length==0) return 0;
        int len = nums.length;
        int[][] dp = new int[len+2][len+2];
        int[][] flag = new int[len+2][len+2];
        int[] newNums = new int[len+2];
        for(int i=1;i<len+1;i++) {
            newNums[i] = nums[i-1];
        }
        newNums[0] = 1;
        newNums[len+1] = 1;
        return helper(1,len,dp,flag,newNums);
    }
    private int helper(int i,int j,int[][] dp,int[][] flag,int[] nums) {
        if(flag[i][j]==1) return dp[i][j];
        flag[i][j] = 1;
        dp[i][j] = 0;
        for(int k=i;k<=j;k++) {
            dp[i][j] = Math.max(dp[i][j],
                    helper(i,k-1,dp,flag,nums)+helper(k+1,j,dp,flag,nums)+nums[i-1]*nums[k]*nums[j+1]);
        }
        return dp[i][j];
    }
}
