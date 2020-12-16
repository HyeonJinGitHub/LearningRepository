import java.util.Scanner;

class Main{
	private static int[] input;
	private static int cnt;
	private static int n;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		
		input = new int[n];
		go(0);
		
		System.out.println(cnt);
	}

	private static void go(int now) {
//		System.out.println(now);
		if(now==n) {
			cnt++;
			return;
		}
		
		for(int i=0; i<n; ++i) {
			input[now] = i;
			
			if(check(now)) {
				go(now+1);
			}
		}
		
		
	}
	//퀸은 \ / + 다 안된다.
	private static boolean check(int now) {
		for(int i=0; i<now; ++i) {
			if(input[i]==input[now]) return false; //같은 열 ??
			if((input[i]-input[now])==(now-i)) return false; // \
			if((input[now]-input[i])==(now-i)) return false; // /
		}
		
		return true;
	}
}
