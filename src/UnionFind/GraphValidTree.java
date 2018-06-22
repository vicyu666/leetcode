package UnionFind;

public class GraphValidTree {
    class UF{
        int[] father = null;
        int cnt = 0;
        public UF(int n) {
            father = new int[n];
            for(int i=0;i<n;i++) {
                father[i] = i;
            }
            cnt = n;
        }
        private int find(int n) {
            if(father[n]==n)
                return n;
            return father[n]=find(father[n]);
        }
        private void union(int a,int b) {
            int root_a = find(a);
            int root_b = find(b);
            if(root_a!=root_b) {
                father[root_a]=root_b;
                cnt--;
            }
        }
    }
    public boolean validTree(int n, int[][] edges) {
        if(edges.length==0||edges[0].length==0) {
            if(n==1)return true;
            else return false;
        }
        UF uf = new UF(n);
        for(int i=0;i<edges.length;i++) {
            if(uf.find(edges[i][0])==uf.find(edges[i][1])) return false;
            uf.union(edges[i][0],edges[i][1]);
        }
        return uf.cnt==1;
    }
}
