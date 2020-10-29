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
	private static int N;
	private static int M;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};
	private static String[] input;
	private static int[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		input = new String[N];
		visited = new int[N][M];
		for(int i=0; i<N; ++i) {
			input[i] = br.readLine();
		}
		
		visited[0][0] = 1;
		Queue<Info> q = new LinkedList<>();
		
		q.offer(new Info(0, 0));
		
		while(!q.isEmpty()) {
			int x = q.peek().x;
			int y = q.poll().y;
			
			for(int k =0; k<4; ++k) {
				int nX = x + dx[k];
				int nY = y + dy[k];
				
				if(0<=nX && nX < N &&0<=nY && nY < M) {
					if(input[nX].charAt(nY)=='0')continue;
					
					if(visited[nX][nY]==0) {
						visited[nX][nY] = visited[x][y] + 1;
						q.offer(new Info(nX, nY));
					}
				}
			}
		}
		
		
		System.out.println(visited[N-1][M-1]);
	}
}
