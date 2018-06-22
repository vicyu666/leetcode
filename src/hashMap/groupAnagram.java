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
}
