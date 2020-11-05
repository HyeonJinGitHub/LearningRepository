import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static int N, INF = Integer.MAX_VALUE;
	static int map[][];
	static int[]dx = {0, 0, 1, -1};
	static int[]dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=TC; ++tc) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			
			for(int i=0; i<N; ++i) {
				String s = br.readLine();
				for(int j=0; j<s.length(); ++j) {
					map[i][j] = s.charAt(j) - '0';
				}
			}//end for
			
			System.out.println("#" + tc + " " + dijkstra(0, 0, N-1, N-1));
		}
		
	}

	private static int dijkstra(int sX, int sY, int eX, int eY) {
		int[][] minTime = new int[N][N];
		boolean[][] visited = new boolean[N][N];
		
		//모든 최소 비용을 최댓값으로 초기화
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				minTime[i][j] = INF;
			}
		}
		
		
		minTime[sX][sY] = 0;
		int x = 0, y =0, cost = 0;
		while(true) { //도착지 오면 끝낼것!
			cost = INF;
			//1. 미 방문 정점 중에 최소비용의 정점 찾기
			for(int i=0; i<N; ++i) {
				for(int j=0; j<N; ++j) {
					if(!visited[i][j] && cost > minTime[i][j]) {
						cost = minTime[i][j];
						x = i;
						y = j;
					}
				}
			}
			
			visited[x][y] = true;
			//선택된 정점이 도착정점이면 끝냄
			if(x==eX && y==eY) return cost;
			
			//2. 선택된 정점을 경유지로 하여 미방문 정점들의 최소비용 갱신
			//선택된 정점의 인접정점은 4방에 있는 정점이므로 4방탐색 수행
			
			for(int k=0; k<4; ++k) {
				int nX = x + dx[k];
				int nY = y + dy[k];
				
				if(0<=nX && nX < N && 0<=nY && nY < N) {
					if(!visited[nX][nY] &&minTime[nX][nY] > cost+map[nX][nY]) {
						minTime[nX][nY] = cost+map[nX][nY];
					}
				}
			}
			
		}
	}
}
