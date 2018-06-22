package sweepLine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class numAirplane {

    class Pair {
        int time;
        boolean flag;

        public Pair(int t, boolean f) {
            time = t;
            flag = f;
        }
    }

    class sortPair implements Comparator<Pair> {
        @Override
        public int compare(Pair a, Pair b) {
            if(a.time>b.time) return 1;
            if(a.time==b.time) return a.flag==true?1:-1;
            return -1;
        }
    }

    public int countOfAirplanes(List<Interval> airplanes) {
        // write your code here
        List<Pair> timePoints = new ArrayList<>();
        for(Interval i : airplanes) {
            timePoints.add(new Pair(i.start,true));
            timePoints.add(new Pair(i.end,false));
        }
        Collections.sort(timePoints,new sortPair());

        int max_num = 0, count = 0;
        for(Pair p : timePoints) {
            if(p.flag) count++;
            else count--;
            max_num = Math.max(max_num,count);
        }
        return max_num;
    }
}
