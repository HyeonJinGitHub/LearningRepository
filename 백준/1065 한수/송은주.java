import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{

	private static int N;
	private static boolean[] checked;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int res = 0;
		
		for(int i=1; i<=N; ++i) {
			if(1<=i && i<=99) {
				res++;
				continue;
			}
			
			if(isValid(String.valueOf(i))) {
				res++;
				continue;
			}
			
		}
		System.out.println(res);
	}

	private static boolean isValid(String value) {
		int gap = 10;
		
		for(int j=0; j<value.length()-1; ++j) {
			int k = (value.charAt(j+1)-'0') - (value.charAt(j)-'0');
			if(k!=gap) {
				if(gap==10) {
					gap = k;
				}else return false;
			}
		}
		return true;
	}

	
}
