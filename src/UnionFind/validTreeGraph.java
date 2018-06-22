package UnionFind;

public class validTreeGraph {
    private int[] father = null;
    private int find(int n) {
        if(father[n] == n)
            return n;
        return father[n] = find(father[n]);
    }
    private void union(int a, int b) {
        int root_a = find(a);
        int root_b = find(b);
        if(root_a!=root_b) {
            father[root_a] = root_b;
        }
    }
    public boolean validTree(int n, int[][] edges) {
        if(edges.length<n-1) return false;
        father = new int[n+1];
        for(int i=0;i<n+1;i++) {
            father[i] = i;
        }
        for(int i=0;i<edges.length;i++) {
            if(find(edges[i][0])==find(edges[i][1]))
                return false;
            union(edges[i][0],edges[i][1]);
        }
        return true;
    }
}
