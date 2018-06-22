package BinarySearch;

public class bs {
    /** 162. Find Peak Element */

    public int findPeakElement(int[] nums) {
        if(nums.length==0) return -1;
        int left = 0, right = nums.length-1;
        while(left+1<right) {
            int mid = left + (right-left)/2;
            if(nums[mid]>nums[mid+1]) right = mid;
            else left = mid;
        }
        if(nums[left]>nums[right]) return left;
        return right;
    }

    /** 852. Peak Index in a Mountain Array */

    public int peakIndexInMountainArray(int[] A) {
        if(A.length==0) return -1;
        int left=0, right=A.length-1;
        while(left+1<right) {
            int mid = left + (right-left)/2;
            if(A[mid]>A[mid+1]) right = mid;
            if(A[mid]<A[mid+1]) left = mid;
        }
        if(A[left]>A[right]) return left;
        else return right;
    }

    /** 69. Sqrt(x) */
    // 找mid平方不大于x的最大值， 乘方类问题均要考虑int溢出。使用long

    public int mySqrt(int x) {
        if(x<0) return 0;
        long start=0, end=x;
        while(start+1<end) {
            long mid = start+(end-start)/2;
            if(mid*mid==x) return (int)mid;
            else if(mid*mid>x) end=mid;
            else start=mid;
        }
        if(end*end<=x) return (int)end;
        else return (int)start;
    }

    /** 367. Valid Perfect Square */
    // 找mid平方恰好等于x的值， 乘方类问题均要考虑int溢出。使用long

    public boolean isPerfectSquare(int num) {
        if(num<1) return false;
        long start=0,end=num;
        while(start+1<end) {
            long mid=start+(end-start)/2;
            if(mid*mid==num) return true;
            else if(mid*mid<num) start=mid;
            else end=mid;
        }
        return start*start==num||end*end==num;
    }

    /** 441. Arranging Coins */
    // 找第一个从1加到i的和比n大的i

    public int arrangeCoins(int n) {
        long start=1,end=n;
        while(start+1<end) {
            long mid = start+(end-start)/2;
            if((1+mid)*mid/2>n) end=mid;
            else start=mid;
        }
        if((1+start)*start/2>n) return (int)start-1;
        return (int)start;
    }

}
