package BinarySearch;
import tree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class closestVal {

    public int closestValue(TreeNode root, double target) {
        if(root==null) return 0;
        double prev = root.val;
        if(root.left!=null&&root.val>target) {
            double currLeft = closestValue(root.left,target);
            prev = Math.abs(prev-target)>Math.abs(currLeft-target)?currLeft:prev;
        }
        if(root.right!=null&&root.val<target) {
            double currRight = closestValue(root.right,target);
            prev = Math.abs(prev-target)>Math.abs(currRight-target)?currRight:prev;
        }
        return (int)prev;
    }

    /** 658. Find K Closest Elements */

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // 二分查找找到与x最接近的点
        List<Integer> res = new ArrayList<>();
        if(arr.length==0) return res;
        int left=0, right=arr.length-1;
        while(left+1<right) {
            int mid = left+(right-left)/2;
            if(arr[mid]<x) left=mid;
            else right=mid;
        }
        // 从最接近的点开始向前后添加元素到结果
        int count = 0;
        while (left >= 0 && right < arr.length && count < k) {
            if (Math.abs(x - arr[left]) <= Math.abs(x - arr[right])) {
                res.add(arr[left--]);
            } else {
                res.add(arr[right++]);
            }
            count++;
        }
        while (count < k && left >= 0) {
            res.add(arr[left--]);
            count++;
        }
        while (count < k && right < arr.length) {
            res.add(arr[right++]);
            count++;
        }
        Collections.sort(res);
        return res;
    }
}
