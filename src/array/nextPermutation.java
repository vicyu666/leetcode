package array;

public class nextPermutation {
    public void nextPermutation(int[] nums) {
        if(nums ==null) {
            return;
        }
        if(nums.length < 2) {
            return;
        }
        int len = nums.length;
        int firstSmall = len - 1;

        while(firstSmall > 0) {
            if(nums[firstSmall-1] < nums[firstSmall]) {
                break;
            }
            firstSmall--;
        }
        firstSmall--;

        if(firstSmall < 0) {
            reverse(nums, 0, len-1);
        } else {
            int firstLarge = 0;
            for(int i=len-1; i>firstSmall; i--) {
                if(nums[i] > nums[firstSmall]) {
                    firstLarge = i;
                    break;
                }
            }
            swap(nums, firstSmall, firstLarge);
            reverse(nums, firstSmall+1, len-1);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp;
        tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void reverse(int[] nums, int start, int end) {
        while(start < end) {
            swap(nums,start,end);
            start++;
            end--;
        }
    }
}
