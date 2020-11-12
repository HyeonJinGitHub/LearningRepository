import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class Main{
	private static int N;
	private static int K;
	private static int[][] input;
	private static int a;
	private static int b;
	private static int[] w;
	private static int[] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		input = new int[N+1][K+1];
		
		w = new int[N+1];
		v = new int[N+1];
		
		for(int i=1; i<=N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			w[i] = Integer.parseInt(st.nextToken());
			v[i] = Integer.parseInt(st.nextToken());
		}
		
		
		
		for(int i=1; i<=N; ++i) {
			for(int j=1; j<=K; ++j) {
				input[i][j] = input[i-1][j];
				
				if(j-w[i]>=0) {
					input[i][j] = Math.max(v[i]+input[i-1][j-w[i]], input[i-1][j]);
				}
			}
		}
		
		System.out.println(input[N][K]);
	}

}
