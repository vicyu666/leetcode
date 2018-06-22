package BinarySearch;

import java.util.Arrays;

public class heaters {

    public int findRadius(int[] houses, int[] heaters) {
        if(houses.length==0||heaters.length==0) return 0;
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int radius = 0;
        for(int house : houses) {
            int local = binarySearch(heaters, house);
            radius = Math.max(radius,local);
        }
        return radius;
    }
    // 找距离houses[i]最近的heater[j]的heater[j]-houses[i]值
    private int binarySearch(int[] heaters, int target) {
        int l = 0, r = heaters.length - 1;
        while(l + 1 < r) {
            int mid = l + (r - l) / 2;
            if(heaters[mid] == target) return 0;
            else if(heaters[mid] < target) l = mid;
            else r = mid;
        }
        return Math.min(Math.abs(target - heaters[l]), Math.abs(target - heaters[r]));
    }

}
