import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	private static int V;
	private static int E;
	private static final int inf = 10000000;
	private static ArrayList<int[]>[] arr;
	private static int[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList[V+1];
		
		for(int i=1; i<=V; ++i) {
			arr[i] = new ArrayList<int[]>();
		}
		
		int start = Integer.parseInt(br.readLine());
		
		for(int i=0; i<E; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			arr[from].add(new int[] {to, dist});
		}
		
		dijkstra(start);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=V; ++i) {
			if(i==start) sb.append("0\n");
			else if(visited[i]==inf) sb.append("INF\n");
			else sb.append(visited[i]).append('\n');
		}
		
		System.out.print(sb);
		
	}

	private static void dijkstra(int start) {
		dijkstra(start, -1);
	}

	private static void dijkstra(int start, int end) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		
		visited = new int[V+1];
		
		for(int i=1; i<=V; ++i) {
			visited[i] = inf;
		}
		
		visited[start] = 0;
		pq.offer(new int[] {start, 0});
		
		while(pq.size()>0) {
			int[] cur = pq.poll();
			
			if(cur[0]==end) break;
			
			for(int i=0; i<arr[cur[0]].size(); ++i) {
				int[] next = arr[cur[0]].get(i).clone();
				next[1] += cur[1];
				if(next[1] < visited[next[0]]) {
					visited[next[0]] = next[1];
					pq.offer(next);
				}
			}
			
//			System.out.println(Arrays.toString(visited));
		}
	}

}
