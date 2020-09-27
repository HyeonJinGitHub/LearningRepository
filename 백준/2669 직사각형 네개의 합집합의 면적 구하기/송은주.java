import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[][] arr = new boolean[101][101];
		for(int i=0; i<4; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			

			for(int k=a; k<c; ++k) {
				for(int l=b; l<d; ++l) {
					arr[k][l] = true;
				}
			}
		

		int cnt = 0;
		for(int i=0; i<100; ++i) {
			for(int j=0; j<100; ++j) {
				if(arr[i][j]) cnt++;
			}
		}
		
		System.out.println(cnt);
	}
}
