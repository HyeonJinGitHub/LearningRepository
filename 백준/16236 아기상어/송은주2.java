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

public class Main{
	private static int N;
	private static int[][] input;
	private static Point Shark;
	private static int sharkSize = 2;
	private static int eatCnt;
	private static int[][] visited;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};
	private static int close;
	private static int anw;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		Shark = null;
		
		input = new int[N][N];
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; ++j) {
				input[i][j] = Integer.parseInt(st.nextToken());
				if(input[i][j]==9) {
					input[i][j] = 0;
					Shark = new Point(i, j);
				}
			}
		}
		
		
		while(true) {
			close = 999999999;
			if(!bfs()) break;
			eat();
			
		}
		
		System.out.println(anw);
	}

	private static void eat() {
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				if(input[i][j]!=0 && (input[i][j] <sharkSize) && visited[i][j] ==close) {
//					System.out.println("먹겠습니다잉"+i+","+j);
					input[i][j] = 0;
					Shark.x = i; Shark.y = j;
					eatCnt++;
					anw += visited[i][j];
					
					if(eatCnt==sharkSize) {
						sharkSize += 1;
						eatCnt = 0;
					}
					return;
					
				}
			}
		}
		
	}

	private static boolean bfs() {
		boolean flag = false;
		Queue<Point>q = new LinkedList<>();
		
		q.offer(new Point(Shark.x, Shark.y));
		visited = new int[N][N];
		
		while(!q.isEmpty()) {
			int x = q.peek().x;
			int y = q.poll().y;
			
			for(int k=0; k<4; ++k) {
				int nX = x + dx[k];
				int nY = y + dy[k];
				
				if(0<=nX && nX < N && 0<=nY && nY < N) {
					if(Shark.x==nX && Shark.y==nY) continue;
					if(visited[nX][nY]==0 && (input[nX][nY] <=sharkSize)) {
						visited[nX][nY] = visited[x][y] + 1;
						q.offer(new Point(nX, nY));
						
						if(input[nX][nY]!= 0 && input[nX][nY]<sharkSize) {
							flag = true;
							if(close > visited[nX][nY]) close = visited[nX][nY];
						}
					}
				}
			}
		}
		
//		System.out.println("close로 나갑니다잉 =>" +close);
//		print();
		
		
		
		return flag;
	}

	private static void print() {
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				System.out.print(visited[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("=======================");
	}

}
