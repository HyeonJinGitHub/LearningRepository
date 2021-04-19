import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Solution{
	private static int TC;
	private static int N;
	private static int[][] input;
	private static int[] blackhole;
	private static int[][] warmhole;
	private static int[] dx = {0, -1, 0, 1};
	private static int[] dy = {1, 0, -1, 0};
	private static int anw;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		TC = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=TC; ++tc) {
			N = Integer.parseInt(br.readLine());
			
			anw = Integer.MIN_VALUE;
			input = new int[N][N];
			warmhole = new int[6][4];
			for(int i=0; i<6; ++i) {
				for(int j=0; j<4; ++j) {
					warmhole[i][j]= -1;
				}
			}
			
			for(int i=0; i<N; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; ++j) {
					input[i][j] = Integer.parseInt(st.nextToken());
					
					if(6<=input[i][j] && input[i][j] <= 10) {
						if(warmhole[input[i][j]-6][0]==-1) {
							warmhole[input[i][j]-6][0] = i;
							warmhole[input[i][j]-6][1] = j;							
						}else {
							warmhole[input[i][j]-6][2] = i;
							warmhole[input[i][j]-6][3] = j;
						}
					}
					
				}
			}
			
			for(int i=0; i<N; ++i) {
				for(int j=0; j<N; ++j) {
					if(input[i][j]==0) {
						for(int k=0; k<4; ++k) {
							int res = go(i, j, k);
//							System.out.println("결과:"+res);
							anw = Math.max(anw, res);
						}
					}
				}
			}
			
			sb.append('#').append(tc).append(' ').append(anw).append('\n');
		}
		System.out.print(sb);
	}

	private static int go(int startX, int startY, int startDir) {
//		System.out.println("시작"+startX+","+startY+","+startDir);
		int sX = startX;
		int sY = startY;
		int res = 0;
//		int num = 0;
		while(true) {
//			num++;
			int nX = startX + dx[startDir];
			int nY = startY + dy[startDir];
//			System.out.println(/*num+",?"+*/nX+","+nY);
			
			if(0<=nX && nX < N && 0<=nY && nY < N) {
				if(nX == sX && nY == sY) {
					return res;
				}
				if(input[nX][nY]==-1) {
					return res;
				}
				
				if(input[nX][nY]==0) {
					startX = nX;
					startY = nY;
				}else if(1<=input[nX][nY] && input[nX][nY] <=5) {
					startDir = __toggle(startDir, input[nX][nY]);
					res++;
					startX = nX; startY = nY;
				}else if(6<=input[nX][nY] && input[nX][nY] <=10) {
					if(warmhole[input[nX][nY]-6][0]==nX &&
							warmhole[input[nX][nY]-6][1]==nY) {
						startX = warmhole[input[nX][nY]-6][2];
						startY = warmhole[input[nX][nY]-6][3];
					}else {
						startX = warmhole[input[nX][nY]-6][0];
						startY = warmhole[input[nX][nY]-6][1];
					}
				}
				
			}else {
				startDir = __toggle(startDir);
				startX = nX; startY = nY;
				res++;
			}
			
		}
//		return -1;
	}

	private static int __toggle(int startDir, int block) {
		switch(block) {
		case 1:
			switch(startDir) {
			case 0:
				return 2;
			case 1:
				return 3;
			case 2:
				return 1;
			case 3:
				return 0;
			}
		case 2:
			switch(startDir) {
			case 0:
				return 2;
			case 1:
				return 0;
			case 2:
				return 3;
			case 3:
				return 1;
			}
		case 3:
			switch(startDir) {
			case 0:
				return 3;
			case 1:
				return 2;
			case 2:
				return 0;
			case 3:
				return 1;
			}
		case 4:
			switch(startDir) {
			case 0:
				return 1;
			case 1:
				return 3;
			case 2:
				return 0;
			case 3:
				return 2;
			}
		case 5:
			switch(startDir) {
			case 0:
				return 2;
			case 1:
				return 3;
			case 2:
				return 0;
			case 3:
				return 1;
			}
		}
		return -1;
	}

	private static int __toggle(int dir) {
		switch(dir) {
		case 0:
			return 2;
		case 1:
			return 3;
		case 2:
			return 0;
		case 3:
			return 1;
		}
		return -1;
	}
}
