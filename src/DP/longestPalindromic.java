package DP;

public class longestPalindromic {

    /** 5. Longest Palindromic Substring */
    // 区间型dp
    public String longestPalindrome(String s) {
        char[] letters = s.toCharArray();;
        boolean[][] dp = new boolean[letters.length+1][letters.length+1];
        int[][] flag = new int[letters.length+1][letters.length+1];
        int max_length = 0;
        String res = "";
        for(int i=0;i<s.length();i++) {
            for(int j=i;j<s.length();j++) {
                if(helper(i,j,dp,letters,flag)&&j-i>=max_length) {
                    res = s.substring(i,j+1);
                    max_length = j-i+1;
                }
            }
        }
        return res;
    }
    private boolean helper(int i,int j,boolean[][] dp,char[] letters,int[][] flag) {
        //
        if(flag[i][j]==1) return dp[i][j];
        flag[i][j] = 1;
        //
        if(i>j) dp[i][j] = false;
        else if(i==j) dp[i][j] = true;
        else if(i+1==j) dp[i][j] = letters[i]==letters[j];
        else if(letters[i]==letters[j]) dp[i][j] = helper(i+1,j-1,dp,letters,flag);
        else dp[i][j] = false;
        return dp[i][j];
    }

    // enumerate approach
    public int longestPalindromeEnumerate(String s) {
        int longest = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isPalindrome(s, i, j)) {
                    longest = Math.max(longest, j - i + 1);
                }
            }
        }
        return longest;
    }
    private boolean isPalindrome(String s, int left, int right) {
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /** 516. Longest Palindromic Subsequence */
    // dp[i][j]代表下标从i到j段存在Palindromic Subsequence的最长长度
    public int longestPalindromeSubseq(String s) {
        char[] letters = s.toCharArray();;
        int[][] dp = new int[letters.length+1][letters.length+1];
        int[][] flag = new int[letters.length+1][letters.length+1];
        int max_length = 0;
        for(int i=0;i<s.length();i++) {
            for(int j=i;j<s.length();j++) {
                max_length = Math.max(helper(i,j,dp,letters,flag),max_length);
            }
        }
        return max_length;
    }
    private int helper(int i,int j,int[][] dp,char[] letters,int[][] flag) {
        //
        if(flag[i][j]==1) return dp[i][j];
        flag[i][j] = 1;
        //
        if(i>j) dp[i][j] = 1;
        else if(i==j) dp[i][j] = 1;
        else if(i+1==j) dp[i][j] = letters[i]==letters[j]?2:1;
        else if(letters[i]==letters[j]) dp[i][j] = 2+helper(i+1,j-1,dp,letters,flag);
        else dp[i][j] = Math.max(helper(i,j-1,dp,letters,flag),helper(i+1,j,dp,letters,flag));
        return dp[i][j];
    }
}
