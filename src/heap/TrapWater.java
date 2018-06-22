package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TrapWater {

    /** 407. Trapping Rain Water II */
    // Given the following 3x6 height map:
    // [
    //  [1,4,3,1,3,2],
    //  [3,2,1,3,2,4],
    //  [2,3,3,2,3,1]
    // ]
    //
    // Return 4
    class Cell {
        public int x, y, h;
        Cell(){}
        Cell(int a, int b, int c) {
            x = a;
            y = b;
            h = c;
        }
    }

    int[] dx = {1,-1,0,0};
    int[] dy = {0,0,1,-1};

    public int trapRainWater(int[][] heightMap) {
        int ans = 0;
        if(heightMap.length==0 || heightMap[0].length==0)
            return ans;
        int row = heightMap.length;
        int col = heightMap[0].length;
        int[][] visited = new int[row][col];

        PriorityQueue<Cell> pq = new PriorityQueue<>(1,new Comparator<Cell>() {
            @Override
            public int compare(Cell a, Cell b) {
                if(a.h>b.h)
                    return 1;
                else if(a.h==b.h)
                    return 0;
                else return -1;
            }
        });
        for(int i=0;i<row;i++) {
            pq.offer(new Cell(i,0,heightMap[i][0]));
            pq.offer(new Cell(i,col-1,heightMap[i][col-1]));
            visited[i][0] = 1;
            visited[i][col-1] = 1;
        }
        for(int i=0;i<col;i++) {
            pq.offer(new Cell(0,i,heightMap[0][i]));
            pq.offer(new Cell(row-1,i,heightMap[row-1][i]));
            visited[0][i] = 1;
            visited[row-1][i] = 1;
        }
        while(!pq.isEmpty()) {
            Cell curr = pq.poll();
            for(int i=0;i<4;i++) {
                int nx = curr.x+dx[i];
                int ny = curr.y+dy[i];
                if(nx>=0 && nx<row && ny>=0 && ny<col && visited[nx][ny]==0) {
                    visited[nx][ny] = 1;
                    pq.offer(new Cell(nx,ny,Math.max(curr.h,heightMap[nx][ny])));
                    ans += Math.max(0,curr.h-heightMap[nx][ny]);
                }
            }
        }
        return ans;
    }

}
