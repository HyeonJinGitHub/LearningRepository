import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int n = N;
		
		int cnt = 0;
		while(true) {
			if(0<= N && N <10) {
				N = N*10 + N;
			}else {
				int k = (N/10 + N%10);
				if(k>=10) {
					k = k%10;
				}
				N = 10*(N%10) + k;
			}
//			System.out.println(N+"?");
			cnt++;
			if(N==n) break;
		}
		
		System.out.println(cnt);
	}
}
