import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
	private static int N;
	private static int sharkSize = 2;
	private static int eatCnt = 0;
	private static Point Shark = new Point(-1, -1);
	private static int foodSize;
	private static int[][] input;
	private static int[][] visited;
	private static int[][] food;
	private static int[]dx = {0, 0, 1, -1};
	private static int[]dy = {1, -1, 0, 0};
	private static int anw;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine().trim());
		input = new int[N][N];
		anw = 0;
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; ++j) {
				input[i][j] = Integer.parseInt(st.nextToken());
				if(input[i][j]==9) {
					Shark.x = i;
					Shark.y = j;
					input[i][j] = 0;
				}
			}
		}
		
		while(true) {
			foodSize = Integer.MAX_VALUE;
			if(!bfs(Shark.x, Shark.y)) break;
//			print();
//			System.out.println("====================");
			eat();
//			print();
		}
		System.out.println(anw);
	}

	private static void eat() {
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				if(food[i][j]==foodSize) {
					input[i][j] = 0;
					eatCnt+=1;
					if(eatCnt==sharkSize) {
						eatCnt=0;
						sharkSize+=1;
					}
					Shark.x = i;
					Shark.y = j;
					anw += food[i][j];
					return;
				}
			}
		}
		
	}

	private static boolean bfs(int i, int j) {
		Queue<Point> q = new LinkedList<Point>();
		boolean flag = false;
		q.offer(new Point(i, j));
		visited = new int[N][N];
		food = new int[N][N];
		visited[i][j] = 0;
		
		while(!q.isEmpty()) {
			int x = q.peek().x;
			int y = q.peek().y;
			q.poll();
			
			for(int k=0; k<4; ++k) {
				int nX = x + dx[k];
				int nY = y + dy[k];
				
				if(!isRange(nX, nY)) continue;
				if(input[nX][nY]==0 && visited[nX][nY]==0) {
					visited[nX][nY] = visited[x][y] + 1;
					q.offer(new Point(nX, nY));
			
				}
				
				if(input[nX][nY]!=0 && visited[nX][nY]==0) {
					if(input[nX][nY]<=sharkSize) {
						q.offer(new Point(nX, nY));
						visited[nX][nY] = visited[x][y] + 1;
						if(input[nX][nY]<sharkSize) {
							flag = true;
							food[nX][nY] = visited[nX][nY];
							if(foodSize > food[nX][nY])
								foodSize = food[nX][nY];
						}
					}
				}
			}
			
			
		}
		
		return flag;
	}

	private static void print() {
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				System.out.print(visited[i][j] + "\t");
			}
			System.out.println();
		}
		
	}

	private static boolean isRange(int nX, int nY) {
		return 0<=nX && nX < N && 0<=nY && nY < N;
	}
}//end of class
