import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	private static int n;
	private static int max = Integer.MIN_VALUE;
	private static int[] naegu;
	private static int[] muge;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		naegu = new int[n];
		muge = new int[n];
		
		for(int i=0; i<n; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			naegu[i] = Integer.parseInt(st.nextToken());
			muge[i] = Integer.parseInt(st.nextToken());
		}
		
		go(0, 0);
		System.out.println(max);
	}

	private static void go(int now, int result) {
//		print();
//		System.out.println("now=>"+now+"result=>"+result);
		if(now==n) {
//			System.out.println(result+"냥");
			if(max < result) {
				max = result;
			}
			return;
		}
		if(naegu[now]<=0) {
			go(now+1, result);
			return;
		}
		
		boolean flag = false;
		for(int i=0; i<n; ++i) {
			if(now==i) continue;
			
			if(naegu[i]>0) {
				flag = true;
//				System.out.println(now+"로"+i+"번째 계란 침");
				naegu[now] -= muge[i];
				naegu[i] -= muge[now];
				if(naegu[now] <= 0) result++;
				if(naegu[i] <= 0) result++;
				
				go(now+1, result);
				if(naegu[now] <= 0) result--;
				if(naegu[i] <= 0) result--;
				naegu[now] += muge[i];
				naegu[i] += muge[now];
			}
			
		}
		if(!flag) {
			go(now+1, result);
		}
	}

	private static void print() {
		System.out.println("내구도");
		System.out.println(Arrays.toString(naegu));
		System.out.println("무게");
		System.out.println(Arrays.toString(muge));
		System.out.println("============");
	}
}
