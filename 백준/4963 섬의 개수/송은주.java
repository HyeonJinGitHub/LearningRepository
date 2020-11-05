import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main{
	private static int N;
	private static int M;
	private static boolean[][] input;
	private static int[] dx = {0, 0, 1, -1, 1, -1, -1, 1};
	private static int[] dy = {1, -1, 0, 0, -1, 1, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			int cnt = 0;
			st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			if(N==0 && M==0) break;
			
			input = new boolean[N][M];
			
			for(int i=0; i<N; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<M; ++j) {
					input[i][j] = st.nextToken().charAt(0)=='0'?false:true;
				}
			}
			/*
			for(int i=0; i<N; ++i) {
				for(int j=0; j<M; ++j) {
					System.out.print(input[i][j]?"1":"0");
				}
				System.out.println();
			}
			*/
			for(int i=0; i<N; ++i) {
				for(int j=0; j<M; ++j) {
					if(input[i][j]) {
						cnt++;
						
						Queue<int[]> q = new LinkedList<>();
						q.offer(new int[] {i, j});
						
						input[i][j] = false;
						
						while(!q.isEmpty()) {
							int x = q.peek()[0];
							int y = q.poll()[1];
							
							for(int k=0; k<8; ++k) {
								int nX = x + dx[k];
								int nY = y + dy[k];
								
								if(0<=nX && nX < N &&0<=nY&& nY < M) {
									if(input[nX][nY]) {
										q.offer(new int[] {nX, nY});
										input[nX][nY] = false;
									}
								}
							}
						}
						
						
					}
				}
			}
			System.out.println(cnt);
		}
		
		
		
	}
}
