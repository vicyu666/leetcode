package hashMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class isIsomorphic {

    // check map.key and also check map.value
    public boolean isIsomorphic(String s, String t) {
        if(s.length()!=t.length()) return false;
        Map<Character,Character> map = new HashMap<>();
        boolean res = true;
        for(int i=0;i<s.length();i++) {
            if(map.containsKey(s.charAt(i))) res &= map.get(s.charAt(i))==t.charAt(i);
            else if(!map.containsValue(t.charAt(i))) map.put(s.charAt(i),t.charAt(i));
            else return false;
        }
        return res;
    }

    // check latest index
    public boolean isIsomorphic_encode(String s, String t) {
        int[] dict_s = new int[256];
        int[] dict_t = new int[256];
        Arrays.fill(dict_s,-1);
        Arrays.fill(dict_t,-1);
        for(int i=0;i<s.length();i++) {
            if(dict_s[s.charAt(i)]!=dict_t[t.charAt(i)]) return false;
            dict_s[s.charAt(i)] = i;
            dict_t[t.charAt(i)] = i;
        }
        return true;
    }
}
