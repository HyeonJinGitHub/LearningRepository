import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	private static int n;
	private static int k;
	private static final int inf = 1000000;
	private static int[][] input;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		input = new int[n+1][n+1];
		
		for(int i=1; i<=n; ++i) {
			for(int j=1; j<=n; ++j) {
				if(i==j) continue;
				input[i][j] = inf; 
			}
		}
		
		for(int i=0; i<k; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			
			input[first][second] = 1;
		}
		
		for(int k=1; k<=n; ++k) {
			for(int i=1; i<=n; ++i) {
				for(int j=1; j<=n; ++j) {
					if(input[i][j] > input[i][k] + input[k][j]) {
						input[i][j] = input[i][k] + input[k][j];
					}
				}
			}
		}
		
//		print();
		int s = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<s; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			
			if(input[first][second]==inf && input[second][first]!=inf) {
				sb.append(1).append('\n');
			}else if(input[first][second]!=inf && input[second][first]==inf) {
				sb.append(-1).append('\n');
			}else if(input[first][second]==inf && input[second][first]==inf) {
				sb.append(0).append('\n');
			}
		}
		
		System.out.print(sb);
	}

	private static void print() {
		for(int i=1; i<=n; ++i) {
			for(int j=1; j<=n; ++j) {
				System.out.print(input[i][j]+"\t");
			}
			System.out.println();
		}
		
	}
}
