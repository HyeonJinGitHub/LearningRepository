import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
	private static int anw = Integer.MAX_VALUE;
	private static int N;
	private static int M;
	private static int[] arr;
	private static ArrayList<Point> virus = new ArrayList<Point>();
	private static int[][] input;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};
//	private static int temp;
	private static int emptyPlace;
	private static int zzzzz;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M];
		input = new int[N][N];
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; ++j) {
				input[i][j] = Integer.parseInt(st.nextToken());
				if(input[i][j] == 2) {
					virus.add(new Point(i, j));
				}
				if(input[i][j]==0) {
					emptyPlace += 1;
				}
			}
		}
		
		dfs(0, 0);
		
		if(anw==Integer.MAX_VALUE) anw = -1;
		System.out.println(anw);
	}
	private static void dfs(int idx,int cnt) {
		if(cnt==M) {
//			System.out.print(zzzzz++ + "\t");
//			System.out.println(Arrays.toString(arr));
			Queue<Point>q = new LinkedList<Point>();
			for(int i=0; i<arr.length; ++i) {
				q.add(new Point(virus.get(arr[i]).x, virus.get(arr[i]).y));
//				System.out.println(virus.get(arr[i]).x+","+virus.get(arr[i]).y);
			}
//			System.out.println("=======");
			
			go(q);
			return;
		}
		
		for(int i=idx; i<virus.size(); ++i) {
			arr[cnt] = i;
			dfs(i+1, cnt+1);
		}
	}
	private static void go(Queue<Point> q) {
//		int res = 0;
		int total_time = 0;
		int infect =0;
		int[][] temp = new int[N][N];
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				temp[i][j] = -1;
			}
		}
		
		for(int i=0; i<arr.length; ++i) {
			temp[virus.get(arr[i]).x][virus.get(arr[i]).y] = 0;
		}
		
		while(!q.isEmpty()) {
			int x = q.peek().x;
			int y = q.poll().y;
			
			for(int k=0; k<4; ++k) {
				int nX = x + dx[k];
				int nY = y + dy[k];
				
				if(0<=nX && nX < N && 0<=nY && nY < N) {
					if(input[nX][nY]==1) continue;
					if(temp[nX][nY]==-1) {
						q.offer(new Point(nX, nY));
						temp[nX][nY] = temp[x][y] + 1;
						if(input[nX][nY]==0) {
							infect += 1;
							total_time = temp[nX][nY];
						}
					}
					
				}
			}
		}
		
		if(infect==emptyPlace) {
			if(anw > total_time)
				anw = total_time;
		}
		/*
		if(check(temp)) {
			if(cnt < anw) {
				anw = cnt;
			}
		}*/
		/*
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				System.out.print(temp[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println(res);
		
		System.out.println("======================");
		*/
		
	}
	private static boolean check(boolean[][] temp) {
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				if(input[i][j]==0 && !temp[i][j])
					return false;
			}
		}
		
		return true;
	}
}//end of class
