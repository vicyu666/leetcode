package stack;

import tree.TreeNode;
import java.util.Deque;
import java.util.LinkedList;

public class maxTree {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if(nums.length==0) return null;
        Deque<TreeNode> node = new LinkedList<>();
        for(int i=0;i<nums.length;i++) {
            TreeNode curr = new TreeNode(nums[i]);
            while(!node.isEmpty() && nums[i]>=node.peek().val) {
                TreeNode tmp = node.pop();
                curr.left = tmp;
            }
            if(!node.isEmpty()) node.peek().right = curr;
            node.push(curr);
        }
        return node.pollLast();
    }

}
