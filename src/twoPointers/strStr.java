package twoPointers;

public class strStr {

    /** 28. Implement strStr() */
    // Example 1:
    //
    // Input: haystack = "hello", needle = "ll"
    // Output: 2
    public int strStr(String haystack, String needle) {
        if(needle.length()==0) return 0;
        int i=0, j=0;
        while(i<haystack.length()&&j<needle.length()) {
            if(haystack.charAt(i)==needle.charAt(j)) {
                i++;
                j++;
            } else {
                i = i-j+1;
                j = 0;
            }
        }
        return j==needle.length()?i-j:-1;
    }
}
