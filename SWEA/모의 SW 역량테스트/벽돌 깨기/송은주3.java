import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

class Solution{
	private static int TC;
	private static int N;
	private static int W;
	private static int H;
	private static int[][] input;
	private static int[] arr;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};
	private static int anw;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			input = new int[H][W];
			anw = Integer.MAX_VALUE;
			
			for(int i=0; i<H; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<W; ++j) {
					input[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			arr = new int[N];
			go(0);
			
			sb.append('#').append(tc).append(' ').append(anw).append('\n');
			
		}
		System.out.print(sb);
	}

	private static void go(int cnt) {
		if(cnt==N) {
//			System.out.println(Arrays.toString(arr));
			int[][] copy = deepCopy();
			dropDown(copy);
			int res = count(copy);
			anw = Math.min(res, anw);
			return;
		}
		
		for(int i=0; i<W; ++i) {
			arr[cnt] = i;
			go(cnt+1);
		}
		
	}

	private static int count(int[][] copy) {
		int res = 0;
		for(int i=0; i<H; ++i) {
			for(int j=0; j<W; ++j) {
				if(copy[i][j]!=0) {
					res++;
				}
			}
		}
		return res;
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

	private static void dropDown(int[][] copy) {
		for(int i=0; i<arr.length; ++i) {
			int temp = arr[i];
			
			Queue<int[]> q = new LinkedList<>();
			for(int j=0; j<H; ++j) {
				if(copy[j][temp]!=0) {
//					System.out.println(j+","+temp+","+copy[j][temp]+"힘들어");
					q.offer(new int[] {j, temp, copy[j][temp]});
					copy[j][temp]=0;
//					if(arr[0]==2 && arr[1]==2 && arr[2]==6) {
//						print(copy);
//					}
					broken(q, copy);
					break;
				}
			}
		}
		
	}

	private static void broken(Queue<int[]> q, int[][] copy) {
		while(!q.isEmpty()) {
			int qSize = q.size();
			
			for(int a=0; a<qSize; ++a) {
				int[] temp = q.poll();
				int x = temp[0];
				int y = temp[1];
				
				for(int i=0; i<=temp[2]-1; ++i) {
					for(int k=0; k<dx.length; ++k) {
						int nX = x + dx[k]*i;
						int nY = y + dy[k]*i;
						
						if(0<=nX && nX < H && 0<=nY && nY< W) {
							if(copy[nX][nY]!=0) {
								q.offer(new int[] {nX, nY, copy[nX][nY]});
								copy[nX][nY] = 0;
							}
						}
					}
				}
			}
		}
		down(copy);
	}

	private static void down(int[][] copy) {
		for(int i=0; i<W; ++i) {
			
			Queue<Integer> qq = new LinkedList<>();
			for(int j=H-1; j>=0; --j) {
				if(copy[j][i]!=0) {
					qq.offer(copy[j][i]);
					copy[j][i] = 0;
				}
			}
			if(qq.size()>0) {
				int temp = H-1;
				while(!qq.isEmpty()) {
					copy[temp--][i] = qq.poll();
				}
				
			}
		}
	}

	private static void print(int[][] copy) {
		System.out.println("==");
		for(int i=0; i<H; ++i) {
			for(int j=0; j<W; ++j) {
				System.out.print(copy[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println("==");
		
	}
}
