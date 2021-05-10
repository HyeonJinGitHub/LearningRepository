import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{

	private static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int k = Integer.parseInt(st.nextToken());
			
			int[] arr = new int[k];
			double avg = 0.0;
			for(int j=0; j<k; ++j) {
				arr[j] = Integer.parseInt(st.nextToken());
				avg += (double)arr[j];
			}
			
			avg /= (double)k;
			
			int anw = 0;
			for(int j=0; j<k; ++j) {
				if(avg < (double)arr[j]) {
					anw++;
				}
			}
			
			sb.append(String.format("%.3f", (double)anw/(double)k*100.0)).append("%\n");
			
		}
		System.out.print(sb);
	}
}
