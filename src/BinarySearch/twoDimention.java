package BinarySearch;

public class twoDimention {

    /** 74. Search a 2D Matrix */

    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length==0||matrix[0].length==0) return false;
        int row = matrix.length, col = matrix[0].length;
        int start = 0, end = row-1;
        while(start+1<end) {
            int mid = start+(end-start)/2;
            if(matrix[mid][0]==target) return true;
            if(matrix[mid][0]>target) end = mid;
            if(matrix[mid][0]<target) start = mid;
        }
        int aimRow = 0;
        if(matrix[start][0]<=target&&matrix[start][col-1]>=target) aimRow = start;
        else aimRow = end;
        start = 0; end = col-1;
        while(start+1<end) {
            int mid = start+(end-start)/2;
            if(matrix[aimRow][mid]<target) start = mid;
            if(matrix[aimRow][mid]>target) end = mid;
            if(matrix[aimRow][mid]==target) return true;
        }
        if (matrix[aimRow][start] == target) return true;
        else if (matrix[aimRow][end] == target) return true;
        return false;
    }
}
