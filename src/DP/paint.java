package DP;

public class paint {

    /** 256. Paint House */
    // dp[i][j]即第i个房子画第j号颜色之前的总花费
    // i房间可以取a号颜色的条件是i-1房间取了b和c号色
    public int minCost(int[][] costs) {
        if(costs.length==0) return 0;
        int[][] dp = new int [costs.length][3];
        for(int i=0;i<3;i++) {
            dp[0][i] = costs[0][i];
        }
        for(int i=1;i<costs.length;i++) {
            for(int j=0;j<3;j++) {
                dp[i][j] = Math.min(dp[i-1][(j+1)%3],dp[i-1][(j+2)%3])+costs[i][j];
            }
        }
        return Math.min(dp[costs.length-1][0],Math.min(dp[costs.length-1][1],dp[costs.length-1][2]));
    }

    /** 276. Paint Fence */
    // 递推公式见笔记

    public int numWays(int n, int k) {
        if(n==0||k==0) return 0;
        if(n==1) return k;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = k;
        dp[2] = k*k;
        for(int i=3;i<=n;i++) {
            dp[i] = (k-1)*(dp[i-1]+dp[i-2]);
        }
        return dp[n];
    }
}
