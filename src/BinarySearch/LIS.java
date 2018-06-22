package BinarySearch;

import java.util.ArrayList;
import java.util.List;

public class LIS {

    /** 300. Longest Increasing Subsequence */
    // maintain a monotonically increasing list
    // use binary search to obtain the replace position

    public int lengthOfLIS(int[] nums) {
        List<Integer> cnt = new ArrayList<>();
        if(nums.length==0) return 0;
        cnt.add(nums[0]);
        for(int n : nums) {
            if(n<=cnt.get(0)) cnt.set(0,n);
            else if(n>cnt.get(cnt.size()-1)) cnt.add(n);
            else {
                int left = 0, right = cnt.size()-1;
                while(left+1<right) {
                    int mid = left+(right-left)/2;
                    if(cnt.get(mid)<n) left=mid;
                    else right=mid;
                }
                if(cnt.get(left)>n) cnt.set(left,n);
                else cnt.set(right,n);
            }
        }
        return cnt.size();
    }
}
