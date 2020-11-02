import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main{
	private static int[] visited;
	private static int from;
	private static int to;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		visited = new int[150000];
		from = Integer.parseInt(st.nextToken());
		to = Integer.parseInt(st.nextToken());
		
		Queue<Integer>q = new LinkedList<>();
		if(from==to) {
			System.out.println(0);
			return;
		}
		
		q.offer(from);
		
		while(!q.isEmpty()) {
			int k = q.poll();
			if(k==to) {
				System.out.println(visited[k]);
				return;
			}
			
			int nK = k+1;
			if(0<=nK && nK < 150000 && visited[nK]==0) {
				visited[nK] = visited[k] + 1;
				q.offer(nK);
			}
			
			nK = k-1;
			
			if(0<=nK && nK < 150000 && visited[nK]==0) {
				visited[nK] = visited[k] + 1;
				q.offer(nK);
			}
			
			nK = k*2;
			
			if(0<=nK && nK < 150000 && visited[nK]==0) {
				visited[nK] = visited[k] + 1;
				q.offer(nK);
			}
			
		}
	}
}
