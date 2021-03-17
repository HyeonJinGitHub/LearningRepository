import java.util.PriorityQueue;
import java.util.Comparator;
class Solution {
    public int solution(int[] scoville, int K) {
        int result = 0;
        PriorityQueue<Integer> q = new PriorityQueue(new Comparator<Integer>(){
           public int compare(Integer a, Integer b){
               return a-b;
           } 
        });
        for(int i=0; i<scoville.length; ++i){
            q.offer(scoville[i]);
        }
        while(q.peek()<K && q.size()>=2){
            int a = q.poll();
            int b = q.poll();
            
            q.offer(a + b*2);
            result++;
        }
        if(q.peek()>=K){
            return result;
        }else return -1;
        
    }
}
