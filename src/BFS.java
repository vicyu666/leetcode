import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {

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
        Queue<TreeNode> level = new LinkedList<>();
        level.offer(root);
        while(!level.isEmpty()) {
            int size = level.size(), level_max = Integer.MIN_VALUE;
            for(int i=0;i<size;i++) {
                TreeNode curr = level.poll();
                level_max = Math.max(curr.val,level_max);
                if(curr.left!=null) level.offer(curr.left);
                if(curr.right!=null) level.offer(curr.right);
            }
            res.add(level_max);
        }
        return res;
    }

    /** 207. Course Schedule */
    // Input: 2, [[1,0],[0,1]]
    // Output: false

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> neighbors = new ArrayList<>();
        int[] indegree = new int[numCourses];
        Queue<Integer> q = new LinkedList<>();
        int cnt = 0;
        for(int i=0;i<numCourses;i++) {
            neighbors.add(new ArrayList<Integer>());
        }

        for(int i=0;i<prerequisites.length;i++) {
            neighbors.get(prerequisites[i][1]).add(prerequisites[i][0]);
            indegree[prerequisites[i][0]]++;
        }
        for(int i=0;i<numCourses;i++) {
            if(indegree[i]==0)
                q.offer(i);
        }
        while(!q.isEmpty()) {
            int curr = q.poll();
            cnt++;
            for(int course : neighbors.get(curr)) {
                indegree[course]--;
                if(indegree[course]==0)
                    q.offer(course);
            }
        }

        return cnt==numCourses;
    }

    /** 210. Course Schedule II */
    // Input: 4, [[1,0],[2,0],[3,1],[3,2]]
    // Output: [0,1,2,3] or [0,2,1,3]

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        List<List<Integer>> neighbors = new ArrayList<>();
        int[] indegree = new int[numCourses];
        for(int i=0;i<numCourses;i++) {
            neighbors.add(new ArrayList<Integer>());
        }
        for(int i=0;i<prerequisites.length;i++) {
            neighbors.get(prerequisites[i][1]).add(prerequisites[i][0]);
            indegree[prerequisites[i][0]]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<numCourses;i++) {
            if(indegree[i]==0)
                q.offer(i);
        }
        int cnt = 0;
        while(!q.isEmpty()) {
            int num = q.poll();
            for(int i : neighbors.get(num)) {
                indegree[i]--;
                if(indegree[i]==0)
                    q.offer(i);
            }
            res[cnt++]=num;
        }
        if(cnt!=numCourses)
            return new int[0];
        return res;
    }


}
