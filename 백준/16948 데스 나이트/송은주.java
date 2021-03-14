import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main{
	private static int[][] input;
	private static boolean[][] visited;
	private static int[] dr = {-2, -2, 0, 0, 2, 2};
	private static int[] dc = {-1, 1, -2, 2, -1, 1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		input = new int[N][N];
		visited = new boolean[N][N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int r1 = Integer.parseInt(st.nextToken());
		int c1 = Integer.parseInt(st.nextToken());
		
		int r2 = Integer.parseInt(st.nextToken());
		int c2 = Integer.parseInt(st.nextToken());
		
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r1, c1});
		visited[r1][c1] = true;
		
		while(q.size()>0) {
			int[] temp = q.poll();
			int r = temp[0];
			int c = temp[1];
			
			if(r==r2 && c==c2) {
				System.out.println(input[r][c]);
				return;
			}
			
			for(int k=0; k<dr.length; ++k) {
				int nR = r + dr[k];
				int nC = c + dc[k];
				
				if(0<=nR && nR < N && 0<=nC && nC < N) {
					if(!visited[nR][nC]) {
						visited[nR][nC] = true;
						input[nR][nC] = input[r][c] + 1;
						q.offer(new int[] {nR, nC});
					}
				}
			}
			
		}
		System.out.println(-1);
		return;
		
	}
}
