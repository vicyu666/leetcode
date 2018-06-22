package DP;

public class maxSquare {
    /** 221. Maximal Square */

    public int maximalSquare(char[][] matrix) {
        if(matrix.length==0||matrix[0].length==0) return 0;
        int row = matrix.length, col = matrix[0].length;
        int[][] dp = new int[row][col];
        int res = 0;

        for(int i=0;i<row;i++) {
            dp[i][0] = matrix[i][0]=='1'?1:0;
            res = Math.max(res,dp[i][0]);
        }
        for(int i=0;i<col;i++) {
            dp[0][i] = matrix[0][i]=='1'?1:0;
            res = Math.max(res,dp[0][i]);
        }

        for(int i=1;i<row;i++) {
            for(int j=1;j<col;j++) {
                if(matrix[i][j]=='1')
                    // 通过向上 向左 向左上三个矩形的最小值确定当前矩形可达到的边长最大值
                    dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i][j-1],dp[i-1][j]))+1;
                    //dp[i][j] = Math.min(dp[i-1][j-1],Math.min(upLength(matrix,i-1,j),leftLength(matrix,i,j-1)))+1;
                else dp[i][j] = 0;
                res = Math.max(res,dp[i][j]);
            }
        }
        return res*res;
    }
}
