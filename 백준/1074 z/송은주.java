import java.util.Scanner;

public class Main {
	private static int N;
	private static int r;
	private static int c;
	private static int n;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		r = sc.nextInt();
		c = sc.nextInt();
		
		n = 1;
		for(int i=0; i<N; ++i) {
			n*=2;
		}
//		System.out.println(k);
		int x,y;
		x=y=0;
		int res = 0;
		while(n>1) {
			n/=2;
			if(r < x+n && c < y+n) {
				
			}else if(r<x+n && c >= y+n) {
				res += n*n*1;
				y += n;
			}else if(r>=x+n && c < y+n) {
				res += n*n*2;
				x += n;
			}else {
				res += n*n*3;
				x += n;
				y += n;
			}
		}
		
		System.out.println(res);
	}
}//end of class
