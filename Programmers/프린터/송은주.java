import java.util.*;
class Info{
    int target;
    int idx;
    
    public Info(int target, int idx){
        this.target = target;
        this.idx = idx;
    }
}
class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Info>q = new LinkedList<>();
        for(int i=0; i<priorities.length; ++i){
            q.offer(new Info(priorities[i], i));
        }
        
        int cnt = 0;
        for(int a=0; a<priorities.length; ++a){
            Info I = q.peek();
            int max = I.target;
            int maxIdx = I.idx;
            
            for(int i=0; i<q.size(); ++i){
                Info k = q.poll();
                if(max < k.target){
                    max = k.target;
                    maxIdx = k.idx;
                }
                q.offer(k);
            }
            
            for(int i=0; i<q.size(); ++i){
                Info k = q.poll();
                if(max==k.target && maxIdx==k.idx){
                    cnt++;
                    if(k.idx==location) return cnt;
                    break;
                }else {
                    q.offer(k);
                }
                
            }
        }
        
        
        
        return answer;
    }
}
