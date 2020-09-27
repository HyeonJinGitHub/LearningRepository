import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	private static int M;
	private static int N;
	private static int[][] input;
	private static int[][] visited;
	private static int[] dx= {0, 0, 1, -1};
	private static int[] dy= {1, -1, 0, 0};
	private static int anw;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		input = new int[N][M];
		visited = new int[N][M];
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; ++j) {
				input[i][j] = Integer.parseInt(st.nextToken());
				visited[i][j] = -1;
			}
		}
		dfs(0, 0);	
		
		System.out.println(visited[0][0]);
	}

	private static int dfs(int i, int j) {
		if(i==N-1 && j==M-1) {
			return 1;
		}
		
		if(visited[i][j]!=-1) return visited[i][j];
		
		visited[i][j] = 0;
		
		for(int k=0; k<4; ++k) {
			int nX = i + dx[k];
			int nY = j + dy[k];
			
			if(0<=nX && nX < N && 0<=nY && nY < M) {
				if(input[nX][nY] < input[i][j]) {
					visited[i][j] += dfs(nX, nY);
				}
			}
		}
		
		return visited[i][j];
	}


}
