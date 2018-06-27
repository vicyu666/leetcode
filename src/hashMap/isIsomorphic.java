package hashMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class isIsomorphic {

    /** 205. Isomorphic Strings */
    // check map.key and also check map.value
    public boolean isIsomorphic(String s, String t) {
        if(s == null || t == null) {
            return false;
        }
        if(s.length()!=t.length()) {
            return false;
        }
        
        boolean res = true;
        Map<Character,Character> map = new HashMap<>();
        for(int i=0; i<s.length(); i++) {
            char letter = s.charAt(i);
            if(map.containsKey(letter)) {
                res &= map.get(letter)==t.charAt(i);
            } else if(!map.containsValue(t.charAt(i))) {
                map.put(letter,t.charAt(i));
            } else {
                return false;
            }
        }
        return res;
    }

    /** 290. Word Pattern */
    // check map.key and also check map.value
    public boolean wordPattern(String pattern, String str) {
        if(pattern == null || str == null) {
            return false;
        }
        // if multiple symbols use regex
        String[] words = str.split(" ");
        if(pattern.length() != words.length) {
            return false;
        }

        boolean res = true;
        Map<Character, String> map = new HashMap<>();
        for(int i=0; i<words.length; i++) {
            char letter = pattern.charAt(i);
            if(map.containsKey(letter)) {
                res &= map.get(letter).equals(words[i]);
            } else if(!map.containsValue(words[i])) {
                map.put(letter, words[i]);
            } else {
                return false;
            }
        }
        return res;
    }
}
