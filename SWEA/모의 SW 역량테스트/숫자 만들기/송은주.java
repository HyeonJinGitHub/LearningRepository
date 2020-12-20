import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution{
	private static int N;
	private static int min;
	private static int max;
	private static int[] num;
	private static int[] arr;
	private static int[] operator;
	private static int[] count;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=TC; ++tc) {
			N = Integer.parseInt(br.readLine().trim());
			
			operator = new int[4];
			num = new int[N];
			arr = new int[N-1];
			
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<4; ++i) {
				operator[i] = Integer.parseInt(st.nextToken());
				
			}

			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N; ++i) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			
			go(0);
			
			sb.append('#').append(tc).append(' ').append(max-min).append('\n');
		}
		
		System.out.println(sb);
	}
	private static void go(int cnt) {
		if(cnt==N-1) {
			count = new int[4];
//			System.out.println(Arrays.toString(arr));
			for(int i=0; i<N-1; ++i) {
				count[arr[i]]++;
			}
//			System.out.println("냐오!");
//			System.out.println(Arrays.toString(count));
//			System.out.println(Arrays.toString(operator));
			for(int i=0; i<4; ++i) {
				if(count[i]!=operator[i]) return;
			}
			simulate();
			return;
			
		}
		
		for(int i=0; i<4; ++i) {
			arr[cnt] = i;
			go(cnt+1);
		}
		
	}
	private static void simulate() {
		int res = num[0];
		for(int i=0; i<N-1; ++i) {
			switch(arr[i]) {
			case 0:
				res += num[i+1];
				break;
			case 1:
				res -= num[i+1];
				break;
			case 2:
				res *= num[i+1];
				break;
			case 3:
				res /= num[i+1];
				break;
			}
		}
		
		if(res > max) {
			max = res;
		}
		
		if(res < min) {
			min = res;
		}
		
		return;
		
	}
}
