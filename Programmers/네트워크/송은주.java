import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        
        Queue<Integer>q = new LinkedList<>();
        for(int i=0; i<n; ++i){
            if(!visited[i]){
                q.offer(i);
                visited[i] = true;
                answer += 1;
                
                while(!q.isEmpty()){
                    int k = q.poll();
                    
                    for(int j=0; j<n; ++j){
                        if(computers[k][j]==1 && !visited[j]){
                            visited[j] = true;
                            q.offer(j);
                        }
                    }
                }
                
            }
        }
        return answer;
    }
}
