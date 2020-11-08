import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	private static int N;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};
	private static int[][] input;
	private static int[][] minTime;
	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		while(true) {
			cnt++;
			N = Integer.parseInt(br.readLine());
			if(N==0) break;
			input = new int[N][N];
			minTime = new int[N][N];
			
			for(int i=0; i<N; ++i) {
				String s = br.readLine();
				for(int j=0, index=0; j<N; ++j, index+=2) {
					input[i][j] = s.charAt(index)-'0';
				}
			}
			
			int anw = dijkstra(0, 0, N-1, N-1);
			sb.append("Problem ").append(cnt).append(": ").append(anw).append('\n');
			
			
			//print();
		}
		System.out.println(sb.toString());
	}

	private static void print() {
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				System.out.print(minTime[i][j] + "\t");
				
			}
			System.out.println();
		}
		
	}

	private static int dijkstra(int sX, int sY, int eX, int eY) {
		init();
		//{x, y, 값}
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		
		
		minTime[sX][sY] = input[sX][sY];
		pq.offer(new int[] {sX, sY, minTime[sX][sY]});
		
		while(true) {
			int[] temp = pq.poll();
			
			int x = temp[0];
			int y = temp[1];
			int cost = temp[2];
			
			
			if(x==eX && y==eY) {
				return cost;
			}
			
			for(int k=0; k<4; ++k) {
				int nX = x + dx[k];
				int nY = y + dy[k];
				
				if(0<=nX && nX < N && 0<=nY && nY < N) {
					if(minTime[nX][nY] > cost+input[nX][nY]) {
						minTime[nX][nY] = cost+input[nX][nY];
						pq.offer(new int[] {nX, nY, minTime[nX][nY]});
					}
				}
			}
		}
	}

	private static void init() {
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				minTime[i][j] = INF;
			}
		}
	}

}
