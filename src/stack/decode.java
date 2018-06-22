package stack;

import java.util.Stack;

public class decode {
    public String decodeString(String s) {
        Stack<String> stringStack = new Stack<>();
        Stack<Integer> intStack = new Stack<>();

        String res = "";
        char[] arr = s.toCharArray();
        int i = 0;

        while(i<arr.length) {
            if(Character.isDigit(arr[i])) {
                int cnt = 0;
                while(Character.isDigit(arr[i])) {
                    cnt = cnt*10 + arr[i]-'0';
                    i++;
                }
                intStack.push(cnt);
            }
            else if(arr[i]=='[') {
                stringStack.push(res);
                res = "";
                i++;
            }
            else if(arr[i]==']') {
                StringBuilder sb = new StringBuilder(stringStack.pop());
                int times = intStack.pop();
                for(int j=0;j<times;j++) {
                    sb.append(res);
                }
                res = sb.toString();
                i++;
            }
            else {
                res += arr[i];
                i++;
            }
        }
        return res;
    }
}
