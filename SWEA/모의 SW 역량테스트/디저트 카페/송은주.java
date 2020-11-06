import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution{
	private static int T;
	private static int N;
	private static int[][] input;
	private static int[][] pos;
	private static int anw = -1;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; ++tc) {
			anw = -1;
			N = Integer.parseInt(br.readLine().trim());
			
			input = new int[N][N];
			for(int i=0; i<N; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; ++j) {
					input[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			pos = new int[4][2];
			
			
			for(int i=0; i<N-2; ++i) {
				for(int j=1; j<N-1; ++j) {
					for(int d1=1; d1<=j; ++d1) {
						for(int d2=1; d2<N-j; ++d2) {
							if(chk(i, j, d1, d2)) {
								pos[0][0] = i; 		 pos[0][1] = j;
								pos[1][0] = i+d1;	 pos[1][1] = j-d1;
								pos[2][0] = i+d2;	 pos[2][1] = j+d2;
								pos[3][0] = i+d1+d2; pos[3][1] = j+d2-d1;
								
								int res = go();
								if(anw<res) anw = res;								
							}
						}
					}
				}
			}//end of for*4
			
			sb.append('#').append(tc).append(' ').append(anw).append('\n');
		}//end of TC
		
		System.out.println(sb);
	}

	private static boolean chk(int x, int y, int d1, int d2) {
	    if(x+d1>=N || y-d1<0) return false;
	    if(x+d2>=N || y+d2>=N) return false;
	    if(x+d1+d2>=N || y+d2-d1 >=N) return false;
	    //if(x+d1+d2>=N || 0> y+d1-d2) return false;
	    return true;
	}

	private static int go() {
		int cnt = 0;
		boolean[] visited = new boolean[110];
		
		for(int i=pos[0][0], j=pos[0][1]; i<pos[2][0]; ++i, ++j) {
			int temp = input[i][j];
			if(visited[temp]) return -1;
			visited[temp] = true;
			cnt+=1;
			
		}
		
		for(int i=pos[2][0], j=pos[2][1]; i<pos[3][0];++i, --j) {
			int temp = input[i][j];
			if(visited[temp]) return -1;
			visited[temp] = true;
			cnt+=1;
		
		}
		
		for(int i=pos[3][0], j=pos[3][1]; i>pos[1][0];--i, --j) {
			int temp = input[i][j];
			if(visited[temp]) return -1;
			visited[temp] = true;
			cnt+=1;
			
		}
		
		for(int i=pos[1][0], j=pos[1][1]; i>pos[0][0]; --i, ++j) {
			int temp = input[i][j];
			if(visited[temp]) return -1;
			visited[temp] = true;
			cnt+=1;
			
		}
		
		return cnt;
	}
}
