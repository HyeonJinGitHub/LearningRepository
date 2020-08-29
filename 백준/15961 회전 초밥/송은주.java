import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int d;
	private static int k;
	private static int c;
//	private static boolean flag;
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
		
		int cnt = 0;
		int anw = 0;
		Deque<Integer>dq = new ArrayDeque<Integer>();
		for(int i=0; i<k; ++i) {
			dq.offerLast(arr[i]);
			if(visited[arr[i]]==0) {
				visited[arr[i]]+=1;
				cnt += 1;
			}else visited[arr[i]]+=1;
			anw = max(anw, cnt);
		}
		//7 9 7 30 2 7 9 25
		//k==4면 7 9 7 들어가있음
		for(int i=0; i<N-1; ++i) {
			dq.pollFirst();
			//9 7
			if(visited[arr[i]] != 0) {
				visited[arr[i]]-=1;
				if(visited[arr[i]]==0) cnt-=1;
			}
			
			dq.offerLast(arr[(i+k)%N]); //회전초밥이 원형
			
			if(visited[arr[(i+k)%N]]==0) {
				visited[arr[(i+k)%N]]+=1;
				cnt += 1;
			}else visited[arr[(i+k)%N]]+=1;
			
			if(visited[c]==0) {
				anw = max(anw, cnt+1);
			}else {
				anw = max(anw, cnt);
			}
			
		}
		
		System.out.println(anw);
	}

	private static int max(int a, int b) {
		return a>=b?a:b;
	}
}//end of class
