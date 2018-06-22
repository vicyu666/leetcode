package sweepLine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class meetingRoom {
    class point {
        int time;
        boolean isStart;
        public point(int t,boolean b){
            time = t;
            isStart = b;
        }
    }
    class sortPoint implements Comparator<point> {
        @Override
        public int compare(point a,point b) {
            if(a.time!=b.time) return a.time-b.time;
            else if(a.isStart!=b.isStart) {
                if(a.isStart) return 1;
                else return -1;
            }
            return 0;
        }
    }
    public int minMeetingRooms(Interval[] intervals) {
        if(intervals.length==0) return 0;
        int len = intervals.length;
        List<point> timePoints = new ArrayList<>();
        for(int i=0;i<len;i++) {
            timePoints.add(new point(intervals[i].start,true));
            timePoints.add(new point(intervals[i].end,false));
        }
        Collections.sort(timePoints,new sortPoint());
        int res = 0, cnt = 0;
        for(point p : timePoints) {
            if(p.isStart) cnt++;
            else cnt--;
            res = Math.max(res,cnt);
        }
        return res;
    }
}
