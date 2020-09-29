import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int F;
	private static int S;
	private static int G;
	private static int U;
	private static int D;
	private static int[] check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		F = Integer.parseInt(st.nextToken()); //총 층
		S = Integer.parseInt(st.nextToken()); //시작층
		G = Integer.parseInt(st.nextToken()); //도착층
		U = Integer.parseInt(st.nextToken()); //위로
		D = Integer.parseInt(st.nextToken()); //아래로
		
		if(S==G) {
			System.out.println(0);
			return;
		}
		go(S);
		
		
		if(check[G]==0) {
			System.out.println("use the stairs");
		}else {
			System.out.println(check[G]-1);
		}
	
	}

	private static void go(int s2) {
//		Map<Integer, Integer>m = new HashMap<Integer, Integer>();
		check = new int[F+1];
//		m.put(S, 1);
		
		check[S] = 1;
		
		Queue<Integer>q = new LinkedList<>();
		q.offer(S);
		
		while(!q.isEmpty()) {
			int k = q.poll();
//			System.out.println(k+"드러왓지롱");
			
			if(k==G) return;
			
			int next = k + U;
			if((0<next && next <= F) && check[next]==0) {
				check[next] = check[k] + 1;
				q.offer(next);
			}
			
			next = k - D;
			
			if((0<next && next <= F) && check[next]==0) {
				check[next] = check[k] + 1;
				q.offer(next);
			}
		}
		
		
	}
}
