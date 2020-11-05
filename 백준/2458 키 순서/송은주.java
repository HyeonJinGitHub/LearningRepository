import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	private static int N;
	private static int M;
	private static boolean[][] input;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken())+1;
		M = Integer.parseInt(st.nextToken());
		
		input = new boolean[N][N];
		
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			input[a][b] = true;
		}
		
		for(int k=1; k<N; ++k) {
			for(int i=1; i<N; ++i) {
				for(int j=1; j<N; ++j) {
					if(input[i][k] && input[k][j]) {
						input[i][j] = true;
					}
				}
			}
		}
		
		int anw = 0;
		for(int i=1; i<N; ++i) {
			boolean flag = false;
			for(int j=1; j<N; ++j) {
				if(i==j) continue;
				if(!input[i][j] && !input[j][i]) {
					flag = true;
					break;
				}
			}
			
			if(!flag) anw++;
		}
		
		System.out.println(anw);
	}
}
