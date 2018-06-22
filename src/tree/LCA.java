package tree;

public class LCA {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) return null;

        if(root.val>=p.val&&root.val<=q.val||root.val<=p.val&&root.val>=q.val)
            return root;
        else if(root.val>p.val&&root.val>q.val)
            return lowestCommonAncestor(root.left,p,q);
        else return lowestCommonAncestor(root.right,p,q);
    }
}
