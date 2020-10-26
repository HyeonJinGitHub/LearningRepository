/** 우선 순위큐로 구현 */
//다익스트라 무향 유향 둘 다 가능 (음수 가중치를 갖는게 있으면 안됨)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static int E;
	private static int V;
	private static int start;
	private static final int INF = Integer.MAX_VALUE;
	static ArrayList<int[]>[] arr;
	private static int[] visited;
	
  /** 출발점만 있고 도착점은 없을 때 */
	public static void dijkstra(int stt) {
		dijkstra(stt, -1);
	}
	
  /** 출발점 도착점 둘 다 있을 때*/
	public static void dijkstra(int stt, int dest) {
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(V, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		}); 
		
		for(int i=0; i<=V; ++i) {
			visited[i] = INF;
		}
		visited[stt] = 0;
		
		pq.add(new int[] {stt, 0});
		while(!pq.isEmpty()) {
			int curr[] = pq.poll();
			
			if(curr[0] == dest) break;
			
			for(int i=0; i<arr[curr[0]].size(); ++i) {
				int next[] = arr[curr[0]].get(i).clone();
				next[1] += curr[1];
				
				if(next[1]<visited[next[0]]) {
					visited[next[0]] = next[1];
					pq.add(next);
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList[V+1];
		visited = new int[V+1];
		
		for(int i=1; i<=V; ++i) {
			arr[i] = new ArrayList<int[]>();
		}
		
		start = Integer.parseInt(br.readLine());
		for(int i=0; i<E; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
      //u에서 v로 가는 길에 가중치 w가 있다.
			arr[u].add(new int[] {v, w});
		}
		
		
		dijkstra(start);
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=V; ++i) {
			if(i==start) {
				sb.append("0\n");
				continue;
			}
			if(visited[i]==INF) {
				sb.append("INF\n");
			}
			else sb.append(visited[i]).append('\n');
			
		}
		
		System.out.print(sb.toString());
	}
}//end of Main
