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
			System.out.println(Arrays.toString(arr));
			int[][] copy = deepCopy();
			dropDown(copy);
			if(arr[0]==2 && arr[1]==2 && arr[2]==6) {
				print(copy);
				System.out.println("ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ개막장");
			}
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
					System.out.println(i+"번째");
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
//				if(arr[0]==2 && arr[1]==2 && arr[2]==6) {
//					System.out.println(Arrays.toString(temp)+"kk");
//				}
				
				for(int i=1; i<=temp[2]-1; ++i) {
					for(int k=0; k<dx.length; ++k) {
						int nX = x + dx[k]*i;
						int nY = y + dy[k]*i;
//						System.out.println("허허.."+nX+","+nY);
						
						if(0<=nX && nX < H && 0<=nY && nY< W) {
							if(copy[nX][nY]!=0) {
//								System.out.println("걸려들엇옹");
								q.offer(new int[] {nX, nY, copy[nX][nY]});
								copy[nX][nY] = 0;
							}
						}
					}
				}
			}
		}
		downing(copy);
//		print(copy);
	}

	private static void downing(int[][] copy) {
		ArrayList<int[]> list = new ArrayList<>();
		boolean[][] visited = new boolean[H][W];
		for(int i=H-1; i>=0; --i) {
			for(int j=0; j<W; ++j) {
				if(!visited[i][j] && copy[i][j]!=0) {
					visited[i][j]=  true;
					if(arr[0]==2 && arr[1]==2 && arr[2]==6) {
						System.out.println(i+","+j+"햣");
					}
					downing2(copy, i, j, visited);
				}
			}
		}
		
	}

	private static void downing2(int[][] copy, int i, int j, boolean[][] visited) {
		Queue<int[]> q = new LinkedList<>();
		ArrayList<int[]> list = new ArrayList<>();
		q.offer(new int[] {i, j});
		list.add(new int[] {i, j, copy[i][j]});
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			
			for(int k=0; k<dx.length; ++k) {
				int nX = temp[0] + dx[k];
				int nY = temp[1] + dy[k];
				
				if(0<=nX && nX < H && 0<=nY && nY < W) {
					if(!visited[nX][nY] && copy[nX][nY]!=0) {
						visited[nX][nY] = true;
						q.offer(new int[] {nX, nY});
						list.add(new int[] {nX, nY, copy[nX][nY]});
					}
				}
				
			}
		}
		
		realDown(copy, list, visited);
	}

	private static void realDown(int[][] copy, ArrayList<int[]> list, boolean[][] visited) {
		boolean[][] visit = new boolean[H][W];
		for(int[]temp:list) {
			if(temp[0]==H-1) return;
			copy[temp[0]][temp[1]] = 0;
			visited[temp[0]][temp[1]] = false;
			
		}
		System.out.println("뽈롱1");
		for(int i=0; i<list.size(); ++i) {
			visited[list.get(i)[0]+1][list.get(i)[1]] = true;
			visit[list.get(i)[0]+1][list.get(i)[1]] = true;
			
			list.get(i)[0] += 1;
			copy[list.get(i)[0]][list.get(i)[1]] = list.get(i)[2];
		}
		
		System.out.println("뽈롱2");
		for(int[] temp:list) {
			if(temp[0]==H-1) {
				System.out.println("아오");
				return;
			}
			if(copy[temp[0]+1][temp[1]] != 0 && 
					!visit[temp[0]+1][temp[1]])
			{
				System.out.println("심적으로너무힘듬");
				return;
			}
		}
		
		System.out.println("뽈롱3");
		if(arr[0]==2 && arr[1]==2 && arr[2]==6) {
			System.out.println(copy);
		}
		realDown(copy, list, visited);
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
