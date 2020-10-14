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
		super();
		this.x = x;
		this.y = y;
	}
}

public class Main{
	private static int N;
	private static int M;
	private static boolean flag;
	private static int[][] input;
	private static int[] dx= {0, 0, 1, -1};
	private static int[] dy= {1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		input = new int[N][M];
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; ++j) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int anw = 0;
		boolean lastFlag = false;
		while(true) {
			if(chk() >= 2) {
				lastFlag = true;
				break;
			}
			if(flag) break;
			
			melting();
			
//			print();
			anw += 1;
		}
		
		if(!lastFlag) anw = 0;
		System.out.println(anw);
		
	}

	private static void print() {
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				System.out.print(input[i][j] + "\t");
			}
			System.out.println();
		}
	}

	private static void melting() {
		int[][] temp = new int[N][M];
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(input[i][j]==0) continue;
				
				int t = 0;
				for(int k=0; k<4; ++k) {
					int nX = i+dx[k];
					int nY = j+dy[k];
					if(0<=nX && nX < N && 0 <= nY && nY < M) {
						if(input[nX][nY]==0)
							t += 1;
					}
				}
				
				temp[i][j] = t;
				
			}
		}
		
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				input[i][j] -= temp[i][j];
				if(input[i][j] < 0) input[i][j] = 0;
			}
		}
		
		
	}

	private static int chk() {
		int res = 0;
		int zeroCnt = 0;
		Queue<Info>q = new LinkedList<Info>();
		boolean[][] visited = new boolean[N][M];
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(input[i][j]==0) {
					zeroCnt++; continue;
				}
				if(!visited[i][j] && input[i][j]!=0) {
					res += 1;
					q.offer(new Info(i, j));
					visited[i][j] = true;
					
					while(!q.isEmpty()) {
						int x = q.peek().x;
						int y = q.poll().y;
						
						for(int k=0; k<4; ++k) {
							int nX = x + dx[k];
							int nY = y + dy[k];
							
							if(0<=nX && nX < N && 0<=nY && nY < M) {
								if(input[nX][nY]!=0 && !visited[nX][nY]) {
									visited[nX][nY] = true;
									q.offer(new Info(nX, nY));
								}
							}
						}
					}
				}
			}
		}
		
		if(zeroCnt==N*M) {
			flag = true;
		}
		
		return res;
	}
}//end of class
