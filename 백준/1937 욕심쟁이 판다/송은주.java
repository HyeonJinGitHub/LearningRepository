import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int[][] input;
	private static int[][] d; //d[i][j] => 판다가 i,j에 있을때 최대일수로 살수잇는 정보가 저장
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		input = new int[N][N];
		d = new int[N][N];
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; ++j) {
				input[i][j] = Integer.parseInt(st.nextToken());
//				d[i][j] = 0;
			}
		}
		int anw = -1;
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				d[i][j] = dp(i, j);
				if(anw < d[i][j]) anw = d[i][j];
			}
		}
		
		System.out.println(anw);
		/*
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				System.out.print(d[i][j] + "\t");
			}
			System.out.println();
		}
		*/
	}

	private static int dp(int i, int j) {
		if(d[i][j]!=0) return d[i][j];
		d[i][j]=1;
		
		for(int k=0; k<4; ++k) {
			int nX = i + dx[k];
			int nY = j + dy[k];
			
			if(0<=nX && nX < N && 0<= nY && nY < N) {
				if(input[nX][nY] > input[i][j]) {
					d[i][j] = d[i][j]>dp(nX, nY)+1?d[i][j]:dp(nX, nY)+1;
				}
			}
		}
		return d[i][j];
	}
}//end of class
