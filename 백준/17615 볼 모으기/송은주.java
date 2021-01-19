import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
	private static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		String s = br.readLine();
		
		boolean flag = false;
		
		char ch = s.charAt(N-1);
		
		int r = 0;
		int b = 0;
		for(int i=N-2; i>=0; --i) {
			if(!flag && ch!=s.charAt(i)) {
				flag = true;
				if(s.charAt(i)=='B') b++;
				else r++;
				
				continue;
			}
			if(flag) {
				if(s.charAt(i)=='B') b++;
				else r++;
			}
		}
		
		ch = s.charAt(0);
		flag = false;
		int r2 = 0;
		int b2 = 0;
		for(int i=1; i<N; ++i) {
			if(!flag && ch!=s.charAt(i)) {
				flag= true;
				if(s.charAt(i)=='B') b2++;
				else r2++;
				
				continue;
			}
			if(flag) {
				if(s.charAt(i)=='B') b2++;
				else r2++;
			}
		}
		
		if(!flag) {
			System.out.println(0);
		}else {
			int temp = Math.min(r, b);
			int temp1 = Math.min(r2, b2);
			System.out.println(Math.min(temp, temp1));
		}
	}
}
