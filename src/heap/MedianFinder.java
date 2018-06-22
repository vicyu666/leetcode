package heap;

import java.util.Collections;
import java.util.PriorityQueue;

class MedianFinder {

    /** 295. Find Median from Data Stream */
    // Example:
    //
    // addNum(1)
    // addNum(2)
    // findMedian() -> 1.5
    // addNum(3)
    // findMedian() -> 2

    PriorityQueue<Integer> maxheap, minheap;

    public MedianFinder() {
        minheap = new PriorityQueue<>();
        maxheap = new PriorityQueue<>(Collections.reverseOrder());
    }

    public void addNum(int num) {
        maxheap.offer(num);
        minheap.offer(maxheap.poll());
        if(maxheap.size()<minheap.size())
            maxheap.offer(minheap.poll());
    }

    public double findMedian() {
        if(maxheap.size()==minheap.size())
            return (double)(maxheap.peek()+minheap.peek())/2;
        return maxheap.peek();
    }

    /** 480. Sliding Window Median */
    // For example,
    // Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
    //
    // Window position                Median
    // ---------------               -----
    // [1  3  -1] -3  5  3  6  7       1
    //  1 [3  -1  -3] 5  3  6  7       -1
    //  1  3 [-1  -3  5] 3  6  7       -1
    //  1  3  -1 [-3  5  3] 6  7       3
    //  1  3  -1  -3 [5  3  6] 7       5
    //  1  3  -1  -3  5 [3  6  7]      6
    // Therefore, return the median sliding window as [1,-1,-1,3,5,6].

    PriorityQueue<Long> maxhp, minhp;

    public double[] medianSlidingWindow(int[] nums, int k) {
        maxhp = new PriorityQueue<>(Collections.reverseOrder());
        minhp = new PriorityQueue<>();
        double[] res = new double[nums.length-k+1];
        for(int i=0;i<k;i++) {
            add(nums[i]);
        }
        int ind = 0;
        res[ind] = getMed();
        for(int i=k;i<nums.length;i++) {
            removeNum(nums[i-k]);
            add(nums[i]);
            res[++ind] = getMed();
        }
        return res;
    }

    private void add(long n) {
        maxhp.offer(n);
        minhp.offer(maxhp.poll());
        if(maxhp.size()<minhp.size())
            maxhp.offer(minhp.poll());
    }
    private void removeNum(long n) {
        if(n<=getMed())
            maxhp.remove(n);
        else
            minhp.remove(n);
    }
    private double getMed() {
        if(maxhp.size()==minhp.size())
            return (maxhp.peek()+minhp.peek())/2.0;
        return maxhp.peek();
    }

    /** 239. Sliding Window Maximum */
    // same as previous question. Find maximum rather than median.
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length==0 || k>nums.length)
            return new int[0];
        PriorityQueue<Integer> maxheap;
        maxheap = new PriorityQueue<>(Collections.reverseOrder());
        int[] res = new int[nums.length-k+1];
        for(int i=0;i<k;i++) {
            maxheap.offer(nums[i]);
        }
        int ind = 0;
        res[ind] = maxheap.peek();
        for(int i=k;i<nums.length;i++) {
            maxheap.remove(nums[i-k]);
            maxheap.offer(nums[i]);
            res[++ind] = maxheap.peek();
        }
        return res;
    }
}