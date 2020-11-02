import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution{
	private static int W;
	private static int H;
	private static int[][] input;
	private static int N;
	private static int[] arr;
	private static int anw;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};
	private static Queue<int[]> q;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine().trim());
		q = new LinkedList<>();
		
		for(int tc=1; tc<=TC; ++tc) {
			anw = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine(), " ");
			
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			input = new int[H][W];
			for(int i=0; i<H; ++i) {
				String s = br.readLine();
				for(int j=0, index=0; j<W; ++j, index+=2) {
					input[i][j] = s.charAt(index)-'0';
				}
			}
			arr = new int[N];
			go(0);
			
			System.out.println("#" + tc + " " + anw);
		}
		
		
	}

	private static void go(int cnt) {
		if(cnt==N) {
			int[][] temp = deepCopy();
			
			int res = simulation(temp);
			if(anw>res) {
				anw = res;
			}
			return;
		}
		
		for(int i=0; i<W; ++i) {
			arr[cnt] = i;
			go(cnt+1);
		}
		
	}


	private static int simulation(int[][] temp) {
		for(int z=0; z<arr.length; ++z) {
			boolean flag = false;
			q.clear();
			int k = arr[z];
			
			for(int i=0; i<H; ++i) {
				if(temp[i][k]!=0) {
					if(temp[i][k]!=1) {
						q.offer(new int[] {i, k, temp[i][k]});						
					}
					temp[i][k] = 0;
					break;
				}
			}
			
			while(q.size()>0) {
				flag = true;
				int[] a = q.poll();
				
				for(int p = 0; p<a[2]; ++p) {
					
					for(int u=0; u<4; ++u) {
						int nX = a[0] + dx[u]*p;
						int nY = a[1] + dy[u]*p;
						
						if(0<= nX && nX < H && 0<=nY && nY < W) {
							if(temp[nX][nY]==0) continue;
							
							if(temp[nX][nY]==1) {
								temp[nX][nY] = 0;
								continue;
							}
							
							q.offer(new int[] {nX, nY, temp[nX][nY]});
							temp[nX][nY] = 0;
						}
					}
					
				}
				
				
			}
			if(flag) down(temp);
		}
		int res = 0;
		for(int i=0; i<H; ++i) {
			for(int j=0; j<W; ++j) {
				if(temp[i][j]!=0) res++;
			}
		}
		return res;
	}

	private static void down(int[][] temp) {
		for(int i=H-1; i>=0; --i) {
			for(int j=W-1; j>=0; --j) {
				if(temp[i][j]!=0) {
					int z = temp[i][j];
					temp[i][j] = 0;
					int nX = i;
					while(true) {
						nX++;
						if(nX >= H) {
							nX--;
							break;
						}
						
						if(temp[nX][j]!=0) {
							nX--;
							break;
						}
					}
					temp[nX][j] = z;
				}
			}
		}
		
	}

	private static int[][] deepCopy() {
		int[][] result = new int[H][W];
		for(int i=0; i<H; ++i) {
			for(int j=0; j<W; ++j) {
				result[i][j] = input[i][j];
			}
		}
		
		return result;
	}
}
