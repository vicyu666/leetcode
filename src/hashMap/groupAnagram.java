package hashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class groupAnagram {

    /** 49. Group Anagrams */

    public List<List<String>> groupAnagrams(String[] strs) {
        if(strs.length==0) return new ArrayList<>();
        Map<String,List<String>> map = new HashMap<>();
        for(String s : strs) {
            //   n: total number of words, m:longest length of word
            // also can use arrays.sort() to get the key but it takes O(n*m*log(m))
            // encode improve to O(n*m)
            int[] dict = new int[26];
            String encode = "";
            for(char c : s.toCharArray()) {
                dict[c-'a']++;
            }
            // using an array size of 26 to track numbers of char that occurs.
            for(int i : dict) {
                encode += (i + "/");
            }
            if(!map.containsKey(encode))
                map.put(encode,new ArrayList<String>());
            map.get(encode).add(s);
        }
        return new ArrayList<List<String>>(map.values());
    }

    /** 438. Find All Anagrams in a String */
    // O(n*m) not very efficiency
    // using sliding window approach to improve the time complexity to O(n)

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) return res;
        int sLen = s.length(), pLen = p.length();
        String standard = encode(p);
        for(int i=0;i+pLen-1<sLen;i++) {
            String curr = encode(s.substring(i,i+pLen));
            if(curr.equals(standard)) res.add(i);
        }
        return res;
    }
    private String encode(String p) {
        int[] dict = new int[26];
        for(int i=0;i<p.length();i++) {
            dict[p.charAt(i)-'a']++;
        }
        String res = "";
        for(int i : dict) {
            res += (i+"/");
        }
        return res;
    }

    /** 760. Find Anagram Mappings */
    // 只是参考循环插入map的写法
    public int[] anagramMappings(int[] A, int[] B) {
        if(A==null || A.length==0 || B==null || B.length==0) return new int[0];
        Map<Integer,List<Integer>> map = new HashMap<>();
        int len = B.length;
        int[] res = new int[len];

        for(int i=0;i<len;i++) {
            if(!map.containsKey(B[i]))
                map.put(B[i],new ArrayList<>());
            map.get(B[i]).add(i);
        }

        for(int i=0;i<len;i++) {
            res[i] = map.get(A[i]).get(0);
        }
        return res;
    }
}
