package stringOperation;

public class atoi {
    // 最小值：Integer.MIN_VALUE= -2147483648 （-2的31次方）
    // 最大值：Integer.MAX_VALUE= 2147483647  （2的31次方-1）

    // 最小值：Long.MIN_VALUE=-9223372036854775808 （-2的63次方）
    // 最大值：Long.MAX_VALUE=9223372036854775807 （2的63次方-1）

    // 最小值：Float.MIN_VALUE=1.4E-45 （2的-149次方）
    // 最大值：Float.MAX_VALUE=3.4028235E38 （2的128次方-1）

    // 最小值：Double.MIN_VALUE=4.9E-324 （2的-1074次方）
    // 最大值：Double.MAX_VALUE=1.7976931348623157E308 （2的1024次方-1）

    public int myAtoi(String str) {
        if(str == null) {
            return 0;
        }

        str = str.trim();
        if(str.length() == 0) {
            return 0;
        }

        double res = 0;
        if(Character.isDigit(str.charAt(0))) {
            res = atoiHelper(str,0);
        } else if(str.charAt(0) == '-') {
            res = (-1) * atoiHelper(str,1);
        } else if(str.charAt(0) == '+') {
            res = atoiHelper(str,1);
        }

        if (res > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (res < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int)res;
    }

    private double atoiHelper(String str, int i) {
        double res = 0;
        int idx = i;
        while(idx < str.length() && Character.isDigit(str.charAt(idx))) {
            res = res*10 + str.charAt(idx)-'0';
            idx ++;
        }
        return res;
    }
}
