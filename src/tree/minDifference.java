package tree;

public class minDifference {

    /** 783. Minimum Distance Between BST Nodes */

    // 递归调用时需要使用对象类型（Object（Integer等），数组，容器……）传引用。
    // 递归调用原函数
    int min = Integer.MAX_VALUE, prev = -1;

    public int minDiffInBST(TreeNode root) {
        if(root==null) return min;
        minDiffInBST(root.left);
        if(prev!=-1) min = Math.min(min,root.val-prev);
        prev = root.val;
        minDiffInBST(root.right);
        return min;
    }
}
