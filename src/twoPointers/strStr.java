package twoPointers;

public class strStr {

    /** 28. Implement strStr() */
    // Example 1:
    //
    // Input: haystack = "hello", needle = "ll"
    // Output: 2
    // O(n ^ 2)
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

    // way 2: use hashing function to generate hashcode
    // O(n + m)
    private int BASE = 100000;
    public int strStrII(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }

        int len = needle.length();
        if (len == 0) {
            return 0;
        }

        int needleCode = 0;
        for (int i=0; i<len; i++) {
            needleCode = (needleCode * 19 + needle.charAt(i)) % BASE;
        }

        int power = 1;
        for (int i=0; i<len; i++) {
            power = (power * 19) % BASE;
        }

        int haystackCode = 0;
        for (int i=0; i<haystack.length(); i++) {
            haystackCode = (haystackCode * 19 + haystack.charAt(i)) % BASE;

            if (i < len-1) {
                continue;
            }

            if (i >= len) {
                haystackCode = haystackCode - (haystack.charAt(i - len) * power) % BASE;
                if (haystackCode < 0) {
                    haystackCode += BASE;
                }
            }

            if (haystackCode == needleCode) {
                if (haystack.substring(i-len+1, i+1).equals(needle)) {
                    return i-len+1;
                }
            }
        }
        return -1;
    }
}
