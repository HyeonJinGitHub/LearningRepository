import java.util.Scanner;

public class Main{
	private static int N;
	private static int anw;
	private static long memoi[][][];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		memoi = new long[N+1][3][2]; //3번째 인자는 지각 총 갯수, 2번째 인자는 결석
		
		StringBuilder sb = new StringBuilder();
		
		long anw = go(0, 0, 0);
		System.out.println(anw);
	}
	private static long go(int now, int a, int l) {
		if(l>=2) return 0L;
		if(a>=3) return 0L;
		
		if(now==N) return 1L;
		
		if(memoi[now][a][l]!=0) return memoi[now][a][l];
		
		
		int sum = 0;
		sum += go(now+1, 0, l);
		sum += go(now+1, a+1, l);
		sum += go(now+1, 0, l+1);
		
		return memoi[now][a][l] = (sum%1_000_000); 
		
	}
}
