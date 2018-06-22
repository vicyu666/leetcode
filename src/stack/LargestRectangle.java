package stack;

import java.util.Stack;

public class LargestRectangle {

    /** 84. Largest Rectangle in Histogram */

    public int largestRectangleArea(int[] heights) {
        if(heights.length==0) return 0;
        Stack<Integer> indexes = new Stack<>();
        int max_area = 0;

        for(int i=0;i<=heights.length;i++) {
            int curr_height = (i==heights.length)?-1:heights[i];
            while(!indexes.isEmpty() && curr_height<=heights[indexes.peek()]) {
                int h = heights[indexes.pop()];
                int w = indexes.isEmpty()?i:i-indexes.peek()-1;
                max_area = Math.max(max_area,h*w);
            }
            indexes.push(i);
        }
        return max_area;
    }

    /** 85. Maximal Rectangle */

    public int maximalRectangle(char[][] matrix) {
        if(matrix.length==0||matrix[0].length==0) return 0;
        int max_area = 0;
        int m = matrix.length, n = matrix[0].length;

        for(int i=0;i<m;i++) {
            char[] row = new char[n];
            for(int j=0;j<n;j++) {
                for(int h=i;h>=0;h--) {
                    if(matrix[h][j]=='1') row[j]++;
                    else break;
                }
            }

            Stack<Integer> indexs= new Stack<>();
            for(int j=0;j<=n;j++) {
                int curr_height = (j==n)?-1:row[j];
                while(!indexs.isEmpty()&&curr_height<=row[indexs.peek()]) {
                    int h = row[indexs.pop()];
                    int w = indexs.isEmpty()?j:j-indexs.peek()-1;
                    max_area = Math.max(max_area,h*w);
                }
                indexs.push(j);
            }
        }
        return max_area;
    }
}
