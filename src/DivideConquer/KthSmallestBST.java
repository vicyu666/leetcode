package DivideConquer;

import tree.TreeNode;

public class KthSmallestBST {
    public int kthSmallest(TreeNode root, int k) {
        int cnt = count(root.left);
        if(cnt+1<k) return kthSmallest(root.right,k-cnt-1);
        else if(cnt>=k) return kthSmallest(root.left,k);
        return root.val;
    }
    private int count(TreeNode root) {
        if(root==null) return 0;
        return 1+count(root.left)+count(root.right);
    }
}
