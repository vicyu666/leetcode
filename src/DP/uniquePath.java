package DP;

public class uniquePath {

    // count different ways from (0,0) to (m,n)
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[n][m];
        for(int i=0;i<n;i++) {
            dp[i][0] = 1;
        }
        for(int i=0;i<m;i++) {
            dp[0][i] = 1;
        }
        for(int i=1;i<n;i++) {
            for(int j=1;j<m;j++) {
                dp[i][j] = dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[n-1][m-1];
    }

    // count different ways from (0,0) to (m,n) with some obstacles
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid.length==0||obstacleGrid[0].length==0)
            return 0;
        if(obstacleGrid[0][0]==1) return 0;
        int row=obstacleGrid.length, col=obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = 1;
        for(int i=1;i<row;i++) {
            if(obstacleGrid[i][0]==0 && dp[i-1][0]==1) dp[i][0] = 1;
            else dp[i][0] = 0;
        }
        for(int i=1;i<col;i++) {
            if(obstacleGrid[0][i]==0 && dp[0][i-1]==1) dp[0][i] = 1;
            else dp[0][i] = 0;
        }
        for(int i=1;i<row;i++) {
            for(int j=1;j<col;j++) {
                if(obstacleGrid[i][j]==0) dp[i][j] = dp[i-1][j]+dp[i][j-1];
                else dp[i][j] = 0;
            }
        }
        return dp[row-1][col-1];
    }

    // find min value from (0,0) to (m,n)
    public int minPathSum(int[][] grid) {
        if(grid.length==0||grid[0].length==0) return 0;
        int row = grid.length, col = grid[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = grid[0][0];
        for(int i=1;i<row;i++) {
            dp[i][0] = dp[i-1][0]+grid[i][0];
        }
        for(int i=1;i<col;i++) {
            dp[0][i] = dp[0][i-1]+grid[0][i];
        }
        for(int i=1;i<row;i++) {
            for(int j=1;j<col;j++) {
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }
        return dp[row-1][col-1];
    }
}
