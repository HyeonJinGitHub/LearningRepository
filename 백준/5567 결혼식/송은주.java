import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	private static int n;
	private static int m;
	private static ArrayList<ArrayList<Integer>> arr;
	private static int[] visited;
	private static int anw = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		arr = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<n+1; ++i) {
			arr.add(new ArrayList<Integer>());
		}
		for(int i=0; i<m; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			arr.get(from).add(to);
			arr.get(to).add(from);
		}

		bfs(1);

		System.out.println(anw);
	}

	private static void bfs(int i) {
		Queue<Integer>q = new LinkedList<>();
		q.offer(i);
		visited = new int[n+1];
		visited[i] = 1;
		
		while(!q.isEmpty()) {
			int x = q.poll();
			
			for(int k=0; k<arr.get(x).size(); ++k) {
				int nX = arr.get(x).get(k);
				if(visited[nX]==0) {
					visited[nX] = visited[x] + 1;
					if(visited[nX]==2 || visited[nX]==3) anw += 1;
					q.offer(nX);
				}
			}
			
		}
		
	}
}//end of mainclass
