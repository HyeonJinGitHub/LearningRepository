import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	private static int N;
	private static int R;
	private static int[][] input;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		N = (int) Math.pow(2, N);
		R = Integer.parseInt(st.nextToken());
		
		input = new int[N][N];
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; ++j) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<R; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int k = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			if(1<=k && k<=4) {
				if(l==0) continue;
				int[][] arr = new int[N][N];
				get(0, 0, l, 3, N, k, arr);
				
				print(arr);
				
				
			}else {
				int[][] arr = new int[N][N];
				go(0, 0, l, 3, N, k, arr);
			}
			
			
			
		}
		
	}

	

	private static void go(int startX, int startY, int goal, int now, int size, int type, int[][] arr) {
		if(goal==now) {
			System.out.println(startX+","+startY+","+goal+","+now+","+size);
			
			switch(type) {
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			}
			return;
		}
		
//		go(startX+size/2, startY+size/2, goal, now-1, size/2, type);
//		go(startX, startY+size/2, goal, now-1, size/2, type);
//		go(startX+size/2, startY, goal, now-1, size/2, type);
//		go(startX, startY, goal, now-1, size/2, type);
		
	}



	private static void get(int startX, int startY, int goal, int now, int size, int type, int[][] arr) {
		if(goal==now) {
			System.out.println(startX+","+startY+","+goal+","+now+","+size);
			
			int[][] temp = new int[size][size];
			int[][] res = new int[size][size];
			for(int i=startX; i<startX+size; ++i) {
				for(int j=startY; j<startY+size; ++j) {
					res[i-startX][j-startY] = input[i][j];
				}
			}
//			print(res);
			
			switch(type) {
			case 1:
				for(int i=0; i<size; ++i) {
					for(int j=0; j<size; ++j) {
//						System.out.print((N-(i+startX)-1)+",");
//						System.out.println((j+startY));
						temp[i][j] = res[size-i-1][j];
					}
				}
				
				for(int i=0; i<size; ++i) {
					for(int j=0; j<size; ++j) {
						arr[i+startX][j+startY] = temp[i][j];
					}
				}
//				print(temp);
				break;
			case 2:
				for(int i=0; i<size; ++i) {
					for(int j=0; j<size; ++j) {
//						System.out.print((N-(i+startX)-1)+",");
//						System.out.println((j+startY));
						temp[i][j] = res[i][size-j-1];
					}
				}
				
				for(int i=0; i<size; ++i) {
					for(int j=0; j<size; ++j) {
						arr[i+startX][j+startY] = temp[i][j];
					}
				}
				
				break;
			case 3:
				for(int i=0; i<size; ++i) {
					for(int j=0; j<size; ++j) {
						temp[i][j] = res[size-j-1][i];
					}
				}
				
				for(int i=0; i<size; ++i) {
					for(int j=0; j<size; ++j) {
						arr[i+startX][j+startY] = temp[i][j];
					}
				}
				break;
			case 4:
				for(int i=0; i<size; ++i) {
					for(int j=0; j<size; ++j) {
						temp[i][j] = res[j][size-i-1];
					}
				}
				
				for(int i=0; i<size; ++i) {
					for(int j=0; j<size; ++j) {
						arr[i+startX][j+startY] = temp[i][j];
					}
				}
				break;
			}
			
			return;
		}
		get(startX+size/2, startY+size/2, goal, now-1, size/2, type, arr);
		get(startX, startY+size/2, goal, now-1, size/2, type, arr);
		get(startX+size/2, startY, goal, now-1, size/2, type, arr);
		get(startX, startY, goal, now-1, size/2, type, arr);
		
	}

	private static void print(int[][] temp) {
		for(int i=0; i<temp.length;++i) {
			for(int j=0; j<temp[0].length; ++j) {
				System.out.print(temp[i][j] + "\t");
			}
			System.out.println();
		}
		
		System.out.println("==");
	}
}
