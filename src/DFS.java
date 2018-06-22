import tree.TreeNode;
import java.util.*;

public class DFS {

    /** 339. Nested List Weight Sum */
    // Example:
    // Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)

    public int depthSum(List<NestedInteger> nestedList) {
        int res = 0;
        for(NestedInteger ni : nestedList) {
            res += depthSum_dfs(ni,1);
        }
        return res;
    }

    private int depthSum_dfs(NestedInteger ni, int depth) {
        int res = 0;
        if(ni.isInteger())
            return ni.getInteger()* depth;
        for(NestedInteger n : ni.getList()) {
            res += depthSum_dfs(n,depth+1);
        }
        return res;
    }

    /** 690. Employee Importance */
    // Example 1:
    // Input: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
    // Output: 11
    // Explanation:
    // Employee 1 has importance value 5, and he has two direct subordinates: employee 2 and employee 3.
    // They both have importance value 3. So the total importance value of employee 1 is 5 + 3 + 3 = 11.

    // Employee info

    class Employee {
        // It's the unique id of each node;
        // unique id of this employee
        public int id;
        // the importance value of this employee
        public int importance;
        // the id of direct subordinates
        public List<Integer> subordinates;
    }

    public int getImportance(List<Employee> employees, int id) {
        int res = 0;
        if(employees.isEmpty())
            return res;
        HashSet<Integer> visited = new HashSet<>();
        HashMap<Integer, Employee> correspond = new HashMap<>();
        for(Employee e : employees) {
            correspond.put(e.id,e);
        }
        return getImportance_dfs(correspond, visited, id);
    }

    private int getImportance_dfs(HashMap<Integer, Employee> correspond, HashSet<Integer> visited, int id) {
        if(visited.contains(id))
            return 0;
        visited.add(id);
        int res = correspond.get(id).importance;
        for(int i : correspond.get(id).subordinates) {
            res += getImportance_dfs(correspond, visited, i);
        }
        return res;
    }

    /** 366. Find Leaves of Binary Tree */
    // Example:
    // Given binary tree
    //          1
    //         / \
    //        2   3
    //       / \
    //      4   5
    // Returns [4, 5, 3], [2], [1].

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        while(root!=null) {
            List<Integer> leaves = new ArrayList<>();
            root = findLeaves_dfs(root, leaves);
            res.add(leaves);
        }
        return res;
    }

    private TreeNode findLeaves_dfs(TreeNode node, List<Integer> leaves) {
        if(node==null)
            return null;
        if(node.left==null && node.right==null) {
            leaves.add(node.val);
            return null;
        }
        node.left = findLeaves_dfs(node.left,leaves);
        node.right = findLeaves_dfs(node.right,leaves);
        return node;
    }

    /** 257. Binary Tree Paths */
    // Example:
    //
    // Input:
    //    1
    //  /   \
    // 2     3
    //  \
    //   5
    // Output: ["1->2->5", "1->3"]

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if(root==null)
            return res;
        binaryTreePaths_dfs(root,"",res);
        return res;
    }

    private void binaryTreePaths_dfs(TreeNode node, String path, List<String> res) {
        if(node==null)
            return;
        if(node.left==null && node.right==null) {
            path += "->"+node.val;
            res.add(path.substring(2));
            return;
        }
        binaryTreePaths_dfs(node.left,path+"->"+node.val,res);
        binaryTreePaths_dfs(node.right,path+"->"+node.val,res);
    }

    /** 129. Sum Root to Leaf Numbers */
    // Input: [4,9,0,5,1]
    //     4
    //    / \
    //   9   0
    //  / \
    // 5   1
    // Output: 1026
    // Explanation:
    // The root-to-leaf path 4->9->5 represents the number 495.
    // The root-to-leaf path 4->9->1 represents the number 491.
    // The root-to-leaf path 4->0 represents the number 40.
    // Therefore, sum = 495 + 491 + 40 = 1026.

    // Solution 1:
    public int sumNumbers(TreeNode root) {
        int res = 0;
        List<String> path = new ArrayList<>();
        sumNumbers_dfs(root,path,"");
        for(String s : path) {
            res += Integer.parseInt(s);
        }
        return res;
    }
    private void sumNumbers_dfs(TreeNode node, List<String> path,String s) {
        if(node==null)
            return;
        if(node.left==null && node.right==null) {
            s += node.val;
            path.add(s);
        }
        s += node.val;
        sumNumbers_dfs(node.left,path,s);
        sumNumbers_dfs(node.right,path,s);
    }

    // Solution 2:
    public int sumNumbers2(TreeNode root) {
        return sumNumbers2_dfs(root,0);
    }
    private int sumNumbers2_dfs(TreeNode node,int sum) {
        if(node==null)
            return 0;
        sum = sum*10 + node.val;
        if(node.left==null && node.right==null)
            return sum;
        return sumNumbers2_dfs(node.left,sum) + sumNumbers2_dfs(node.right,sum);
    }

    /** 113. Path Sum II */
    // Given the below binary tree and sum = 22,
    //       5
    //      / \
    //     4   8
    //    /   / \
    //   11  13  4
    //  /  \    / \
    // 7    2  5   1
    // Return: [[5,4,11,2],[5,8,4,5]]

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if(root==null)
            return res;
        pathSum_dfs(root,res,sum,new ArrayList<>());
        return res;
    }

    private void pathSum_dfs(TreeNode node, List<List<Integer>> res, int sum, List<Integer> path) {
        if(node==null)
            return;
        path.add(node.val);
        if(node.val==sum && node.left==null && node.right==null) {
            res.add(new ArrayList<>(path));
        }
        pathSum_dfs(node.left,res,sum-node.val,path);
        pathSum_dfs(node.right,res,sum-node.val,path);
        path.remove(path.size()-1);
    }

    /** 112. Path Sum */
    // Exist or not

    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null)
            return false;
        if(sum==root.val && root.right==null && root.left==null)
            return true;
        return hasPathSum(root.left,sum-root.val) || hasPathSum(root.right,sum-root.val);
    }

    /** 437. Path Sum III */
    // root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
    //       10
    //      /  \
    //     5   -3
    //    / \    \
    //   3   2   11
    //  / \   \
    // 3  -2   1
    //
    // Return 3. The paths that sum to 8 are:
    //
    // 1.  5 -> 3
    // 2.  5 -> 2 -> 1
    // 3. -3 -> 11

    public int pathSum_3(TreeNode root, int sum) {
        if(root==null) return 0;
        return pathSum3_dfs(root,sum)+pathSum_3(root.left,sum)+pathSum_3(root.right,sum);

    }
    private int pathSum3_dfs(TreeNode node, int sum) {
        int res = 0;
        if(node==null)
            return res;
        if(node.val==sum) {
            res++;
        }
        res += pathSum3_dfs(node.left,sum-node.val);
        res += pathSum3_dfs(node.right,sum-node.val);
        return res;
    }

    /** 513. Find Bottom Left Tree Value */
    // Example:
    //
    // Input:
    //
    //        1
    //       / \
    //      2   3
    //     /   / \
    //    4   5   6
    //       /
    //      7
    //
    // Output: 7

    /**
     * 递归调用时：简而言之，基础数据类型（int，char，……）传值，
     * 对象类型（Object，数组，容器……）传引用。
     * 但是对于String, Integer, Double等等immutable的类型，也是传值，需要特殊处理。
     */

    public int findBottomLeftValue(TreeNode root) {
        if(root==null) return -1;
        int[] res = new int[] {0,0};
        findBottomLeftValue_dfs(root, 1, res);
        return res[0];
    }

    private void findBottomLeftValue_dfs(TreeNode node, int depth, int[] res) {
        if(res[1]<depth) {
            res[1] = depth;
            res[0] = node.val;
        }
        if(node.left!=null) findBottomLeftValue_dfs(node.left,depth+1,res);
        if(node.right!=null) findBottomLeftValue_dfs(node.right,depth+1,res);
    }

    /** 104. Maximum Depth of Binary Tree */
    // Example:
    //
    // Given binary tree [3,9,20,null,null,15,7],
    //
    //    3
    //   / \
    //  9  20
    //    /  \
    //   15   7
    // return its depth = 3

    public int maxDepth(TreeNode root) {
        if(root==null) return 0;
        int[] res = new int[] {0};
        maxDepth_dfs(root,1,res);
        return res[0];
    }
    private void maxDepth_dfs(TreeNode node, int depth, int[] res) {
        res[0] = Math.max(depth,res[0]);
        if(node.left!=null) maxDepth_dfs(node.left,depth+1,res);
        if(node.right!=null) maxDepth_dfs(node.right,depth+1,res);
    }

    /** 111. Minimum Depth of Binary Tree */
    // Given binary tree [3,9,20,null,null,15,7],
    //
    //     3
    //    / \
    //   9  20
    //     /  \
    //    15   7
    // return its minimum depth = 2.

    public int minDepth(TreeNode root) {
        if(root==null)
            return 0;
        int[] res = new int[]{Integer.MAX_VALUE};
        minDepth_dfs(root,res,1);
        return res[0];
    }

    private void minDepth_dfs(TreeNode node, int[] res, int depth) {
        if(node==null)
            return;
        if(node.left==null && node.right==null){
            res[0] = Math.min(depth,res[0]);
        }
        minDepth_dfs(node.left,res,depth+1);
        minDepth_dfs(node.right,res,depth+1);
    }

    /** 515. Find Largest Value in Each Tree Row */
    // Example:
    // Input:
    //
    //          1
    //         / \
    //        3   2
    //       / \   \
    //      5   3   9
    //
    // Output: [1, 3, 9]

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root==null) return res;
        largestValues_dfs(root,1,res);
        return res;
    }

    private void largestValues_dfs(TreeNode node, int depth, List<Integer> res) {
        if(res.size()<depth) //come to a new level
            res.add(node.val);
        else
            res.set(depth-1,Math.max(node.val,res.get(depth-1)));
        if(node.left!=null) largestValues_dfs(node.left, depth+1, res);
        if(node.right!=null) largestValues_dfs(node.right, depth+1, res);
    }

    /** 199. Binary Tree Right Side View */
    // Input: [1,2,3,null,5,null,4]
    // Output: [1, 3, 4]
    // Explanation:
    //
    //    1            <---
    //  /   \
    // 2     3         <---
    //  \     \
    //   5     4       <---

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root==null)
            return res;
        rightSideView_dfs(root,res,1);
        return res;
    }

    private void rightSideView_dfs(TreeNode node, List<Integer> res, int depth) {
        if(node==null)
            return;
        if(res.size()<depth) {
            res.add(node.val);
        }
        res.set(depth-1,node.val);
        rightSideView_dfs(node.left,res,depth+1);
        rightSideView_dfs(node.right,res,depth+1);
    }

    /** 100. Same Tree */
    // Example 2:
    //
    // Input:   1        1
    //         /          \
    //        2            2
    //
    //      [1,2], [1,null,2]
    // Output: false

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null)
            return true;
        if(p==null || q==null)
            return false;
        boolean res = p.val==q.val;
        res &= isSameTree(p.left,q.left);
        res &= isSameTree(p.right,q.right);
        return res;
    }

    /** 101. Symmetric Tree */
    // For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
    //
    //     1
    //    / \
    //   2   2
    //  / \ / \
    // 3  4 4  3

    public boolean isSymmetric(TreeNode root) {
        if(root==null)
            return true;
        return isSymmetric_dfs(root.left,root.right);
    }

    private boolean isSymmetric_dfs(TreeNode n1, TreeNode n2) {
        if(n1==null && n2==null)
            return true;
        if(n1==null && n2!=null || n2==null && n1!=null)
            return false;
        boolean equal = n1.val==n2.val;
        return equal&&isSymmetric_dfs(n1.left,n2.right)&&isSymmetric_dfs(n1.right,n2.left);
    }

    /** 114. Flatten Binary Tree to Linked List */
    //     1
    //    / \
    //   2   5
    //  / \   \
    // 3   4   6
    // The flattened tree should look like:
    // 1
    //  \
    //   2
    //    \
    //     3
    //      \
    //       4
    //        \
    //         5
    //          \
    //           6
    /** 这道题是一道反向递归的题，需要从最右叶子节点开始操作，需要特别注意。*/

    TreeNode prev = null;
    public void flatten(TreeNode root) {
        flatten_dfs(root);
    }
    private void flatten_dfs(TreeNode node) {
        if(node==null)
            return;
        flatten_dfs(node.right);
        flatten_dfs(node.left);
        node.right = prev;
        node.left = null;
        prev = node;
    }

    /** 695. Max Area of Island */
    // Example 1:
    // [[0,0,1,0,0,0,0,1,0,0,0,0,0],
    //  [0,0,0,0,0,0,0,1,1,1,0,0,0],
    //  [0,1,1,0,1,0,0,0,0,0,0,0,0],
    //  [0,1,0,0,1,1,0,0,1,0,1,0,0],
    //  [0,1,0,0,1,1,0,0,1,1,1,0,0],
    //  [0,0,0,0,0,0,0,0,0,0,1,0,0],
    //  [0,0,0,0,0,0,0,1,1,1,0,0,0],
    //  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
    // Given the above grid, return 6.

    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        if(grid.length<1 || grid[0].length<1)
            return res;

        for(int row=0;row<grid.length;row++) {
            for(int col=0;col<grid[row].length;col++) {
                if(grid[row][col]==1){
                    int size = maxAreaOfIsland_dfs(grid, row, col);
                    res = Math.max(size, res);
                }
            }
        }
        return res;
    }

    private int maxAreaOfIsland_dfs(int[][] grid, int row, int col) {
        if(row<0 || col<0 || row>grid.length-1 || col>grid[row].length-1) {
            return 0;
        }
        if(grid[row][col]==0) {
            return 0;
        }
        grid[row][col] = 0;

        int size = 1;
        // If diagonally included, then use nested for loop.
        for(int i = row-1; i<=row+1; i++) {
            if(i!=row) {
                size += maxAreaOfIsland_dfs(grid,i,col);
            }
        }
        for(int j = col-1; j<=col+1; j++) {
            if(j!=col) {
                size += maxAreaOfIsland_dfs(grid,row,j);
            }
        }
        return size;
    }

    /** 200. Number of Islands */
    // Example 2:
    //
    // Input:
    // 11000
    // 11000
    // 00100
    // 00011
    //
    // Output: 3

    public int numIslands(char[][] grid) {
        int res = 0;

        if(grid.length<1 || grid[0].length<1)
            return res;
        int row = grid.length, col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        for(int i=0;i<row;i++) {
            Arrays.fill(visited[i],false);
        }
        for(int i=0;i<row;i++) {
            for(int j=0;j<col;j++) {
                if(grid[i][j]=='1' && !visited[i][j]) {
                    numIslands_dfs(grid,visited,i,j);
                    res++;
                }
            }
        }
        return res;
    }

    private void numIslands_dfs(char[][] grid, boolean[][] visited, int row, int col) {
        if(row<0 || row>grid.length-1 || col<0 || col>grid[0].length-1)
            return;
        if(grid[row][col]!='1' || visited[row][col])
            return;
        visited[row][col] = true;
        numIslands_dfs(grid,visited,row-1,col);
        numIslands_dfs(grid,visited,row+1,col);
        numIslands_dfs(grid,visited,row,col-1);
        numIslands_dfs(grid,visited,row,col+1);
    }

    /** 733. Flood Fill */
    // Example:
    //
    // Input:  [[1,1,1],
    //          [1,1,0],
    //          [1,0,1]]
    // sr = 1, sc = 1, newColor = 2
    // Output: [[2,2,2],
    //          [2,2,0],
    //          [2,0,1]]

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image.length<1 || image[0].length<1 || image[sr][sc]==newColor)
            return image;
        floodFill_dfs(image,sr,sc,newColor,image[sr][sc]);
        return image;
    }

    private void  floodFill_dfs(int[][] image,int sr, int sc, int newColor, int oldColor) {
        if(sr<0 || sr>image.length-1 || sc<0 || sc>image[sr].length-1)
            return;
        if(oldColor!=image[sr][sc])
            return;
        image[sr][sc] = newColor;
        floodFill_dfs(image,sr-1,sc,newColor,oldColor);
        floodFill_dfs(image,sr+1,sc,newColor,oldColor);
        floodFill_dfs(image,sr,sc-1,newColor,oldColor);
        floodFill_dfs(image,sr,sc+1,newColor,oldColor);
    }

    /** 531. Lonely Pixel I */
    // Example:
    //
    // Input:
    // [['W', 'W', 'B'],
    //  ['W', 'B', 'W'],
    //  ['B', 'W', 'W']]
    // Output: 3

    public int findLonelyPixel(char[][] picture) {
        int res = 0;
        if (picture.length == 0 || picture[0].length == 0) return res;

        int length = picture[0].length, height = picture.length;
        int[] col = new int[height], row = new int[length];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                if (picture[i][j] == 'B') {
                    col[i]++;
                    row[j]++;
                }
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                if(picture[i][j]=='B' && col[i]==1 && row[j]==1)
                    res++;
            }
        }
        return res;
    }

    /** 337. House Robber III */
    // Example 2:
    //     3
    //    / \
    //   4   5
    //  / \   \
    // 1   3   1
    // Maximum amount of money the thief can rob = 4 + 5 = 9.

    public int rob(TreeNode root) {
        HashMap<TreeNode,Integer> visited = new HashMap<>();
        return rob_dfs(root, visited);
    }
    private int rob_dfs(TreeNode node, HashMap<TreeNode,Integer> visited) {
        if(node==null)
            return 0;
        if(visited.containsKey(node))
            return visited.get(node);
        int val = 0;
        if(node.left!=null) {
            val += rob_dfs(node.left.left,visited) + rob_dfs(node.left.right,visited);
        }
        if(node.right!=null) {
            val += rob_dfs(node.right.left,visited) + rob_dfs(node.right.right,visited);
        }
        val = Math.max(val+node.val,rob_dfs(node.left,visited)+rob_dfs(node.right,visited));
        visited.put(node,val);
        return val;
    }

    /** 494. Target Sum */
    // Input: nums is [1, 1, 1, 1, 1], S is 3.
    // Output: 5
    // Explanation:
    //
    // -1+1+1+1+1 = 3
    // +1-1+1+1+1 = 3
    // +1+1-1+1+1 = 3
    // +1+1+1-1+1 = 3
    // +1+1+1+1-1 = 3


    public int findTargetSumWays(int[] nums, int S) {
        int[] res = new int[]{0};
        findTargetSumWays_dfs(nums,S,0,res);
        return res[0];
    }

    private void findTargetSumWays_dfs(int[] nums, int S, int index, int[] res) {
        if(index==nums.length) {
            if(S==0) res[0]++;
            return;
        }
        findTargetSumWays_dfs(nums,S-nums[index],index+1,res);
        findTargetSumWays_dfs(nums,S+nums[index],index+1,res);
    }

    /** 116. Populating Next Right Pointers in Each Node */
    //      1 -> NULL
    //    /  \
    //   2 -> 3 -> NULL
    //  / \  / \
    // 4->5->6->7 -> NULL

    public class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }

    public void connect_perfect(TreeLinkNode root) {
        if(root==null)
            return;
        if(root.left!=null)
            root.left.next = root.right;
        if(root.right!=null)
            root.right.next = root.next==null?null:root.next.left;
        connect_perfect(root.left);
        connect_perfect(root.right);
    }

    /** 117. Populating Next Right Pointers in Each Node II */
    //      1 -> NULL
    //    /  \
    //   2 -> 3 -> NULL
    //  / \    \
    // 4-> 5 -> 7 -> NULL

    public void connect(TreeLinkNode root) {
        if(root==null)
            return;
        TreeLinkNode cur = root.next, nxt = null;
        while(cur!=null) {
            if(cur.left!=null) {
                nxt = cur.left;
                break;
            }
            else if(cur.right!=null) {
                nxt = cur.right;
                break;
            }
            else cur = cur.next; // when next parent node does not have child;
        }
        if(root.left!=null)
            root.left.next = root.right==null?nxt:root.right;
        if(root.right!=null)
            root.right.next = nxt;
        /** 如果这里先递归左子节点
        /* 则右侧某些父节点还没有link上
        /* 会使结果出错 */
        connect(root.right);
        connect(root.left);
    }

    /** 110. Balanced Binary Tree */
    // Given the following tree [1,2,2,3,3,null,null,4,4]:
    //
    //        1
    //       / \
    //      2   2
    //     / \
    //    3   3
    //   / \
    //  4   4
    // Return false.

    // Solution 1:
    public boolean isBalanced(TreeNode root) {
        if(root==null)
            return true;
        return isBalanced_dfs(root)>0;
    }

    private int isBalanced_dfs(TreeNode root) {
        if(root==null) // height of child of leafs = 0;
            return 0;
        int left_height = isBalanced_dfs(root.left); // left subtree height;
        if(left_height==-1)
            return -1;
        int right_height = isBalanced_dfs(root.right); // right subtree height;
        if(right_height==-1)
            return -1;
        if(Math.abs(left_height-right_height)>1) // balanced?
            return -1;
        return (1+Math.max(left_height,right_height));
    }

    // Solution 2:
    public boolean isBalanced2(TreeNode root) {
        if(root==null)
            return true;
        if(Math.abs(isBalanced_dfs2(root.left)-isBalanced_dfs2(root.right)) > 1)
            return false;
        return isBalanced2(root.left) && isBalanced2(root.right);
    }

    private int isBalanced_dfs2(TreeNode root) {
        if(root==null)
            return 0;
        return 1 + Math.max(isBalanced_dfs2(root.left),isBalanced_dfs2(root.right));
    }

    /** 105. Construct Binary Tree from Preorder and Inorder Traversal */
    // preorder = [3,9,20,15,7]
    // inorder = [9,3,15,20,7]
    // Return the following binary tree:
    //     3
    //    / \
    //   9  20
    //     /  \
    //    15   7

    public TreeNode buildTree_pre(int[] preorder, int[] inorder) {
        return pre_dfs(preorder,inorder,0,0,inorder.length-1);
    }

    private TreeNode pre_dfs(int[] preorder, int[] inorder, int pre_st, int in_st, int in_end) {
        if(pre_st>preorder.length-1 || in_st>in_end)
            return null;
        TreeNode curr = new TreeNode(preorder[pre_st]);
        int idx = 0;
        while(inorder[idx]!=preorder[pre_st]) {
            idx++;
        }
        curr.left = pre_dfs(preorder,inorder,pre_st+1,in_st,idx-1);
        curr.right = pre_dfs(preorder,inorder,pre_st+idx-in_st+1,idx+1,in_end);
        return curr;
    }
    /** 106. Construct Binary Tree from Inorder and Postorder Traversal */
    // inorder = [9,3,15,20,7]
    // postorder = [9,15,7,20,3]
    //     3
    //   /   \
    //  9    20
    //      /   \
    //     15    7

    public TreeNode buildTree_post(int[] inorder, int[] postorder) {
        return post_dfs(inorder,postorder,postorder.length-1,0,postorder.length-1);
    }

    private TreeNode post_dfs(int[] inorder, int[] postorder, int post_st, int in_st, int in_end) {
        if(post_st<0 || in_st>in_end)
            return null;
        TreeNode curr = new TreeNode(postorder[post_st]);
        int idx = 0;
        while(inorder[idx]!=postorder[post_st]) {
            idx++;
        }
        curr.left = post_dfs(inorder,postorder,post_st-(in_end-idx)-1,in_st,idx-1);
        curr.right = post_dfs(inorder,postorder,post_st-1,idx+1,in_end);
        return curr;
    }

    /** 687. Longest Univalue Path */
    // Input:
    //
    //              1
    //             / \
    //            4   5
    //           / \   \
    //          4   4   5
    // Output: 2 (4->4->4)
    /** 如果要得到的是一个不一定包含根结点的计算过程的值：
    /* 那么必须需要一个全局变量，或者一个Object的引用，保证其值不会因调用的返回而丢失。
    /* 同时dfs的helper function可以不返回值，或者返回一个每一层的计算结果。*/

    public int longestUnivaluePath(TreeNode root) {
        int[] res = new int[]{0};
        getPath(root,res);
        return res[0];
    }
    private int getPath(TreeNode root,int[] res) {
        if(root==null)
            return 0;
        int leftPath = getPath(root.left,res);
        int rightPath = getPath(root.right,res);
        if(root.left!=null)
            leftPath = root.val==root.left.val?leftPath+1:0;
        if(root.right!=null)
            rightPath = root.val==root.right.val?rightPath+1:0;
        res[0] = Math.max(res[0],rightPath+leftPath);
        return Math.max(leftPath,rightPath);
    }

}
