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
    int[][] dp;
    int[][] visited;
    public int longestIncreasingPath(int[][] matrix) {
        int res = 0;
        if(matrix.length==0||matrix[0].length==0) return res;
        int row = matrix.length, col = matrix[0].length;
        dp = new int[row][col];
        visited = new int[row][col];
        for(int[] i:dp) {
            Arrays.fill(i,1);
        }
        for(int[] i:visited) {
            Arrays.fill(i,0);
        }
        for(int i=0;i<row;i++) {
            for(int j=0;j<col;j++) {
                res = Math.max(res,search(matrix,i,j));
            }
        }
        return res;
    }

    // dp[x][y] = dp[nx][ny] + 1 四个方向的最大值
    private int search(int[][] matrix,int x,int y) {
        if(visited[x][y]==1) return dp[x][y];
        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};
        for(int i=0;i<4;i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(nx>=0 && nx<matrix.length && ny>=0 && ny<matrix[0].length) {
                if(matrix[x][y]<matrix[nx][ny]) {
                    dp[x][y] = Math.max(dp[x][y],1+search(matrix,nx,ny));
                }
            }
        }
        visited[x][y] = 1;
        return dp[x][y];
    }
}
