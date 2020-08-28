import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int M;
	private static String[] input;
	private static int[][][] visited;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		input = new String[N];
		for(int i=0; i<N; ++i) {
			input[i] = br.readLine();
		}
		
		visited = new int[N][M][2];
		
		visited[0][0][0] = 1;
		
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0, 0, 0}); //x, y, (뚫었나 안 뚫었나 여부)
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int x = now[0];
			int y = now[1];
			int flag = now[2];
			
			for(int k=0; k<4; ++k) {
				int nX = x + dx[k];
				int nY = y + dy[k];
				
				if(0<=nX && nX < N && 0<= nY && nY < M) {
					if(input[nX].charAt(nY)=='0' && visited[nX][nY][flag]==0) {
						visited[nX][nY][flag] = visited[x][y][flag] + 1;
						q.offer(new int[] {nX, nY, flag});
					}
					
					if(input[nX].charAt(nY)=='1' && flag==0 && visited[nX][nY][flag]==0) {
						visited[nX][nY][1] = visited[x][y][flag]+1;
						q.offer(new int[] {nX, nY, 1});
					}
				}
				
			}
		}
		
//		System.out.println("오잉"+visited[N-1][M-1][0] +","+visited[N-1][M-1][1]);
		int result = -1;
		if(visited[N-1][M-1][0]!=0 && visited[N-1][M-1][1]!=0) {
			result = Math.min(visited[N-1][M-1][0], visited[N-1][M-1][1]);
		}else if(visited[N-1][M-1][0]==0 && visited[N-1][M-1][1]!=0) {
			result = visited[N-1][M-1][1];
		}else if(visited[N-1][M-1][1]==0 && visited[N-1][M-1][0]!=0) {
			result = visited[N-1][M-1][0];
		}
		
		System.out.println(result);
		
	}
}//end of class
