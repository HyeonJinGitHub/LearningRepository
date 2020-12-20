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
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		String[] input = new String[n];
		
		for(int i=0; i<n; ++i) {
			input[i] = br.readLine();
		}
		
		int min = Integer.MAX_VALUE;
		for(int i=0; i<=n-8; ++i) {
			for(int j=0; j<=m-8; ++j) {
				int temp1 = 0;
				char ch = 'B';
				for(int a=0; a<8; ++a) {
					ch = ch=='W'?'B':'W';
					for(int b=0; b<8; ++b) {
						if(input[a+i].charAt(b+j)==ch) {
							temp1++;
						}
						ch = ch=='W'?'B':'W';
					}
				}
				
				ch = 'W';
				int temp2 = 0;
				for(int a=0; a<8; ++a) {
					ch = ch=='W'?'B':'W';
					for(int b=0; b<8; ++b) {
						if(input[a+i].charAt(b+j)==ch) {
							temp2++;
						}
						ch = ch=='W'?'B':'W';
					}
				}
				
				min = Math.min(Math.min(temp1, temp2), min);
			}
		}
		
		System.out.println(min);
	}
}
