package UnionFind;

import java.util.HashMap;
import java.util.Map;

public class SentenceSimilarity {
    class UF{
        Map<String,String> id = null;
        public UF(String[][] pairs) {
            id = new HashMap<>();
            for(int i=0;i<pairs.length;i++) {
                union(pairs[i][0],pairs[i][1]);
            }
        }
        private void union(String a, String b) {
            String root_a = find(a);
            String root_b = find(b);
            if(!root_a.equals(root_b)) id.put(root_a,root_b);
        }
        private String find(String s) {
            if(!id.containsKey(s)) id.put(s,s);
            return s.equals(id.get(s))?s:find(id.get(s));
        }
    }
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        UF uf = new UF(pairs);
        if(words1.length!=words2.length) return false;
        for(int i=0;i<words1.length;i++) {
            String s1 = uf.find(words1[i]);
            String s2 = uf.find(words2[i]);
            if(!s1.equals(s2) && !words1[i].equals(words2[i])) return false;
        }
        return true;
    }
}
