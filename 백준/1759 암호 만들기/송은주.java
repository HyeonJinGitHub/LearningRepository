import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	private static int L;
	private static int C;
	private static char[] input;
	private static char[] arr;
	private static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		input = new char[C];
		arr = new char[L];
		st = new StringTokenizer(br.readLine(), " ");
		
		for(int i=0; i<C; ++i) {
			input[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(input);
		
		sb = new StringBuilder();
		go(0, 0);
		System.out.println(sb);
	}

	private static void go(int idx, int cnt) {
		if(cnt==L) {
			int ja = 0;
			int mo = 0;
			for(int i=0; i<L; ++i) {
				switch(arr[i]) {
				case 'a':
				case 'e':
				case 'i':
				case 'o':
				case 'u':
					mo++;
					break;
				default:
					ja++;
				}
			}
			if(mo>=1 && ja>=2) {
				for(int i=0; i<L; ++i) {
					sb.append(arr[i]);
				}
				sb.append('\n');					
			}
			return;
		}
		
		for(int i=idx; i<C; ++i) {
			arr[cnt] = input[i];
			go(i+1, cnt+1);
		}
		
	}
}
