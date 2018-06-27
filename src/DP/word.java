package DP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class word {

    /** 139. Word Breaks */
    // dp[i] 代表第i位之前可不可以拆分成dict里的词
    // 时间复杂度O(n^2)，空间复杂度O(n)
    // 这道题目其实没有说清楚的是，wordDict中的元素是否可以被重复使用。
    // 面试的时候一定要注意问清楚面试官的意思，一点一点clarify问题。
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] canBreak = new boolean[len + 1];
        canBreak[0] = true;
        for(int i=1; i<len + 1; i++) {
            for(int j=0; j<i; j++) {
                if(wordDict.contains(s.substring(j,i)) && canBreak[j]) {
                    canBreak[i] = true;
                    break;
                }
            }
        }
        return canBreak[len];
    }

    /** 140. Word Break II */
    public List<String> wordBreakII(String s, List<String> wordDict) {
        return helper(s,wordDict,new HashMap<>());
    }
    private List<String> helper(String s, List<String> wordDict, Map<String,List<String>> map) {
        if(map.containsKey(s)) {
            return map.get(s);
        }
        // current recursion level's answer
        List<String> res = new ArrayList<>();
        if(s.length() == 0) {
            return res;
        }
        if(wordDict.contains(s)) {
            res.add(s);
        }
        for(int i=1; i<s.length() + 1; i++) {
            String prefix = s.substring(0,i);
            // next level's answer using recursive call to get and save into a reference of list
            if(wordDict.contains(prefix)) {
                List<String> suffix = helper(s.substring(i), wordDict, map);
                for(String combination : suffix) {
                    res.add(prefix + " " + combination);
                }
            }
        }
        map.put(s, res);
        return res;
    }
}
