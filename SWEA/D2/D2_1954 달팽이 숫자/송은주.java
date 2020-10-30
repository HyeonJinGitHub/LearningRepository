import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] output = new int [10][10];
		
		int tc = sc.nextInt();
		
		for(int TC = 1; TC <= tc ; ++TC) {
			int N = sc.nextInt();
			
			int M = N;
			if(M%2==1) M+=1;
			
			int num = 1;
			for(int i=0; i<=M/2; ++i) { //전체 루프 도는 횟슈
				int n=i, m=i;
				
				int k = N-(i*2)-1;
				
				if(k==0) {
					output[n][m] = num;
				}
				
				for(int j=0; j<k; ++j) {
					output[n][m++] = num++;
				}
				
				for(int j=0; j<k; ++j) {
					output[n++][m] = num++;
				}
				
				for(int j=0; j<k; ++j) {
					output[n][m--] = num++;
				}
				
				for(int j=0; j<k; ++j) {
					output[n--][m] = num++;
				}
			} //end of execute
			
			
			System.out.println("#"+TC);
			for(int i=0; i<N; ++i) {
				for(int j=0; j<N; ++j) {
					System.out.print(output[i][j]+" ");
				}
				System.out.println();
			}
			
		}
		sc.close();
	}//end of main
}//end of class
