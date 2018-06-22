package UnionFind;

import java.util.*;

public class SameEmail {
    private int[] father = null;
    private int find(int n) {
        if(father[n]==n)
            return n;
        return father[n] = find(n);
    }
    private void union(int a,int b) {
        int root_a = find(a);
        int root_b = find(b);
        if(root_a!=root_b)
            father[root_a] = root_b;
    }

    public List<List<Integer>> SameEmail (Map<Integer,Set<String>> emailList) {
        List<List<Integer>> res = new ArrayList<>();
        father = new int[emailList.size()+1];
        for(int i=0;i<father.length;i++) {
            father[i] = i;
        }
        HashMap<String,Integer> map = new HashMap<>();
        for(int i : emailList.keySet()) {
            for(String s : emailList.get(i)) {
                if(!map.containsKey(s)) {
                    map.put(s,i);
                } else {
                    union(map.get(s),i);
                }
            }
        }
        return res; // 通过father数组得到联通区域；
    }
}
