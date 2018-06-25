package DP;

public class editDistance {

    /** 72. Edit Distance */
    // 画出（m+1）*（n+1）匹配矩阵来分析
    // dp[i][j]代表string1在i位以前（不包含i位）与string2在j位以前（不包含j位）的匹配情况
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1+1][len2+1];
        for(int i=0;i<len1+1;i++) {
            dp[i][0] = i;
        }
        for(int i=0;i<len2+1;i++) {
            dp[0][i] = i;
        }
        for(int i=1;i<len1+1;i++) {
            for(int j=1;j<len2+1;j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1))
                    dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j]+1,dp[i][j-1]+1));
                else dp[i][j] = 1+Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]));
            }
        }
        return dp[len1][len2];
    }

    /** 161. One Edit Distance */
    // string operation
    // may be the previous question of above
    public boolean isOneEditDistance(String s, String t) {
        if(s == null || t == null) {
            return false;
        }
        int sLength = s.length();
        int tLength = t.length();
        if(Math.abs(sLength-tLength) > 1) {
            return false;
        }
        for(int i=0; i<Math.min(sLength,tLength); i++) {
            if(s.charAt(i) == t.charAt(i)) {
                continue;
            } else if(sLength == tLength) {
                return s.substring(i+1).equals(t.substring(i+1));
            } else if(sLength < tLength) {
                return s.substring(i).equals(t.substring(i+1));
            } else {
                return s.substring(i+1).equals(t.substring(i));
            }
        }
        // The question is to determine if they are both one edit distance apart.
        // If they are equal, they are NOT one edit distance apart.
        return sLength != tLength;
    }
}
