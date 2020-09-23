import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution { //DP로 풀기
	private static int[][] cnt;
	private static int[][] memoi;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc<=TC; ++tc) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			boolean[] flag = new boolean[2];
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			cnt = new int[N][3]; //W B R
			memoi = new int[N][3]; //W B R
			
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
			
			memoi[0][0] = cnt[0][1] + cnt[0][2]; //첫번쨰 라인을 W로 칠하는 최소 변경의 개수
			memoi[0][1] = 10000;//이것은 첫번쨰 칸을 B로 칠하는 방법(이런 경우는 안되니까 가장 큰 값으롳 초기화(들어올 수 있는 숫자만큼만)))
			memoi[0][2] = 10000;//이것은 첫번쨰 칸을 R로 칠하는 방법(이런 경우는 안되니까 가장 큰 값으롳 초기화)
			
			//		W		B		R
			//	0
			//	1
			//	2
			// ..
			//	N  		B에 올 수 있는 숫자: N-1의 W숫자거나 N-1의 B이거나
			//			R에 올 수 있는 숫자: N-1의 B숫자거나 N-1의 R이거나 ...??
			
			for(int i=1; i<N; ++i) {
				//야는 이 전의 화이트일수밖에 없자나??? 그래서 그이전꺼 + 새로 w칠하는거 갯수
				memoi[i][0] = memoi[i-1][0] + cnt[i][1] + cnt[i][2]; 
				//B를 칠하려면 이전라인이 W이거나 B인걸 가져오면됨 .
				//즉 memoi[i-1][0], memoi[i-1][1]중 작은거 가져오기 + 현재 라인 B로 칠하는거
				memoi[i][1] = Math.min(memoi[i-1][0], memoi[i-1][1]) + cnt[i][0] + cnt[i][2];
				
				//memoi[i][2] 도 memoi[i][1] 랑 마찬가지의 원리
				memoi[i][2] = Math.min(memoi[i-1][1], memoi[i-1][2]) + cnt[i][0] + cnt[i][1];
			}
			
			
			//마지막에  W, B, 올 수 없으니까 마지막 R로 끝나는거 출력하면 됨
			System.out.println("#" + tc + " " + memoi[N-1][2]);
		}//end of for testCase
	}//end of main

}
