import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * DP
 * 길이가 N 인 앞 뒤가 1크거나 작은, 0~9 숫자가 등장하는 삐끗수의 개수 출력
 * 
 * 0으로 시작하는건 안됨.
 * 9876543210
 * 
 * 11자리면
 * 89876543210
 * 이거나
 * 98765432101 등등...
 * 
 * 가지수가 많아질텐데 나머지 연산을 해야됨 ! == 나머지 정리 적용
 * 
 * 숫자의 개수 f(N, i, 사용한숫자체크)
 * 최악의경우 : N은 범위가 100이야 i는 10개, 사용한숫자체크 1024개
 * 대략 1024000 100만 이니까 할수있음.
 * i:1~8 f(N, i) = f(N-1, i-1) + f(N-1, i+1)
 * i:0   f(N, i) = 				  f(N-1, i+1);
 * i:9   f(N, i) = f(N-1, i-1);
 */

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		final int MOD = 1_000_000_000;
		final int LIMIT = 1<<10;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		
		
		//int형 변수 하나 써서,
		//4바이트 32비트 중 밑에 10개 써서 사용 여부  알아보겠다!
		//2^10  :  1<< 10 == 1024
		int[][][] memo = new int[101][10][LIMIT]; //[자리수][마지막숫자][지금까지사용한숫자 bitmask]
		
		//자리가 한 글자인 가짓수
		for(int i=1; i<10; ++i) { //길이가 1인 수의 개수, 0으로 시작하는 숫자는 불가능하므로 0개
			memo[1][i][1<<i] = 1; //1자리 숫자를 만드는데 마지막 숫자가 i로 끝나는 녀석은 1개씩이다. [0][i]는 =0으로 해줘야 됨! 조건에 위배.
			//1<<i는 i번째 자릿수만 썼다 그런뜻!
		}
		
		for(int i=2; i<=100; ++i) { //길이가 2자리~N자리까지 계산!!
			for(int j=0; j<10; ++j) {//마지막 숫자를 j로 표현
				for(int k=0; k<LIMIT; ++k) { //비트마스크 한 값
					int temp = k|(1<<j); //이 거 100만번 하니까, 이렇게 따로 뺴서 쓰면 시간이 더 빠르다. 위의 for문 조건도 마찬가지!
					switch(j) {
					case 0:
						memo[i][j][temp] = (memo[i][j][temp] + memo[i-1][j+1][k])%MOD;
						break;
					case 9:
						//나보다 큰 걸 할수없어!
						memo[i][j][temp] = (memo[i][j][temp] + memo[i-1][j-1][k])%MOD;
						break;
					default:
						//1을 j만큼 shift해준 값을 k에 누적한다(|연산자는 하나만 1이어도 1)
						memo[i][j][temp] = ((memo[i][j][temp] + memo[i-1][j-1][k])%MOD + memo[i-1][j+1][k])%MOD;
						break;
					}
				}
			}				
		}
		//자리수는 총 N자리, 마지막 자리는 0, 1, 2, 3, ...,9 상관없어. 그리고 마지막 비트 
		//마지막은 10개의 숫자를 다 쓴거해서 더해주면 된다.+ 나머지연산
		
		for(int testCase = 1 ; testCase<=TC; ++testCase) {
			int N = Integer.parseInt(br.readLine().trim()); // 수의 길이 N(1<=N<=100)
			
			int cnt = 0;
			for(int i=0; i<10; ++i) {
				cnt = (cnt+memo[N][i][LIMIT-1])%MOD;
			}
			
			System.out.println("#" + testCase+" " + cnt);
		}
	}//end of main
}
