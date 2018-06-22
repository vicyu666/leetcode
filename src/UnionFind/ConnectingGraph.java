package UnionFind;

public class ConnectingGraph {
    private int[] father = null; // 上级关系
    private int[] size = null; //联通个数
    private int count; //总岛的个数
    private int find(int x) {
        if(father[x]  == x)
            return x;
        return father[x] = find(father[x]);
    }

    public ConnectingGraph(int n) {
        // initiate
        count = n;
        father = new int[n+1];
        for(int i=0;i<n+1;i++) {
            father[i] = i;
            size[i] = 1;
        }
    }

    public void connect(int a, int b) {
        int root_a = find(a);
        int root_b = find(b);
        if(root_a != root_b) {
            father[root_a] = root_b;
            size[root_a] += size[root_b];
            count--;
        }
    }

    public boolean query(int a, int b) {
        return find(a) == find(b);
    }
}
