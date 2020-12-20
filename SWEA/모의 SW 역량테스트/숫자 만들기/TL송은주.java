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
	private static ArrayList<Integer> operator = new ArrayList<>();
	private static int[] num;
	private static boolean[] isSelected;
	private static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=TC; ++tc) {
			N = Integer.parseInt(br.readLine().trim());
			
			operator.clear();
			num = new int[N];
			isSelected = new boolean[N-1];
			arr = new int[N-1];
			
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<4; ++i) {
				int k = Integer.parseInt(st.nextToken());
				
				for(int j=0; j<k; ++j) {
					operator.add(i);
				}
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
			System.out.println(Arrays.toString(arr));
			//simulate();
			return;
			
		}
		
		for(int i=0; i<N-1; ++i) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			arr[cnt] = operator.get(i);
			go(cnt+1);
			isSelected[i] = false;
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
