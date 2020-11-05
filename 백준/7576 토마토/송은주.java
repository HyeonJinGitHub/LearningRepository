import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
class Info{
	int x;
	int y;
	public Info(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Main{
	private static int M;
	private static int N;
	private static int[][] input;
	private static boolean[][] visited;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		Queue<Info>q = new LinkedList<>();
		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		input = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int j=0; j<M; ++j) {
				input[i][j] = Integer.parseInt(st.nextToken());
				if(input[i][j]==1) {
					q.offer(new Info(i, j));
				}
			}
		}
		
		int cnt = -1;
		while(!q.isEmpty()) {
			
			int qSize = q.size();
			
			for(int z=0; z<qSize; ++z) {
				int x = q.peek().x;
				int y = q.poll().y;
				//System.out.println(x+","+y);
				
				for(int k=0; k<4; ++k) {
					int nX = x + dx[k];
					int nY = y + dy[k];
					
					if(0<=nX && nX < N && 0<=nY && nY < M) {
						if(input[nX][nY]==1) continue;
						if(input[nX][nY]==-1) continue;
						
						input[nX][nY] = 1;
						q.offer(new Info(nX, nY));
					}
				}
				//System.out.println("초 끝!");
			}
			cnt++;
			
			
		}
		
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(input[i][j]==0) {
					System.out.println(-1);
					return;
				}
			}
		}
		
		System.out.println(cnt);
		
	}
}
