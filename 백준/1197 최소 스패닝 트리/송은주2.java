import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	private static int V;
	private static int E;
	private static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		parents = new int[V+1];
		for(int i=0; i<parents.length; ++i) {
			parents[i] = i;
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		for(int i=0; i<E; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			pq.add(new int[] {a, b, c});
		}
		
		int result = 0;
		
		for(int i=0; i<E; ++i) {
			int[] temp = pq.poll();
			
			if(findSet(temp[0])!=findSet(temp[1])) {
				union(temp[0], temp[1]);
				result += temp[2];
			}
		}
		
		
		System.out.println(result);
	}
	
	public static int findSet(int x) {
		return parents[x]!=x?parents[x]=findSet(parents[x]):parents[x];
	}
	
	public static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot!=bRoot) {
			parents[bRoot] = aRoot;
		}
	}
	
}
