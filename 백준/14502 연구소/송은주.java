import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int max= -1;
	private static int M;
	private static int[][] input;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};
	private static ArrayList<int[]> arr = new ArrayList<>();

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
				if(input[i][j]==2) arr.add(new int[] {i, j});
			}
		}
		
		
		for(int a1=0; a1<N; ++a1) {
			for(int b1=0; b1<M; ++b1) {
				if(input[a1][b1]!=0) continue;
				for(int a2=0; a2<N; ++a2) {
					for(int b2=0; b2<M; ++b2) {
						if(input[a2][b2]!=0) continue;
						for(int a3=0; a3<N; ++a3) {
							for(int b3=0; b3<M; ++b3) {
								if(input[a3][b3]!=0) continue;
								if(a1==a2 && b1==b2) continue;
								if(a1==a3 && b1==b3) continue;
								if(a2==a3 && b2==b3) continue;
								
								int[][] nInput = deepCopy();
								nInput[a1][b1] = 1;
								nInput[a2][b2] = 1;
								nInput[a3][b3] = 1;
								
								int res = go(nInput);
								
								if(res > max) {
									max = res;
								}
							}
						}
					}
				}
			}
		}
		
		System.out.println(max);
	}

	private static int[][] deepCopy() {
		int[][] nInput = new int[N][M];
		
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				nInput[i][j] = input[i][j];
			}
		}
		return nInput;
	}

	private static int go(int[][] nInput) {
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][] visited = new boolean[N][M];
		
		for(int[] a: arr ) {
			q.offer(a);
			visited[a[0]][a[1]] = true;
		}
		
		int res = 0;
		
		while(q.size()> 0) {
			int[] a = q.poll();
			
			int x = a[0];
			int y = a[1];
			
			for(int k=0; k<4; ++k) {
				int nX = x + dx[k];
				int nY = y + dy[k];
				
				if(0<=nX && nX < N && 0<=nY && nY < M) {
					if(nInput[nX][nY]==0 && !visited[nX][nY]) {
						q.offer(new int[] {nX, nY});
						visited[nX][nY] = true;
						nInput[nX][nY] = 2;
					}
				}
			}
			
		}
		
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(nInput[i][j]==0) res++;
			}
		}
		return res;
	}
}
