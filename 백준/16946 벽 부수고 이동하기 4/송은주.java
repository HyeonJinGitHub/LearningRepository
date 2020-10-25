import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
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
	private static char[][] input;
	private static int[][] visited;
	private static int[][] group;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};
	private static int[][] result;
	private static int[][] group2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
	
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		input = new char[N][M];
		group = new int[N][M];
		group2 = new int[N][M];
		result = new int[N][M];
		
	
		for(int i=0; i<N; ++i) {
			String s = br.readLine();
			for(int j=0; j<M; ++j) {
				input[i][j] = s.charAt(j);
			}
		}
		
		int g = 1;
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(input[i][j]=='0' && group[i][j]==0) {
					go(i, j, g++);
				}
			}
		}
		HashSet<Integer> set = new HashSet<>();
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(input[i][j]=='0') {
					result[i][j] = 0;
					continue;
				}
				set.clear();
				int cnt = 1;
				for(int k=0; k<4; ++k) {
					int nX = i + dx[k];
					int nY = j + dy[k];
					
					if(0<=nX && nX < N &&0<=nY && nY < M) {
						if(input[nX][nY]=='1') continue;
						if(!set.contains(group2[nX][nY])) {
							set.add(group2[nX][nY]);
							cnt += group[nX][nY];
						}
					}
				}
				
				
				result[i][j] = cnt % 10;
				
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				sb.append(result[i][j]);
			}
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}

	private static void go(int i, int j, int g) {
		ArrayList<Info> arr = new ArrayList<>();
		Queue<Info> q = new LinkedList<>();
		
		q.offer(new Info(i, j));
		group[i][j] = -1;
		int cnt = 0;
		arr.add(new Info(i, j));
		
		while(!q.isEmpty()) {
			int x = q.peek().x;
			int y = q.poll().y;
			cnt++;
			
			for(int k=0; k<4; ++k) {
				int nX = x + dx[k];
				int nY = y + dy[k];
				
				if(0<=nX && nX < N &&0<=nY && nY < M) {
					if(input[nX][nY]=='0' && group[nX][nY]==0) {
						group[nX][nY] = -1;
						arr.add(new Info(nX, nY));
						q.offer(new Info(nX, nY));
					}
				}
			}
		}
		
		for(Info I:arr) {
			group[I.x][I.y]= cnt; 
			group2[I.x][I.y]= g; 
		}
	}
}
