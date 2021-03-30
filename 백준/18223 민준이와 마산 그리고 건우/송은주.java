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
	private static int P;
	private static ArrayList<int[]>[] arr;
	private static final int INF = 200_000_000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		arr = new ArrayList[V+1];
		
		for(int i=1; i<=V; ++i) {
			arr[i] = new ArrayList<int[]>();
		}
		
		for(int i=0; i<E; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			arr[a].add(new int[] {b, c});
			arr[b].add(new int[] {a, c});
		}
		
		
		int[] shortCut = dijkstra(1);
		int cut = shortCut[V];
		
		int gunWoo = 0;
		int[] temp = dijkstra(1, P);
		gunWoo += temp[P];
		temp = dijkstra(P, V);
		gunWoo += temp[V];
		
		
		if(gunWoo==cut) {
			System.out.println("SAVE HIM");
		}else System.out.println("GOOD BYE");
		
	}
	private static int[] dijkstra(int start) {
		return dijkstra(start, -1);
	}
	private static int[] dijkstra(int start, int end) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
			
		});
		
		
		int[] visited = new int[V+1];
		for(int i=1; i<=V; ++i) {
			visited[i] = INF;
		}
		
		visited[start] = 0;
		pq.add(new int[] {start, 0});
		
		while(!pq.isEmpty()) {
			int[] curr = pq.poll();
			
			if(curr[0]==end) break;
			
			for(int i=0; i<arr[curr[0]].size(); ++i) {
				int[] next = arr[curr[0]].get(i).clone();
				
				next[1] += curr[1];
				
				if(next[1] < visited[next[0]]) {
					visited[next[0]] = next[1];
					pq.add(next);
				}
			}
			
			
		}
		
		return visited;
	}
}
