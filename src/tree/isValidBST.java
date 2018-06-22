package tree;

public class isValidBST {

    public boolean isValidBST(TreeNode root) {
        return divConq(root,Long.MAX_VALUE,Long.MIN_VALUE);
    }
    private boolean divConq(TreeNode root,long max,long min) {
        if(root==null) return true;
        if(root.val<=min||root.val>=max) return false;
        boolean left = divConq(root.left,root.val,min);
        boolean right = divConq(root.right,max,root.val);
        return left&&right;
    }
}
