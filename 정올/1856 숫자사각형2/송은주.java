import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	private static int N;
	private static int M;
	private static int[][] input;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		input = new int[N][M];
		int cnt=1;
		boolean flag = true;
		for(int i=0; i<N; ++i) {
			if(flag) {
				for(int j=0; j<M; ++j) {
					input[i][j] = cnt++;
				}
				flag = false;
			}else {
				for(int j=M-1; j>=0; --j) {
					input[i][j] = cnt++;
				}
				flag = true;
			}
		}
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				sb.append(input[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
		
	}
}
