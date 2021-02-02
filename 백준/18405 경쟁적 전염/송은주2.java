import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main{
	private static int N;
	private static int K;
	private static int[][] input;
	private static int S;
	private static int X;
	private static int Y;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()); // K개종류
		
		input = new int[N][N];
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; ++j) {
				input[i][j] = Integer.parseInt(st.nextToken());
				if(input[i][j]!=0) {
					pq.offer(new int[] {input[i][j], i, j});
				}
			}
		}
		
		int pqSize = pq.size();
		
		st = new StringTokenizer(br.readLine(), " ");
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		
		X -=1;
		Y -=1;
		
		Queue<int[]> q = new LinkedList<>();
		
		while(pq.size()> 0) {
			q.offer(pq.poll());
		}
		
		for(int i=0; i<S; ++i) {
			boolean[][] visited = new boolean[N][N];
			if(input[X][Y] != 0) {
				System.out.println(input[X][Y]);
				return;
			}
			int qSize = q.size();
			
			for(int j=0; j<qSize; ++j) {
				int[] cur = q.poll();
				
				for(int k=0; k<4; ++k) {
					int nX = cur[1] + dx[k];
					int nY = cur[2] + dy[k];
					
					if(0<=nX && nX < N && 0<=nY && nY < N) {
						if(!visited[nX][nY] && input[nX][nY]==0) {
							visited[nX][nY] = true;
							input[nX][nY] = cur[0];
							q.offer(new int[] {cur[0], nX, nY});
						}
					}
				}
			}
		}
		
		System.out.println(input[X][Y]);
	}

	private static void print() {
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				System.out.print(input[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("==");
		
	}
}
