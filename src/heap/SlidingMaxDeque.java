package heap;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingMaxDeque {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length==0 || k>nums.length)
            return new int[0];
        int[] res = new int[nums.length-k+1];
        Deque<Integer> dq = new ArrayDeque<>();

        for(int i=0;i<k-1;i++) {
            while(!dq.isEmpty() && nums[dq.peekLast()]<nums[i])
                dq.pollLast();
            dq.offerLast(i);
        }
        for(int i=k-1;i<nums.length;i++) {
            if(!dq.isEmpty() && dq.peekFirst()==i-k)
                dq.pollFirst();
            while(!dq.isEmpty() && nums[dq.peekLast()]<nums[i]) {
                dq.pollLast();
            }
            dq.offerLast(i);
            res[i-k+1] = nums[dq.peekFirst()];
        }
        return res;
    }
}
