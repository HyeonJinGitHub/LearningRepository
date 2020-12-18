import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main{
	private static int N;
	private static int M;
	private static char[][] input;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};	
	private static ArrayList<int[]> arr = new ArrayList<>();
	private static Queue<int[]> q = new LinkedList<>();
	private static boolean[] isSelected;
	private static int[] per;
	private static int min;
	private static int sX, sY, eX, eY;
	private static int lasteX, lasteY;
	private static int lastsX, lastsY;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		input = new char[N][M];
		
		min = Integer.MAX_VALUE;
		
		for(int i=0; i<N; ++i) {
			String s = br.readLine();
			for(int j=0; j<M; ++j) {
				input[i][j] = s.charAt(j);
				if(input[i][j]=='X') {
					arr.add(new int[] {i, j});
				}
				else if(input[i][j]=='S') {
					lastsX = i; lastsY = j;
				}
				else if(input[i][j]=='E') {
					lasteX = i; lasteY = j;
				}
			}
		}
		
		isSelected = new boolean[arr.size()];
		per = new int[arr.size()];
		
		go(0);
		
		System.out.println(min);
	}

	private static void go(int cnt) {
		if(cnt==per.length) {
//			System.out.println(Arrays.toString(per));
			
			for(int i=0; i<per.length; ++i) {
				int[] temp = arr.get(per[i]);
				input[temp[0]][temp[1]] = (char)(i+'0');
			}
			
			int res = simulate();
			
			min = Math.min(res, min);
			return;
		}
		
		for(int i=0; i<arr.size(); ++i) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			per[cnt] = i;
			go(cnt+1);
			isSelected[i] = false;
		}

	}

	private static int simulate() {
		int res = 0;
		sX = lastsX;
		sY = lastsY;
		if(arr.size()==0) {
			eX = lasteX;
			eY = lasteY;
		}else {
			eX = arr.get(per[0])[0];
			eY = arr.get(per[0])[1];
		}
		
		for(int i=0; i<per.length+1; ++i) {
			q.clear();
			int[][] visited = new int[N][M];
			if(i!=0) {
				sX = arr.get(per[i-1])[0];
				sY = arr.get(per[i-1])[1];
				if(i==per.length) {
					eX = lasteX;
					eY = lasteY;
				}else {
					eX = arr.get(per[i])[0];
					eY = arr.get(per[i])[1];
				}
			}
			
//			System.out.println("시작점"+sX+","+sY+"도착점"+eX+","+eY);
			
			
			q.offer(new int[] {sX, sY});
			
			while(q.size()>0) {
				int[] temp = q.poll();
				int x = temp[0];
				int y = temp[1];
				
				if(x==eX && y==eY) {
//					System.out.println("도착"+visited[eX][eY]);
					res += visited[eX][eY];
					break;
				}
				for(int k=0; k<4; ++k) {
					int nX = x + dx[k];
					int nY = y + dy[k];
					
					if(0<=nX && nX < N &&0<=nY && nY < M) {
						if(visited[nX][nY]!=0) continue;
						if(input[nX][nY]=='#') continue;
						visited[nX][nY] = visited[x][y] + 1;
						q.offer(new int[] {nX, nY});
					}
				}
			}
//			System.out.println("중간res"+res);
		}
//		System.out.println(res+"냐옹");
		return res;
	}
}
