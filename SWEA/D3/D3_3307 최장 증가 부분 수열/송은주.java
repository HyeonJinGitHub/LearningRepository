import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=testCase; ++tc) {
			int N = Integer.parseInt(br.readLine().trim());
			int[] arr = new int[N];
			int[] lis = new int[N];
			
			int max = 0;
			st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<N; ++i) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=0; i<N; ++i) {
				lis[i] = 1; //자신만으로 lis구성햇을떄 길이 1
				
				//i앞에 있는 원소들과 비교
				for(int j=0; j<i; ++j) {
					//앞쪽 원소보다 큰 경우
					if(arr[j]<arr[i] && lis[i] < lis[j]+1)
						lis[i] = lis[j]+1;
				}
				//현 원소의 lis값과 전체 최대값하고 비교하여 최댓값갱신
				if(max<lis[i]) max = lis[i];
			}
			
			System.out.println("#" + tc + " " + max);
			
		}
	}//end of main
	
}//end of class
