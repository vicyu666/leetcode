package DP;

public class wildcardMatch {

    /** 44. Wildcard Matching */
    // String s = "" is not equal to s = null
    
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
}
