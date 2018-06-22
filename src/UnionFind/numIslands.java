package UnionFind;

public class numIslands {
    class UF{
        int count = 0;
        int[] father = null;

        public UF(char[][] grid) {
            int m = grid.length, n = grid[0].length;
            for(int i=0;i<m;i++) {
                for(int j=0;j<n;j++) {
                    if(grid[i][j]=='1') count++;
                }
            }
            father = new int[m*n];
            for(int i=0;i<m*n;i++) {
                father[i] = i;
            }
        }
        private int find(int n) {
            if(father[n]==n) return n;
            return father[n] = find(father[n]);
        }
        private void union(int a,int b) {
            int root_a = find(a);
            int root_b = find(b);
            if(root_a!=root_b) {
                father[root_a] = root_b;
                count--;
            }
        }
    }
    public int numIslands(char[][] grid) {
        if(grid.length==0 || grid[0].length==0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[][] dr = {{1,0},{-1,0},{0,1},{0,-1}};

        UF uf = new UF(grid);

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j]=='0') continue;

                for(int[] d : dr) {
                    int x = i+d[0];
                    int y = j+d[1];

                    if(x>=0 && x<m && y>=0 && y<n && grid[x][y]=='1') {
                        uf.union(i*n+j,x*n+y);
                    }
                }
            }
        }
        return uf.count;
    }
}
