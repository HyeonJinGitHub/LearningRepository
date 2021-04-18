import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution{
	private static int TC;
	private static int N;
	private static int M;
	private static int K;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};
	private static PriorityQueue<int[]> pq;
	private static Queue<int[]> q;
	private static int[][] input;
	private static boolean[][] visited;
	private static final int NUM = 400;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		// x, y, start, end, state
		TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[4] - o1[4];
			}
		});
		q = new LinkedList<>();
		input = new int[1001][1001];
		visited = new boolean[1001][1001];
		for(int tc = 1; tc<=TC; ++tc) {
			init();
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<N; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<M; ++j) {
					int state = Integer.parseInt(st.nextToken());
					if(state>0) {
						input[i+NUM][j+NUM] = state;
						visited[i+NUM][j+NUM] = true;
						// x, y, start, end, state
						q.add(new int[] {i+NUM, j+NUM, state, state*2, state});
					}
				}
			}
			
			//q에는 활성 비활성의 세포가 다 들어감
			//pq에는 활성화 됐을때 넘기는 것만 들어감
			//아 근데 pq로 값이 큰 것부터 넣었으니까 그냥 컸을때 상관X하고 넣기 가능
			for(int time = 1; time<=K; ++time) {
//				print();
				go(time);
				bfs(time);
			}
			
			sb.append('#').append(tc).append(' ').append(q.size()).append('\n');
		}

		System.out.print(sb);
	}
	private static void print() {
		for (int i = 400 - 20 ; i < 400 + 50 ; i++) {
			for (int j = 400 - 20; j < 400 + 50; j++) {
				System.out.format("%2c", input[i][j] == 0 ? ' ' : input[i][j] + '0');
			}
			System.out.println();
		}
		System.out.println();
	}
	
	// x, y, start, end, state
	private static void bfs(int time) {
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			
			if(time < now[3]) {
				q.add(now);
			}
			
			for(int i=0; i<4; ++i) {
				int nX = now[0] + dx[i];
				int nY = now[1] + dy[i];
				
				if(!visited[nX][nY]) {
					visited[nX][nY] = true;
					input[nX][nY] = now[4];
					
					//시간으로기준을 정하기때문에 start와 end에 time을 추가해줘야 한다..
					q.add(new int[] {nX, nY, now[4]+time, time+now[4]*2, now[4]});
				}
			}
		}
		
	}
	// x, y, start, end, state
	private static void go(int time) {
		int qSize = q.size();
		
		for(int i=0; i<qSize; ++i) {
			int[] now = q.poll();
			
			//시간이 start보다 적을때는 아직 활성화가 안된거짓
			if(time <= now[2]) {
				q.add(now);
			//시간이 start+1햇을때랑 맞으면 활성화 시킨것
			}else if(time==now[2]+1) {
				pq.add(now);
			//시간이 그 사이에있으면 그냥 넣으십숑
			}else if(now[2] < time && time < now[3]) {
				q.add(now);
			}
		}
	}
	private static void init() {
		pq.clear();
		q.clear();
		for(int i=0; i<input.length; ++i) {
			for(int j=0; j<input[0].length; ++j) {
				input[i][j] = 0;
				visited[i][j] = false;
			}
		}
		
	}
}
