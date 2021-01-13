import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	private static int N;
	private static int M;
	private static final int inf = 1000000;
	private static int[][] input;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		input = new int[N+1][N+1];
		for(int i=1; i<=N; ++i) {
			for(int j=1; j<=N; ++j) {
				if(i==j) continue;
				input[i][j] = inf;
			}
		}
		
		for(int i=0; i<M; ++i){
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			input[from][to] = 1;
			input[to][from] = 1;
		}
		
		for(int k=1; k<=N; ++k) {
			for(int i=1; i<=N; ++i) {
				for(int j=1; j<=N; ++j) {
					if(input[i][j] > input[i][k] + input[k][j])
						input[i][j] = input[i][k] + input[k][j];
				}
			}
		}
		
//		print();
		
		int min = Integer.MAX_VALUE;
		int anw = -1;
		for(int i=N; i>=1; --i) {
			int sum = 0;
			for(int j=1; j<=N; ++j) {
				sum += input[i][j];
			}
//			System.out.println(sum+"?");
			if(sum <= min) {
				min = sum;
				anw = i;
//				System.out.println(i+"냐옹");
			}
		}
		
		System.out.println(anw);
		
	}

	private static void print() {
		for(int i=1; i<=N; ++i) {
			for(int j=1; j<=N; ++j) {
				System.out.print(input[i][j] + "\t");
			}
			System.out.println();
		}
		
	}
}
