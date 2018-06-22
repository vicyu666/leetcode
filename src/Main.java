import tree.TreeNode;

public class Main {
    public static void main(String[] args) {
        DFS dfs = new DFS();

        // 339
//        List<NestedInteger> list = new ArrayList<>();
//        NestedInteger n1 = new NestedInteger(1);
//        NestedInteger n2 = new NestedInteger(2);
//
//        NestedInteger nested = new NestedInteger();
//        nested.add(n1);
//        nested.add(n1);
//        list.add(nested);
//        list.add(n2);
//        list.add(nested);
//
//        System.out.println(dfs.depthSum(list));

        // 366
//        String str = "[1,2,null]";
//        tree.TreeNode root = tree.TreeNode.mkTree(str);
//        System.out.println(dfs.findLeaves(root));

        // 531
//        char[][] picture = {{'W','W','B'},{'B','B','W'},{'W','B','W'}};
//        System.out.println(dfs.findLonelyPixel(picture));

        // 513
//        String str2 = "[1]";
//        tree.TreeNode root2 = tree.TreeNode.mkTree(str2);
//        System.out.println(dfs.findBottomLeftValue(root2));

        // 200
//        char[][] island = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
//        System.out.println(dfs.numIslands(island));

        // 494

//        System.out.println(dfs.findTargetSumWays(new int[]{1,1,1,1,1},3));

        // 113
//        String str3 = "[5,4,8,11,null,13,4,7,2,null,null,null,null,5,1]";
//        tree.TreeNode root3 = tree.TreeNode.mkTree(str3);
//        System.out.print( dfs.pathSum(root3,22));

        String str3 = "[1,null,2,null,null,null,3]";
        TreeNode root3 = TreeNode.mkTree(str3);
        System.out.print( dfs.isBalanced(root3));

    }
}
