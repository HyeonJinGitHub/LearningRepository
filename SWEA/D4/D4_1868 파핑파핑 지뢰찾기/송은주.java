import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Solution{
	private static int T;
	private static int N;
	private static String[] input;
	private static int[][] visited;
	private static int[] dx = {0, 0, 1, -1, 1, -1, -1, 1};
	private static int[] dy = {1, -1, 0, 0, -1, 1, -1, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Queue<int[]> q = new LinkedList<>();
		
		T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; ++t) {
			N = Integer.parseInt(br.readLine());
			input = new String[N];
			visited = new int[N][N];
			
			init();
			for(int i=0; i<N; ++i) {
				input[i] = br.readLine();
			}
			
			int anw = 0;
			for(int i=0; i<N; ++i) {
				for(int j=0; j<N; ++j) {
					if(input[i].charAt(j)=='.' && visited[i][j]==-1) {
						q.clear();
						int cnt = 0;
						for(int k=0; k<8; ++k) {
							int nX = i+dx[k];
							int nY = j+dy[k];
							
							if(0<=nX && nX < N && 0<=nY && nY < N) {
								if(input[nX].charAt(nY)=='*') cnt++;
							
							}
							
						}
						if(cnt==0) {
							visited[i][j] = cnt;
							anw++;
							q.offer(new int[] {i, j});
							bfs(i, j, q);
						}
						
					}
				}
			}
			
			for(int i=0; i<N; ++i) {
				for(int j=0; j<N; ++j) {
					if(input[i].charAt(j)=='.' && visited[i][j]==-1) {
						anw++;
					}
				}
			}
			
			sb.append('#').append(t).append(' ').append(anw).append('\n');
		}
		System.out.println(sb);
	}

	private static void print() {
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				System.out.print(visited[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("=============================");
		
	}

	private static void bfs(int i, int j, Queue<int[]>q) {
		
		while(q.size()>0) {
			int[] temp = q.poll();
			int x = temp[0];
			int y = temp[1];
//			if(visited[x][y]!=-1) continue;
			for(int k=0; k<8; ++k) {
				int nX = x + dx[k];
				int nY = y + dy[k];
				
				if(0<=nX && nX < N && 0<=nY && nY < N) {
					int cnt = 0;
					for(int kk=0; kk<8; ++kk) {
						int nnX = nX + dx[kk];
						int nnY = nY + dy[kk];
						
						if(0<=nnX && nnX < N &&0<=nnY && nnY < N) {
							if(input[nnX].charAt(nnY)=='*') cnt++; 
						}
					}
					if(cnt==0 && visited[nX][nY]==-1) {
						visited[nX][nY] = cnt;
						q.offer(new int[] {nX, nY});
					}
					
					if(visited[nX][nY]==-1) {
						visited[nX][nY] = cnt;
					}
				}
			}
//		print();
		}
		
	}

	private static void init() {
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				visited[i][j] =-1;
			}
		}
	}

}
