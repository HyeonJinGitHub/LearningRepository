import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Info{
	int time;
	char dir;
	public Info(int time, char dir) {
		super();
		this.time = time;
		this.dir = dir;
	}
}

public class Main{
	private static int[][] input;
	private static int N;
	private static int K;
	private static int snakeSize;
	private static int[] dx = {0, 0, -1, 1};
	private static int[] dy = {-1, 1, 0, 0};
	private static Info[] info;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	
		N = Integer.parseInt(br.readLine());
		input = new int[N][N];
		snakeSize = 1;

		K = Integer.parseInt(br.readLine());
		for(int i=0; i<K; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			input[x-1][y-1] = -1; //-1은 사과
		}
		
		K = Integer.parseInt(br.readLine());
		int snakeX = 0;
		int snakeY = 0;
		int snakeDir = 1;
		
		info = new Info[K+1];
		for(int i=0; i<K; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int time = Integer.parseInt(st.nextToken());
			char ch = st.nextToken().charAt(0);
			
			info[i] = new Info(time, ch);
		}
		info[K] = new Info(100000, 'D');
		
		input[snakeX][snakeY] = 0;
		int anw = 0;
		for(int i=0; i<K+1; ++i) {
			int time = info[i].time;
			char ch = info[i].dir;
//			System.out.println(time+","+ch);
			for(; anw<time; ++anw) {
//				System.out.println(anw+"입니당");
				int x = snakeX;
				int y = snakeY;
//				System.out.println(x+","+y+"초기초기");
				int nX = x + dx[snakeDir];
				int nY = y + dy[snakeDir];
//				System.out.println(nX + ","+nY+"ㅋㅋ");
				
				if(0<=nX && nX < N && 0<=nY && nY < N) {
					if(input[nX][nY]==-1) {
						input[nX][nY] = input[x][y] + 1;
						snakeSize += 1;
//						System.out.println("뱀커짐");
					}
					else if(input[nX][nY]==0) {
						input[nX][nY] = input[x][y] + 1;
					}
					
					else if(Math.abs(input[nX][nY]-input[x][y]) >=snakeSize) {
						input[nX][nY] = input[x][y] + 1;
					}else {
						System.out.println(input[x][y]+1);
						return;
					}
					
					snakeX = nX;
					snakeY = nY;
//					anw = input[nX][nY];
				}else {
					System.out.println(input[x][y]+1);
					return;
				}
				
//				print();
//				System.out.println("한번끝~");
			}
			snakeDir = _toggle(snakeDir, ch);
		}
		
	}

	
	private static int _toggle(int snakeDir, char ch) {
		switch(snakeDir) {
		case 0:
			if(ch=='L') return 3;
			if(ch=='D') return 2;
		case 1:
			if(ch=='L') return 2;
			if(ch=='D') return 3;
		case 2:
			if(ch=='L') return 0;
			if(ch=='D') return 1;
		case 3:
			if(ch=='L') return 1;
			if(ch=='D') return 0;
		}
		return -1;
	}


	private static void print() {
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				System.out.print(input[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("==========");
	}
}
