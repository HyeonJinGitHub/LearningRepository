import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	private static int n;
	private static int m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		int cnt = 1;
		for(int i=0; i<n; ++i) {
			for(int j=0; j<m; ++j) {
				sb.append(cnt++).append(' ' );
			}
			sb.append('\n');
		}
		System.out.println(sb.toString());
		
	}

}
