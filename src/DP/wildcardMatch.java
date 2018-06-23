package DP;

public class wildcardMatch {

    /** 44. Wildcard Matching */
    // String s = "" is not equal to s = null
    // It is not good enough in the interview, it takes O(mn) time and O(mn) space.
    // Improvement:
    //      1) optimize 2d dp to 1d dp, this will save space, reduce space complexity to O(N).
    //      2) use iterative 2-pointer, reduce time complexity to O(N).

    // dp[i][j] means from index 0 to i in s1 and from index 0 to j in s2 is match.
    public boolean isMatch(String s, String p) {
        if(s==null || p==null) return false;
        int len_s = s.length(), len_p = p.length();
        boolean[][] dp = new boolean[len_s][len_p];
        int[][] visited = new int[len_s][len_p];
        return helper(s,p,0,0,dp,visited);
    }
    private boolean helper(String s,String p,int i,int j,boolean[][] dp,int[][] visited) {
        // if reach the end, check
        if(i==s.length()) return allStar(p,j);
        if(j==p.length()) return i==s.length();
        // not reach the end, then memorization
        if(visited[i][j]==1) return dp[i][j];
        visited[i][j] = 1;
        // not find in memorization
        // 当p的第j位是'*' ：
        //    可以递归match s的i位和p的j+1位 （'*'==''）
        //    也可以递归的match s的i+1位和p的j位（通配符'*'）
        if(p.charAt(j)=='*')
            dp[i][j] = helper(s,p,i+1,j,dp,visited) || helper(s,p,i,j+1,dp,visited);
        else
            dp[i][j] = matchChar(s.charAt(i),p.charAt(j)) && helper(s,p,i+1,j+1,dp,visited);
        return dp[i][j];
    }
    private boolean allStar(String p, int idx) {
        for(int i=idx;i<p.length();i++) {
            if(p.charAt(i)!='*') return false;
        }
        return true;
    }
    private boolean matchChar(Character a,Character b) {
        return a==b || b=='?';
    }

    /** 10. Regular Expression Matching */

    public boolean isMatchII(String s, String p) {
        int len1 = s.length(), len2 = p.length();
        boolean[][] dp = new boolean[len1][len2];
        int[][] visited = new int[len1][len2];
        return helperII(s,p,0,0,dp,visited);
    }
    private boolean helperII(String s, String p,int i,int j,boolean[][] dp,int[][] visited) {
        // if reach the end, check
        if(i==s.length()) return allStarII(p,j);
        if(j==p.length()) return i==s.length();
        // not reach the end, then memorization
        if(visited[i][j]==1) return dp[i][j];
        visited[i][j] = 1;
        // not find in memorization
        // 当p的第j+1位是'*' ：
        //    可以递归match s的i位和p的j+2位 （跳过*和*前一个字符）
        //    也可以递归的match s的i+1位和p的j位（regular expression '*'）
        if(j+1<p.length()&&p.charAt(j+1)=='*')
            dp[i][j] = (charMatch(s.charAt(i),p.charAt(j)) && helperII(s,p,i+1,j,dp,visited))
                    || helperII(s,p,i,j+2,dp,visited);
        else
            dp[i][j] = charMatch(s.charAt(i),p.charAt(j)) && helperII(s,p,i+1,j+1,dp,visited);
        return dp[i][j];
    }
    // 多余1个字符时不管是不是*都不match
    // 多余大于1个字符时每次跳过一组re（字符+'*'）
    private boolean allStarII(String p,int j) {
        for(int i=j;i<p.length();i+=2) {
            if(i+1==p.length() || p.charAt(i+1)!='*') return false;
        }
        return true;
    }
    private boolean charMatch(Character a,Character b) {
        return a==b || b=='.';
    }
}
