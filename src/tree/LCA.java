package tree;

public class LCA {

    // BST
    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null || q == null || p == null) {
            return null;
        } else if(root.val>=p.val&&root.val<=q.val||root.val<=p.val&&root.val>=q.val) {
            return root;
        } else if(root.val>p.val&&root.val>q.val) {
            return lowestCommonAncestorBST(root.left,p,q);
        } else {
            return lowestCommonAncestorBST(root.right,p,q);
        }
    }

    // general Binary Tree
    // this approach is for one time query, for multi-time query can use tarjan's algorithm

    // O(n) bottom up traversal
    // If one key is present and other is absent, then it returns the present key as LCA
    // We can extend this method by passing two boolean variables v1 and v2.
    // v1 is set as true when n1 is present in tree and v2 is set as true if n2 is present in tree.

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return null;
        } else if(root == q || root == p) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if(left != null && right != null) {
            return root;
        } else if(left != null) {
            return left;
        } else if(right != null) {
            return right;
        } else {
            return null;
        }
    }

}
