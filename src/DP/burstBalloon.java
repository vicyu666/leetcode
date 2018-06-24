package DP;

public class burstBalloon {

    // dp[i][j]代表从i到j已经都打爆了的最大得分值
    // 新数组首尾手动设置为1避免边界检查
    public int maxCoins(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[][] dp = new int[len+2][len+2];
        int[][] visited = new int[len+2][len+2];
        int[] coverCorner = new int[len+2];
        for(int i=1; i<len+1; i++) {
            coverCorner[i] = nums[i-1];
        }
        coverCorner[0] = 1;
        coverCorner[len+1] = 1;
        return helper(coverCorner,1,len,dp,visited);
    }
    private int helper(int[] coverCorner, int i, int j, int[][] dp, int[][] visited) {
        if(visited[i][j] == 1) {
            return dp[i][j];
        }
        visited[i][j] = 1;
        if(i == j) {
            dp[i][j] = coverCorner[i-1] * coverCorner[i] * coverCorner[i+1];
        } else {
            for(int k=i; k<=j; k++) {
                dp[i][j] = Math.max(dp[i][j], helper(coverCorner,i,k-1,dp,visited) +
                                              helper(coverCorner,k+1,j,dp,visited) +
                                              coverCorner[i-1] * coverCorner[k] * coverCorner[j+1]);
            }
        }
        return dp[i][j];
    }
}
