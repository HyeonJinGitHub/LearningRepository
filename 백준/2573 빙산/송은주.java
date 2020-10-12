import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

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
	private static int[] dx= {0, 0, 1, -1};
	private static int[] dy= {1, -1, 0, 0};
	private static int N;
	private static int M;
	private static int[][] map;
	private static int[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
	
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new int[N][M];
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int anw = 0;
		boolean flag = false;
		while(true) {
			if(check()) break;
			flag = init();
			if(flag) break;
			anw += 1;
			melt();
		}

		if(flag) anw = 0;
		System.out.println(anw);
		
	}

	private static void melt() {
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(map[i][j]!=0) {
					int zeroArea = 0;
					for(int k=0; k<4; ++k) {
						int nX = i+dx[k];
						int nY = j+dy[k];
						
						if(0<=nX && nX <N && 0<=nY && nY < M) {
							if(map[nX][nY]==0) {
								zeroArea += 1;
							}
						}
					}
					visited[i][j] = zeroArea;
				}
			}
		}
		
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(map[i][j] <= visited[i][j]) {
					map[i][j] = 0;
				}else map[i][j] -= visited[i][j];
			}
		}
		
	}

	private static void print() {
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				System.out.print(map[i][j] + "\t");
			}
			System.out.println();
		}
		
	}

	private static boolean check() {
		int cnt = 0;
		init();

		int zero = 0;
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(map[i][j]!=0 && visited[i][j]==0) {
					if(cnt==1) return true;
					cnt++;
					bfs(i, j);
				}
			}
		}
		
		return false;
	}

	private static void bfs(int i, int j) {
		Queue<Point>q = new LinkedList<>();
		
		q.offer(new Point(i, j));
		visited[i][j] = 1;
		
		while(!q.isEmpty()) {
			int x = q.peek().x;
			int y = q.peek().y;
			
			q.poll();
			
			for(int k=0; k<4; ++k) {
				int nX = x + dx[k];
				int nY = y + dy[k];
				
				if(0<=nX && nX <N && 0<=nY && nY < M) {
					if(map[nX][nY]!=0 && visited[nX][nY]==0) {
						q.offer(new Point(nX, nY));
						visited[nX][nY] = 1;
					}
				}
			}
		}
	}

	
	private static boolean init() { //시간 줄이기 위해서 이니트 하면서 ..  .ㅜㅜ어렵당
		int zeroCnt = 0;
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				visited[i][j] = 0;
				if(map[i][j]==0) zeroCnt += 1;
			}
		}
		
		return zeroCnt==N*M?true:false;
	}
	
}//end of class
