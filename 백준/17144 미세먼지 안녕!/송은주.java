import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Point{
	int x;
	int y;
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}
public class Main {		
	private static int R;
	private static int C;
	private static int T;
	private static int[][] input;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};
	private static int[][] calc;
	private static int machineA;
	private static int machineB;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		input = new int[R][C];
		calc = new int[R][C];
		machineA = -1;
		machineB = -1;
		for(int i=0; i<R; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<C; ++j) {
				input[i][j] = Integer.parseInt(st.nextToken());
				if(input[i][j]==-1 && machineA==-1) {
					machineA=i;
					machineB=i+1;
				}
			}
		}
		
		for(int i=0; i<T; ++i) {
			init();
			diffusion();
//			print();
//			System.out.println("=====");
			clean();
//			print();
		}
		
		int anw = 0;
		for(int i=0; i<R; ++i) {
			for(int j=0; j<C; ++j) {
				if(input[i][j]==-1) continue;
				anw += input[i][j];
			}
		}
		
		System.out.println(anw);
	}

	private static void clean() {
		for(int i=machineA-1; i>0; --i) {
			input[i][0] = input[i-1][0]; 
		}
		
		for(int i=0; i<C-1; ++i) {
			input[0][i] = input[0][i+1];
		}
		
		for(int i=1; i<=machineA; ++i) {
			input[i-1][C-1] = input[i][C-1];
		}
		
		for(int i=C-1; i>1; --i) {
			input[machineA][i] = input[machineA][i-1];
		}
		
		input[machineA][1] = 0;
		
		for(int i=machineA+1; i<R-1; ++i) {
			input[i][0] = input[i+1][0];
		}
		
		for(int i=0; i<C-1; ++i) {
			input[R-1][i] = input[R-1][i+1];
		}
		
		for(int i=R-1; i>machineB; --i) {
			input[i][C-1] = input[i-1][C-1];
		}
		
		for(int i=C-1; i>1; --i) {
			input[machineB][i]= input[machineB][i-1];
		}
		
		input[machineB][0] = -1;
		input[machineB][1] = 0;
	}

	private static void init() {
		for(int i=0; i<R; ++i) {
			for(int j=0; j<C; ++j) {
				calc[i][j] = 0;
			}
		}
		
	}

	private static void print() {
		for(int i=0; i<R; ++i) {
			for(int j=0; j<C; ++j) {
				System.out.print(input[i][j] + "\t");
			}
			System.out.println();
		}
		
	}

	private static void diffusion() {
		for(int i=0; i<R; ++i) {
			for(int j=0; j<C; ++j) {
				if(i==machineA && j==0) continue;
				if(i==machineB && j==0) continue;
				if(input[i][j]==0) continue;
				
				
				int cnt = 0;
				for(int k=0; k<4; ++k) {
					int nI = i+dx[k];
					int nJ = j+dy[k];
					
					if(0<=nI && nI < R && 0<=nJ && nJ < C) {
						if(nI==machineA && nJ==0)continue;
						if(nI==machineB && nJ==0)continue;
						cnt += 1;
					}
				}
				calc[i][j] -= (input[i][j]/5 * cnt);
				
				
				for(int k=0; k<4; ++k) {
					int nI = i+dx[k];
					int nJ = j+dy[k];
					
					if(0<=nI && nI < R && 0<=nJ && nJ < C) {
						if(nI==machineA && nJ==0)continue;
						if(nI==machineB && nJ==0)continue;
						calc[nI][nJ] += input[i][j]/5;
					}
				}
				
				
			}
		}
		for(int i=0; i<R; ++i) {
			for(int j=0; j<C; ++j) {
				input[i][j] += calc[i][j];
			}
		}
		
	}


}//end of class
