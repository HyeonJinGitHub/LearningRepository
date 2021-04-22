import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	private static int N;
	private static int M;
	private static int R;
	private static int[][] input;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		input = new int[N][M];
	
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; ++j) {
				input[i][j]  = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<R; ++i) {
			int temp = Integer.parseInt(st.nextToken());
			switch(temp) {
			case 1:
				input = one();
				break;
			case 2:
				input = two();
				break;
			case 3:
				input = three();
				break;
			case 4:
				input = four();
				break;
			case 5:
				input = five();
				break;
			case 6:
				input = six();
				break;
			}
			
		}
		print();
	}

	private static void print() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<input.length; ++i) {
			for(int j=0; j<input[0].length; ++j) {
				sb.append(input[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.print(sb);
//		System.out.println("===");
	}

	private static int[][] five() {
		int n = input.length;
		int m = input[0].length;
		int[][] res = new int[n][m];
		int halfX = n/2;
		int halfY = m/2;
		
		//2번 위치에 1번을 넣기
		for(int i=0; i<halfX; ++i) {
			for(int j=halfY; j<m; ++j) {
				res[i][j] = input[i][j-halfY];
			}
		}
		
		//3번 위치에 2를 넣기
		for(int i=halfX; i<n; ++i) {
			for(int j=halfY; j<m; ++j) {
				res[i][j] = input[i-halfX][j];
			}
		}
		
		//4번 위치에 3을 넣기
		for(int i=halfX; i<n; ++i) {
			for(int j=0; j<halfY; ++j) {
				res[i][j] = input[i][j+halfY];
			}
		}
		
		//1번 위치에 4를 넣기
		for(int i=0; i<halfX; ++i) {
			for(int j=0; j<halfY; ++j) {
				res[i][j] = input[i+halfX][j];
			}
		}
		
		return res;
	}

	private static int[][] six() {
		int n = input.length;
		int m = input[0].length;
		int[][] res = new int[n][m];
		int halfX = n/2;
		int halfY = m/2;
		
		//4번 위치에 1 넣기
		for(int i=halfX; i<n; ++i) {
			for(int j=0; j<halfY; ++j) {
				res[i][j] = input[i-halfX][j];
			}
		}
		
		//3번 위치에 4 넣기
		for(int i=halfX; i<n; ++i) {
			for(int j=halfY; j<m; ++j) {
				res[i][j] = input[i][j-halfY];
			}
		}
		
		//2번 위치에 3 넣기
		for(int i=0; i<halfX; ++i) {
			for(int j=halfY; j<m; ++j) {
				res[i][j] = input[i+halfX][j];
			}
		}
		
		//1번 위치에 2 넣기
		for(int i=0; i<halfX; ++i) {
			for(int j=0; j<halfY; ++j) {
				res[i][j] = input[i][j+halfY];
			}
		}
		return res;
	}

	//반시계(겉)
	private static int[][] four() {
		int n = input[0].length;
		int m = input.length;
		int[][] res = new int[n][m];
		for(int i=0; i<n; ++i) {
			for(int j=0; j<m; ++j) {
				res[i][j] = input[j][n-i-1];
			}
		}
		return res;
	}

	//시계(안)
	private static int[][] three() {
		int n = input[0].length;
		int m = input.length;
		int[][] res = new int[n][m];
		for(int i=0; i<n; ++i) {
			for(int j=0; j<m; ++j) {
				res[i][j] = input[m-j-1][i];
			}
		}
		return res;
	}

	private static int[][] two() {
		int n = input.length;
		int m = input[0].length;
		int[][] res = new int[n][m];
		// TODO Auto-generated method stub
		for(int i=0; i<n; ++i) {
			for(int j=0; j<m; ++j) {
				res[i][j] = input[i][m-j-1];
			}
		}
		return res;
	}

	private static int[][] one() {
		int n = input.length;
		int m = input[0].length;
		int[][] res = new int[n][m];
		for(int i=0; i<n; ++i) {
			for(int j=0; j<m; ++j) {
				res[i][j] = input[n-i-1][j];
			}
		}
		// TODO Auto-generated method stub
		return res;
	}
}
