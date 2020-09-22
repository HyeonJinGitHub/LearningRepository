import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int D = sc.nextInt();
		int K = sc.nextInt();
		
		ArrayList<Integer>A = new ArrayList<Integer>();
		ArrayList<Integer>B = new ArrayList<Integer>();
		A.add(1);
		A.add(0);
		A.add(1);
		
		B.add(0);
		B.add(1);
		B.add(1);
		for(int i=3; i<=D; ++i) {
			A.add(A.get(i-1)+A.get(i-2));
			B.add(B.get(i-1)+B.get(i-2));
		}

		D-=1;
		for(int a=0; a<100000; ++a) {
			for(int b=0; b<100000; ++b) {
				if((a*A.get(D) + b*B.get(D)) ==K) {
					System.out.println(a);
					System.out.println(b);
					return;
				}else if((a*A.get(D) + b*B.get(D)) > K) {
					break;
				}
			}
		}
		
		
		
	}
}//end of class
