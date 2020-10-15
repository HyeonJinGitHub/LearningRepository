import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Info{
	int x;
	int y;
	public Info(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}
public class Main{
	private static int N;
	private static int M;
	private static int[][] input;
	private static int[] dx = {-1, 0, 1, 0};
	private static int[] dy = {0, 1, 0, -1};
	private static int maX;
	private static int maY;
	private static int maDir;
	private static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		input = new int[N][M];
		visited = new boolean[N][M];
		st = new StringTokenizer(br.readLine(), " ");
		maX = Integer.parseInt(st.nextToken());
		maY = Integer.parseInt(st.nextToken());
		maDir = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; ++j) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		while(true) {
			//현재 위치 청소
			visited[maX][maY] = true;
			int temp = 0;
			for(int i=0; i<4; ++i) {
				maDir = _toggle(maDir);
				
				int nX = maX + dx[maDir];
				int nY = maY + dy[maDir];
				
				if(0<=nX && nX < N && 0<=nY&&nY <= M) {
					if(!visited[nX][nY] && input[nX][nY]==0) {
						maX = nX;
						maY = nY;
						break;
					}else {
						temp++;
					}
				}
				
			}
			
			if(temp==4) {
				int nDir = _back(maDir);
				int nX = maX + dx[nDir];
				int nY = maY + dy[nDir];
				
				if(0<=nX && nX < N &&0<=nY && nY< M) {
					if(input[nX][nY]==1) {
						break;
					}else {
						maX = nX;
						maY = nY;
						continue;
					}
				}
			}
			
			//print();
			
		}
		
		int anw = 0;
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(visited[i][j]) anw++;
			}
		}
		System.out.println(anw);
	}
	private static void print() {
		System.out.println("============");
		
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				System.out.print(visited[i][j]?"1\t":"0\t");
			}
			System.out.println();
		}
		
		System.out.println(maX +","+maY + ","+maDir);
	}
	private static int _back(int maDir2) {
		switch(maDir2) {
		case 0: return 2;
		case 2: return 0;
		case 1: return 3;
		case 3: return 1;
		}
		return -1;
	}
	private static int _toggle(int dir) {
		switch(dir) {
		case 0: return 3;
		case 1: return 0;
		case 2: return 1;
		case 3: return 2;
		}
		return -1;
	}
}
