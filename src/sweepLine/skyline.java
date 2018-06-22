package sweepLine;

import java.util.*;

public class skyline {
    class point {
        int pos;
        int height;
        boolean isStart;
        public point(int p,int h,boolean b) {
            pos = p;
            height = h;
            isStart = b;
        }
    }

    class sortPoint implements Comparator<point> {
        @Override
        public int compare(point a,point b) {
            if(a.pos!=b.pos) return a.pos-b.pos;
            // 高的先放,一步到位
            else if(a.isStart&&b.isStart) return b.height-a.height;
            // 矮的先删，保留有效数据
            else if(!a.isStart&&!b.isStart) return a.height-b.height;
            // 高度相同时，优先开始新的点，再删除久的点，保证不会重复计入
            else if(a.isStart) return -1;
            else return 1;
        }
    }

    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        List<point> points = new ArrayList<>();
        for(int[] bd : buildings) {
            points.add(new point(bd[0],bd[2],true));
            points.add(new point(bd[1],bd[2],false));
        }
        Collections.sort(points,new sortPoint());

        PriorityQueue<Integer> pq;
        pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.offer(0);
        int prev_height = 0;
        for(point p : points) {
            if(p.isStart) pq.offer(p.height);
            else pq.remove(p.height);
            int curr_height = pq.peek();
            if(curr_height!=prev_height) {
                res.add(new int[] {p.pos,curr_height});
                prev_height = curr_height;
            }
        }
        return res;
    }
}
