package twoPointers;

public class RemoveDup {

    /** 26. Remove Duplicates from Sorted Array */
    // in space
    // using fast and slow two pointers

    public int removeDuplicates(int[] nums) {
        if(nums.length==0) return 0;
        int index = 1;
        for(int i=1;i<nums.length;i++) {
            if(nums[i]!=nums[i-1]) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }
}
