import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
	int x;
	int y;
	int z;
	public Point(int x, int y, int z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
}

public class Main {
	private static int N;
	private static int M;
	private static int K;
	private static int[][][] visited;
	private static String[] input;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		visited = new int[N][M][K+1];
		input = new String[N];
		for(int i=0; i<N; ++i) {
			input[i] = br.readLine();
		}
		
		Queue<Point>q = new LinkedList<Point>();
		visited[0][0][0] = 1;
		
		q.offer(new Point(0, 0, 0));
		while(!q.isEmpty()) {
			int x = q.peek().x;
			int y = q.peek().y;
			int z = q.peek().z;
			q.poll();
			for(int k=0; k<4; ++k) {
				int nX = x + dx[k];
				int nY = y + dy[k];
				
				if(0<=nX && nX < N && 0<=nY && nY < M) {
					if(input[nX].charAt(nY)=='0' && visited[nX][nY][z]==0) {
						visited[nX][nY][z] = visited[x][y][z]+1;
						q.offer(new Point(nX, nY, z));
					}
					if(input[nX].charAt(nY)=='1' && visited[nX][nY][z]==0 && z+1<=K) {
						visited[nX][nY][z+1] = visited[x][y][z]+1;
						q.offer(new Point(nX, nY, z+1));		
					}
				}
			}
		}
		
		int anw = Integer.MAX_VALUE;
		boolean flag = false;
		
		for(int i=0; i<=K; ++i) {
//			System.out.println(visited[N-1][M-1][i]);
			if(visited[N-1][M-1][i]!=0) {
				flag = true;
				if(anw > visited[N-1][M-1][i]) {
					anw = visited[N-1][M-1][i];
				}
			}
		}
		if(!flag) anw = -1;
		System.out.println(anw);
//		System.out.println(visited[N-1][M-1][0]);
//		System.out.println(visited[N-1][M-1][1]);
		
	}
	
}//end of Class
