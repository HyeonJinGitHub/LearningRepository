import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution{
	private static int TC;
	private static int N;
	private static int M;
	private static int anw;
	private static int res;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};
	private static int[][] input;
	private static ArrayList<int[]> arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			res = Integer.MIN_VALUE; //보안회사 이익
			anw = Integer.MIN_VALUE; //보안회사 이익에 따른 집개수 
			
			input = new int[N][N];
			arr = new ArrayList<int[]>();
			
			for(int i=0; i<N; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; ++j) {
					input[i][j] = Integer.parseInt(st.nextToken());
					if(input[i][j]==1) {
						arr.add(new int[] {i, j});
					}
				}
			}
			for(int i=21; i>=0; --i) {
				int price = i*i + (i-1)*(i-1);
//				System.out.println("price은"+price);
				for(int n=0; n<N; ++n) {
					for(int m=0; m<N; ++m) {
						boolean flag = true;
						int now = 0;
						boolean[][] visited = new boolean[N][N];
						
						visited[n][m] = true;
						
						Queue<int[]> q = new LinkedList<>();
						
						q.offer(new int[] {n, m, i-1});
						if(input[n][m]==1)now++;
						
						while(!q.isEmpty()) {
							int[] temp = q.poll();
							
							int x = temp[0];
							int y = temp[1];
							int go = temp[2];
							
							for(int o=1; o<=go; ++o) {
								for(int k=0; k<4; ++k) {
									int nX = x + dx[k]*o;
									int nY = y + dy[k]*o;
									//								System.out.println(nX+","+nY);
									
									if(0<=nX && nX < N && 0<=nY &&nY< N) {
										if(!visited[nX][nY]) {
											//										System.out.println("들어왔습니다");
											visited[nX][nY] = true;
											if(input[nX][nY]==1) {
												//											System.out.println("냐오옹???");
												now++;
												//											if((M*now - price) < 0) {
												//												flag = false;
												//												break;
												//											}
											}
											q.offer(new int[] {nX, nY, go-o});
										}
									}
								}
							}
						}
						if(!flag) continue;
//						if(n==3 && m==3) 
//						System.out.println(now+"now는?");
						int profit = M*now - price;
//						if(n==3 && m==3) 
//						System.out.println(profit+"?"+now);
						if(profit >=0) {
							if(anw < now) {
								anw = now;
							}
							//						res = profit;
							//						anw = now;
						}
						
//						if(n==3 && m==3) 
//						print(visited);
					}
					
				}
			}
			sb.append('#').append(tc).append(' ').append(anw).append('\n');
		}
		
		System.out.print(sb);
	}

	private static void print(boolean[][] visited) {
		System.out.println("===");
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				System.out.print(visited[i][j]?"1":"0");
				System.out.print("\t");
}
			System.out.println();
		}
		
	}
}
