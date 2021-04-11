// 다음과 같이 import를 사용할 수 있습니다.
import java.util.*;

class Main {
	public int[] dx = {0, 0, 1, -1};
	public int[] dy = {1, -1, 0, 0};
    public int solution(int n, int[][] garden) {
        // 여기에 코드를 작성해주세요.
			int num = 0;
			
			Queue<int[]> q = new LinkedList<>();
			for(int i=0; i<n; ++i){
				for(int j=0; j<n; ++j){
					if(garden[i][j]==1){
						q.offer(new int[]{i, j});
						num++;
					}
				}
			}
      if(num==n*n) return 1;
			int answer = 0;
			
			while(!q.isEmpty()){
				boolean flag = false;
				int qS = q.size();
				
				for(int a = 0; a<qS; ++a){
					int[] now = q.poll();
					
					int x = now[0];
					int y = now[1];
					
					for(int k=0; k<4; ++k){
						int nX = x + dx[k];
						int nY = y + dy[k];
						
						if(0<=nX && nX < n && 0<=nY && nY < n){
							if(garden[nX][nY]==0){
								garden[nX][nY] = 1;
								flag = true;
								q.offer(new int[]{nX,nY});
							}
						}
					}
					
				}
				
				if(flag) answer++;
			}
        return answer;
    }
    
