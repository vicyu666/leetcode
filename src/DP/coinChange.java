package DP;

import java.util.Arrays;

public class coinChange {
    // O(m * n)
    public int coinChange(int[] coins, int amount) {
        if(coins == null || coins.length == 0 || amount <= 0) {
            return 0;
        }

        int[] dp = new int[amount+1];
        Arrays.fill(dp,amount+1);
        dp[0] = 0;

        for(int coin : coins) {
            for(int i=coin; i<=amount; i++) {
                dp[i] = Math.min(dp[i], dp[i-coin]+1);
            }
        }
        return dp[amount] == amount+1? -1 : dp[amount];
    }
}
