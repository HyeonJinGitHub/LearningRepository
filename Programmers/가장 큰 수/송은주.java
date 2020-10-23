import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        String[] str = new String[numbers.length];
        
        for(int i=0; i<numbers.length; ++i){
            str[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(str, new Comparator<String>(){
           public int compare(String s1, String s2){
               return (s2+s1).compareTo(s1+s2);
           } 
        });
        return str[0].equals("0")?"0":String.join("", str);
        
    }
}
