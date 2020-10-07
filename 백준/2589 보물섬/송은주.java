import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**문제가 되는 부분은 */
class Point{
	int x;
	int y;
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

public class Main {
	private static int N;
	private static int M;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};
	private static String[] input;
	private static int[][] visited;

	private static int bfs(int i, int j) {
		Queue<Point> q = new LinkedList<Point>();
		
		q.offer(new Point(i, j));
		visited = new int[N][M];
		
		int max = -1;
		
		while(!q.isEmpty()) {
			int x = q.peek().x;
			int y = q.poll().y;
			
			for(int k=0; k<4; ++k) {
				int nX = x + dx[k];
				int nY = y + dy[k];
				
				if(0<=nX && nX < N && 0<=nY && nY < M) {
					if(visited[nX][nY]==0 && input[nX].charAt(nY)=='L') {
                        if(nX == i && nY == j) continue;
						visited[nX][nY] = visited[x][y] + 1;
						q.offer(new Point(nX, nY));
						
						if(visited[nX][nY] > max) max = visited[nX][nY];
					}
				}
			}
		}
		
		//System.out.println("max결과 =>"+max);
		return max;
	}



	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
//		System.out.println("행,열값=>"+N+","+M);
		
		input = new String[N];
		
		for(int i=0; i<N; ++i) {
			input[i] = br.readLine();
		}
		
		int anw = 0;
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(input[i].charAt(j)=='W') continue;
				int res = bfs(i, j);
				if(anw < res) {
					anw = res;
				}
				
			}
		}
		
		System.out.println(anw);
	}
} // end of class
