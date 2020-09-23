import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	private static int[][] cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc<=TC; ++tc) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			boolean[] flag = new boolean[2];
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			cnt = new int[N][3]; //W B R
			
			for(int i=0; i<N; ++i) {
				String s = br.readLine();
				for(int j=0; j<s.length(); ++j) {
					switch(s.charAt(j)) {
					case 'W': cnt[i][0]++; break;
					case 'B': cnt[i][1]++; break;
					case 'R': cnt[i][2]++; break;
					}
				}
			}
			
			
			// W B R 순서로 칠하는 경우의 수, 조합, 2중 for문으로 구현, 
			//각 경우의 최솟값 갱신해서 출력, i, j 결정
			
			//0행 <= W < i행
			//i행 <= B < j행
			//j행 <= R < N행
			int minCnt = Integer.MAX_VALUE;
			for(int i=1; i<=N-2; ++i) { //미만이기 때문에 이 숫자가 포함되어야 함(그 전까지 계산을 해야 하므로)
				int w = cntChar(0, i, 'W'); //이미 밑에 포문에선 계산이 되었을테니까 얘는 한 번만 해도 됨
				for(int j=i+1; j<=N-1; ++j) {
					int b = cntChar(i, j, 'B');
					int r = cntChar(j, N, 'R');
					int sum = w+b+r;
					
					if(minCnt > sum) {
						minCnt = sum;
					}
				}
			}
			
			System.out.println("#" + tc + " " + minCnt);
			
			
		}//end of for testCase
	}//end of main

	/**s행 이상 e행 미만의 범위에서 c글자로 변경 시 바꿔야되는 글자의 개수 리턴*/
	private static int cntChar(int stt, int end, char c) {
		int k = 0; //W B R
		for(int i=stt; i<end; ++i) {
			switch(c) {
			case 'W': k += cnt[i][1] + cnt[i][2]; break;
			case 'B': k += cnt[i][0] + cnt[i][2]; break;
			case 'R': k += cnt[i][0] + cnt[i][1]; break;
			}
		}
		
		
		return k;
	}
}
