import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	private static int N;
	private static int[][] input;
	private static int[] count;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		input = new int[N][N];
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; ++j) {
				input[i][j]  = Integer.parseInt(st.nextToken());
			}
		}
		count = new int[3];
		
		go(0, 0, N);
		
		System.out.println(count[0]);
		System.out.println(count[1]);
		System.out.println(count[2]);
	}

	private static void go(int x, int y, int size) {
		int status = input[x][y];
		
		boolean flag = true;
		
		for(int i=x; i<x+size; ++i) {
			for(int j=y; j<y+size; ++j) {
				if(status!=input[i][j]) {
					flag = false;
				}
			}
		}
		
		if(flag) {
			switch(status) {
			case -1:
				count[0]++;
				break;
			case 0:
				count[1]++;
				break;
			case 1:
				count[2]++;
				break;
			}
		}else {
			int k = size/3;
			for(int i=0; i<3; ++i) {
				for(int j=0; j<3; ++j) {
					go(x+i*k, y+j*k, k);
				}
			}
		}
		
		
	}
}
