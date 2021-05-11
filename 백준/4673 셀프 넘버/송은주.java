import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{

	private static int N;
	private static boolean[] checked;

	public static void main(String[] args) throws NumberFormatException, IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
//		StringTokenizer st;
		
		checked = new boolean[10002];
		
		for(int i=1; i<=10000; ++i) {
			int k = i;

			while(true) {
				k = d(k);
				if(k > 10000) break;
				if(checked[k]) break;
				checked[k] = true;
			}
		}
		
		for(int i=1; i<=10000; ++i) {
			if(!checked[i])
				sb.append(i).append('\n');
		}
		
		System.out.print(sb);
	}

	private static int d(int k) {
		int result = k;
		while(k!=0) {
			result += k%10;
			k/=10;
		}
		
		return result;
	}
}
