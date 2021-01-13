import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	private static int N;
	private static int[][] input;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		input = new int[N][N];

		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; ++j) {
				input[i][j] = Integer.parseInt(st.nextToken());
				if(input[i][j]==0) input[i][j] = 1000;
			}
		}
		
		for(int k=0; k<N; ++k) {
			for(int i=0; i<N; ++i) {
				for(int j=0; j<N; ++j) {
//					System.out.println(i+","+j+","+k);
					if(input[i][j]>input[i][k]+input[k][j])  
						input[i][j] = input[i][k] + input[k][j];
//					System.out.println(input[i][j]);
				}
//				System.out.println("===");
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
//				sb.append(input[i][j]).append(' ');
				sb.append(input[i][j]==1000?'0':'1').append(' ');
			}
			sb.append('\n');
		}
		
		System.out.print(sb
				);
		
	}
}
