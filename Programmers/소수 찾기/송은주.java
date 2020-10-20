import java.util.*;
class Solution {
    public boolean[] isPrime = new boolean[10000000];
    public boolean[] isSelected;
    public HashSet<Integer> hashSet = new HashSet<Integer>();
    public int[] arr;
    public int answer;
    public void init(){
        // int length = isPrime.length/2+1;
        for(int i=2; i<isPrime.length; ++i){
            for(int j=i*2; j<isPrime.length; j+=i){
                isPrime[j] = true;
            }
        }
    }
    public int solution(String numbers) {
        
        init();
        for(int i=1; i<=numbers.length(); ++i){
            //System.out.println(i);
            isSelected = new boolean[numbers.length()];
            arr = new int[i];
            go(i, 0, numbers);
        }
        return answer;
    }
    
    public void go(int N, int cnt, String numbers){
        if(N==cnt){
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<arr.length; ++i){
                sb.append(arr[i]);
            }
             //System.out.println("초안"+sb.toString());
             if(sb.length()==0) return;
            if(sb.charAt(0)=='0') return;
            
            if(Integer.parseInt(sb.toString())==0) return;
            if(Integer.parseInt(sb.toString())==1) return;
            if(hashSet.contains(Integer.parseInt(sb.toString()))) return;
            hashSet.add(Integer.parseInt(sb.toString()));
            //System.out.println("필터링후"+sb.toString());
             if(!isPrime[Integer.parseInt(sb.toString())]) answer++;
             
             return;
        }
        
         for(int i=0; i<numbers.length(); ++i){
             if(isSelected[i]) continue;
             isSelected[i] = true;
             arr[cnt] = numbers.charAt(i)-'0';
             go(N, cnt+1, numbers);
             isSelected[i]= false;          
         }
    }
}
