package DP;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class frogJump {
    // dp map里存的key是所有的石头，value是到达这个石头所有可能的最后一步步长。
    public boolean canCross(int[] stones) {
        Map<Integer,Set<Integer>> dp = new HashMap<>();
        for(int stone : stones) {
            dp.put(stone,new HashSet<>());
        }
        dp.get(0).add(0);
        for(int stone : stones) {
            for(int k : dp.get(stone)) {
                if(k-1 > 0 && dp.containsKey(stone + k-1)) {
                    dp.get(stone+k-1).add(k-1);
                }
                if(k > 0 && dp.containsKey(stone + k)) {
                    dp.get(stone+k).add(k);
                }
                if(k+1 > 0 && dp.containsKey(stone + k+1)) {
                    dp.get(stone+k+1).add(k+1);
                }
            }
        }
        return !dp.get(stones[stones.length-1]).isEmpty();
    }

    /** 55. Jump Game */
    // 时间复杂度太高 最优greedy
    // dp[i] 代表可不可以从0到达第i个index
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length == 0) {
            return false;
        }
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;
        for(int i=0; i<nums.length; i++) {
            for(int j=0; j<i; j++) {
                if(dp[i] == true) {
                    continue;
                }
                dp[i] = dp[j] && (nums[j] >= i - j);
            }
        }
        return dp[nums.length - 1];
    }

}
