import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	private static int N;
	private static int M;
	private static int K;
	private static final int inf = 1000000;
	private static ArrayList<int[]>[] arr;
	private static int[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList[N+1];
		for(int i=1; i<=N; ++i) {
			arr[i] = new ArrayList<int[]>();
		}
		
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int n =  Integer.parseInt(st.nextToken());
			int m =  Integer.parseInt(st.nextToken());
			
			arr[n].add(new int[] {m, 1});
		}
		dijkstra(X);
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		for(int i=1; i<=N; ++i) {
			if(i==X) continue;
			if(visited[i]==K) {
				flag = true;
				sb.append(i).append('\n');
			}
		}
		if(!flag) System.out.println(-1);
		else System.out.print(sb);
		
	}

	private static void dijkstra(int start) {
		dijkstra(start, -1);
	}

	private static void dijkstra(int start, int dest) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		
		visited = new int[N+1];
		for(int i=1; i<=N; ++i) {
			visited[i] = inf;
		}
		
		visited[start] = 0;
		pq.offer(new int[] {start, 0});
		
		while(pq.size()>0) {
			int[] temp = pq.poll();
			if(temp[0]==dest) break;
			
			for(int i=0; i<arr[temp[0]].size(); ++i) {
				int[] next = arr[temp[0]].get(i).clone();
				
				next[1] += temp[1];
				
				if(next[1] < visited[next[0]]) {
					visited[next[0]] = next[1];
					pq.offer(next);
				}
			}
		}
	}
}
