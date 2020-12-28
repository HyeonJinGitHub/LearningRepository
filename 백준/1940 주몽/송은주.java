import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	private static int[] input;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		input = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; ++i) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		int anw = 0;
		for(int i=0; i<N; ++i) {
			for(int j=i+1; j<N; ++j) {
				if(input[i]+input[j]==M) anw++;
			}
		}
		
		System.out.println(anw);
	}
}
