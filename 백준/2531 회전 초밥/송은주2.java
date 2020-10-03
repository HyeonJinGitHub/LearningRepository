import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main{
	private static int N;
	private static int d;
	private static int k;
	private static int c;
	private static int[] visited;
	private static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		visited = new int[d+1];
		arr = new int[N];
		
		for(int i=0; i<N; ++i) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		int anw = 0;
		int cnt = 0;
		Deque<Integer>dq = new ArrayDeque<>();
		for(int i=0; i<k; ++i) {
			dq.offerLast(arr[i]);
			if(visited[arr[i]]==0) {
				visited[arr[i]]+=1;
				cnt += 1;
			}else visited[arr[i]]+=1;
			anw = Math.max(cnt,  anw);
		}
		
		for(int i=0; i<N-1; ++i) {
			dq.pollFirst();
			
			if(visited[arr[i]]!=0) {
				visited[arr[i]]-=1;
				if(visited[arr[i]]==0) cnt-=1;
			}
			
			dq.offerLast(arr[(i+k)%N]);
			
			int last = dq.peekLast();
			
			if(visited[last]==0) {
				visited[last]+=1;
				cnt += 1;
			}else visited[last] += 1;
			
			
			if(visited[c]==0) {
				anw = Math.max(anw, cnt+1);
			}else anw = Math.max(anw, cnt);
			
		}
		System.out.println(anw);
	}
}
