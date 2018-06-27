package DP;

import java.util.Arrays;


public class LIS {

    /** 300. Longest Increasing Subsequence */
    // O(n^2)
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);
        int res = 0;
        for(int i=0;i<nums.length;i++) {
            for(int j=i;j<nums.length;j++) {
                if(nums[j]>nums[i])
                    dp[j] = Math.max(dp[j],dp[i]+1);
                res = Math.max(res,dp[j]);
            }
        }
        return res;
    }

    /** 674. Longest Continuous Increasing Subsequence */
    /** or Longest Increasing Substring */
    // O(n)
    public int findLengthOfLCIS(int[] nums) {
        if(nums.length==0) return 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);
        int res = 1;
        for(int i=1;i<nums.length;i++) {
            if(nums[i]>nums[i-1]) dp[i] = dp[i-1]+1;
            res = Math.max(res,dp[i]);
        }
        return res;
    }

    /** 329. Longest Increasing Path in a Matrix */
    // memorization search + dfs
    // O(row*col) 记忆化搜索时遇到计算过的点会直接返回。

    int[][] dir = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null) {
            return 0;
        }
        if(matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int row = matrix.length, col = matrix[0].length;
        int[][] dp = new int[row][col];
        int[][] visited = new int[row][col];
        // for every node there is at least 1 step path for itself
        for(int[] d : dp) {
            Arrays.fill(d,1);
        }
        int res = 0;
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                dp[i][j] = helper(matrix,i,j,dp,visited);
                res = Math.max(res,dp[i][j]);
            }
        }
        return res;
    }
    private int helper(int[][] matrix, int i, int j, int[][] dp, int[][] visited) {
        if(visited[i][j] == 1) {
            return dp[i][j];
        }
        visited[i][j] = 1;

        for(int n=0;n<4;n++) {
            int nx = i + dir[n][0];
            int ny = j + dir[n][1];
            if(nx>=0 && nx<matrix.length && ny>=0 && ny<matrix[0].length) {
                if(matrix[nx][ny] > matrix[i][j]) {
                    dp[i][j] = Math.max(helper(matrix,nx,ny,dp,visited)+1,dp[i][j]);
                }
            }
        }
        return dp[i][j];
    }
}
