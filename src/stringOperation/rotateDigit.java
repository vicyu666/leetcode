package stringOperation;

import java.util.HashSet;
import java.util.Set;

public class rotateDigit {

    public int rotatedDigits(int N) {
        Set<Integer> goodNumber = new HashSet<>();
        goodNumber.add(2);
        goodNumber.add(5);
        goodNumber.add(6);
        goodNumber.add(9);
        Set<Integer> badNumber = new HashSet<>();
        badNumber.add(3);
        badNumber.add(4);
        badNumber.add(7);

        int res = 0;
        for (int num=1; num<=N; num++) {
            int i = num;
            boolean good = false, bad = false;
            while(i > 0) {
                if(goodNumber.contains(i%10)) {
                    good = true;
                }
                if(badNumber.contains(i%10)) {
                    bad = true;
                }
                i = i / 10;
            }
            if(good && !bad) {
                res++;
            }
        }
        return res;
    }
}
