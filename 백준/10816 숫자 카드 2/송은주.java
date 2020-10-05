import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main{
	private static int N;
	private static int M;

	public static void main(String[] args) throws NumberFormatException, IOException {
		HashMap<Integer, Integer>map = new HashMap<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; ++i) {
			int temp = Integer.parseInt(st.nextToken());
			map.put(temp, map.getOrDefault(temp, 0)+1);
		}
		
		M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine(), " ");
//		System.out.println(st.nextToken());
		
		for(int i=0; i<M; ++i) {
			int temp = Integer.parseInt(st.nextToken());
			if(map.containsKey(temp)) {
				sb.append(map.get(temp)).append(' ');
			}else sb.append("0 ");
		}
		
		
		System.out.println(sb.toString());
		
	}
}
