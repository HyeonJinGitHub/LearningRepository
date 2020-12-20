import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	private static int N;
	private static int M;
	private static int[] arr;
	private static int[] target;
	private static int temp;
	private static boolean[] check;
	private static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		target = new int[N];
		for(int i=0; i<N; ++i) {
			target[i] = i+1;
		}
		arr = new int[M];
		check = new boolean[N];
		sb = new StringBuilder();
		go(0);
		System.out.println(sb.toString());
		
	}
	private static void go(int cnt) {
		if(cnt==M) {
			for(int i=0; i<M; ++i) {
				sb.append(arr[i]).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		for(int i=0; i<N; ++i) {
			if(check[i])continue;
			check[i] = true;
			arr[cnt] = target[i];
			go(cnt+1);
			check[i] = false;
		}
		
		
	}
	
}
