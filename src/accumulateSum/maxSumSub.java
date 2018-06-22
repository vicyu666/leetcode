package accumulateSum;

public class maxSumSub {
    public double findMaxAverage(int[] nums, int k) {
        if(nums.length==0||k>nums.length) return 0;

        int[] ac = new int[nums.length];
        ac[0] = nums[0];
        for(int i=1;i<nums.length;i++) {
            ac[i] = ac[i-1]+nums[i];
        }

        double max = Integer.MIN_VALUE;
        for(int i=k-1;i<nums.length;i++) {
            if(i==k-1) max = Math.max(max,ac[i]);
            else max = Math.max(max,ac[i]-ac[i-k]);
        }
        return max/k;
    }
}
