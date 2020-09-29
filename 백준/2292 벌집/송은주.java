import java.util.Scanner;

public class Main {
	public static int execute(int N) {
		if(N==1) return 1;
    //n 최대 10억
		//1은 1
		//2개 가야하는건 2~7
		//3개 가야하는건 8~19
		//4개 가야하는건 20~37 //앞이 6, 12, 18..계차 수열로 늘어남
		
		for(int anw = 2, start=2, temp=6;; start += temp,temp+=6, anw++) {
			//System.out.println(start+","+(start+temp-1));
			if(start <= N && N <= start+temp-1) {
				return anw;
			}
		}
	
		//return 0;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		System.out.println(Main.execute(N)); // 3
	}

}
