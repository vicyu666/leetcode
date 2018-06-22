package BinarySearch;

public class QuickSort {

    /** 215. Kth Largest Element in an RotateArray */
    // Example 2:
    //
    //Input: [3,2,3,1,2,4,5,5,6] and k = 4
    //Output: 4
    public int findKthLargest(int[] nums, int k) {
        if(nums.length<=0 || k>nums.length)
            return 0;
        int start = 0, end = nums.length-1;
        while(true) {
            int pos = partition(nums,start,end);
            if(pos==k-1) {
                return nums[pos];
            } else if(pos>k-1) {
                end = pos-1;
            } else
                start = pos+1;
        }
    }
    /** quick sort is to maintain two parts of array,
     * from start to i is smaller than pivot and i to j is greater
     * [6,3,5,7,9,8,7]
     * l    i   j   r*/
    private int partition(int[] nums, int l, int r) {
        int pivot = nums[l];
        int i = l;
        for(int j=l+1; j<=r; j++) {
            if(nums[j]>=pivot) {
                i += 1;
                swap(nums,i,j);
            }
        }
        swap(nums,l,i);
        return i;
    }
    private void swap(int[] nums,int i,int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
