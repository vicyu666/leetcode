package stringOperation;

import java.util.HashSet;
import java.util.Set;

public class reverseVowel {
    public String reverseVowels(String s) {
        if(s == null) {
            return "";
        }
        char[] letter = s.toCharArray();
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');

        for(int i=0, j=s.length()-1; i<j; ) {
            if(!vowels.contains(s.charAt(i))) {
                i++;
                continue;
            }
            if(!vowels.contains(s.charAt(j))) {
                j--;
                continue;
            }
            char tmp;
            tmp = letter[i];
            letter[i] = letter[j];
            letter[j] = tmp;
            i++; j--;
        }
        return String.valueOf(letter);
    }
}
