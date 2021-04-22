import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
	private static int N;
	private static int M;
	private static int R;
	private static int[][] input;
	private static int[] dx = {1, 0, -1, 0};
	private static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		input = new int[N][M];
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; ++j) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int L = Math.min(N, M)/2;
		N+=1; M+=1;
		for(int i=0; i<L; ++i) {
			N-=1;
			M-=1;
			int x = i;
			int y = i;
			int dir = 0;
			
			ArrayList<Integer> arr = new ArrayList<>();
			arr.add(input[x][y]);
			
			while(true) {
				int nX = x + dx[dir];
				int nY = y + dy[dir];
				
				if(nX==i && nY==i) break;
				if(0+i<=nX && nX < N && 0+i<=nY && nY <M) {
					arr.add(input[nX][nY]);
					x = nX; y = nY;
				}else {
					dir = (dir+1)%4;
				}
				
			}
			int r = R % arr.size();
			
			ArrayList<Integer> newOne = new ArrayList<>();
			for(int a=arr.size()-r; a<arr.size(); ++a) {
				newOne.add(arr.get(a));
			}
			
			for(int a=0; a<arr.size()-r; ++a) {
				newOne.add(arr.get(a));
			}
			
			x = i;
			y = i;
			int idx = 0;
			dir = 0;
			
			input[x][y] = newOne.get(idx++);
			while(true) {
				int nX = x + dx[dir];
				int nY = y + dy[dir];
				
				if(nX==i && nY==i) break;
				if(0+i<=nX && nX < N && 0+i<=nY && nY <M) {
					input[nX][nY] = newOne.get(idx++);
					x = nX; y = nY;
				}else {
					dir = (dir+1)%4;
				}
				
			}
			
		}
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<input.length; ++i) {
			for(int j=0; j<input[0].length; ++j) {
				sb.append(input[i][j]).append(' ');
			}
			sb.append('\n');
		}
		
		System.out.print(sb);
		
	}
}
