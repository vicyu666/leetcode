package DP;

public class decodeWays {
    public int numDecodings(String s) {
        int len = s.length();
        if (s == null || len == 0) {
            return 0;
        }
        char[] letters = s.toCharArray();
        int[] dp = new int[len + 1];
        dp[0] = 1;
        if(letters[0] != '0') {
            dp[1] = 1;
        }
        for(int i=2; i<len+1; i++) {
            if(letters[i-1] != '0') {
                dp[i] += dp[i-1];
            }
            int twoDigits = (letters[i-2] - '0') * 10 + letters[i-1] - '0';
            if(twoDigits >= 10 && twoDigits <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[len];
    }
}
