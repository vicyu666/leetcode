package stringOperation;

import java.util.HashMap;
import java.util.Map;

public class rtoi {

    public String intToRoman(int num) {
        String res = "";
        if(num <= 0) {
            return res;
        }
        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] dict = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        for(int i=0; i<values.length; i++) {
            while(num >= values[i]) {
                res += dict[i];
                num -= values[i];
            }
        }
        return res;
    }

    public int romanToInt(String s) {
        int value=0;
        Map<Character,Integer> dic=new HashMap<Character,Integer>(){
            {
                put('I',1);
                put('V',5);
                put('X',10);
                put('L',50);
                put('C',100);
                put('D',500);
                put('M',1000);
            }
        };
        for(int i=0;i<s.length()-1;i++){
            if(dic.get(s.charAt(i))<dic.get(s.charAt(i+1))){
                value-=dic.get(s.charAt(i));
            }else{
                value+=dic.get(s.charAt(i));
            }
        }
        return value+dic.get(s.charAt(s.length()-1));
    }
}
