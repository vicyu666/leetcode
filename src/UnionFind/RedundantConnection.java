package UnionFind;

public class RedundantConnection {
    class UF{
        int[] father = null;
        public UF(int[][] edges,int m,int n){
            father = new int[m*n];
            for(int i=0;i<m*n;i++) {
                father[i] = i;
            }
        }
        private int find(int n) {
            if(n==father[n]) return n;
            return father[n] = find(father[n]);
        }
        private void union(int a, int b) {
            int root_a=find(a);
            int root_b=find(b);
            if(root_a!=root_b) father[root_a] = root_b;
        }
    }
    public int[] findRedundantConnection(int[][] edges) {
        if(edges.length==0||edges[0].length==0)
            return new int[0];
        int m=edges.length, n=edges[0].length;
        int[] res = new int[2];
        UF uf = new UF(edges,m,n);
        for(int i=0;i<m;i++) {
            if(uf.find(edges[i][0])==uf.find(edges[i][1])) {
                res[0] = edges[i][0];
                res[1] = edges[i][1];
            }
            uf.union(edges[i][0],edges[i][1]);
        }
        return res;
    }
}
