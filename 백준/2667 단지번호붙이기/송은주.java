import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

class Main{
	private static boolean[][] visited;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};
	
	private static ArrayList<Integer>result;
	private static String[] arr;
	private static int N;
	private static int group;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new String[N];
		visited = new boolean[N][N];
		result = new ArrayList<>();
		
		for(int i=0; i<N; ++i) {
			arr[i] = br.readLine();
		}
		
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				if(arr[i].charAt(j)=='1' && !visited[i][j]) {
					go(i, j);
				}
			}
		}
		
		
		System.out.println(result.size());
		Collections.sort(result);
		
		for(Integer I:result) {
			System.out.println(I);
		}
	}

	private static void go(int i, int j) {
		Queue<int[]> q = new LinkedList<>();
		
		q.offer(new int[] {i, j});
		visited[i][j] = true;
		int cnt = 0;
		
		while(!q.isEmpty()) {
			int x = q.peek()[0];
			int y = q.poll()[1];
			cnt++;
			
			for(int k=0; k<4; ++k) {
				int nX = x + dx[k];
				int nY = y + dy[k];
				
				if(0<=nX && nX < N && 0<= nY && nY < N) {
					if(!visited[nX][nY] && arr[nX].charAt(nY)=='1') {
						q.offer(new int[] {nX, nY});
						visited[nX][nY] = true;
					}
				}
			}
		}
		
		result.add(cnt);
		
	}
}
