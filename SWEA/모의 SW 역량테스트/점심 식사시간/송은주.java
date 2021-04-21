import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution{
	private static int TC;
	private static int N;
	private static int anw;
	private static int[][] input;
	private static int[][] stair;
	private static ArrayList<int[]> arr;
	private static boolean[] isSelected;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();
		TC = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=TC; ++tc) {
			arr = new ArrayList<>();
			N = Integer.parseInt(br.readLine());
			
			anw = Integer.MAX_VALUE;
			input = new int[N][N];
			stair = new int[2][2];
			
			for(int i=0; i<2; ++i) {
				for(int j=0; j<2; ++j) {
					stair[i][j] = -1;
				}
			}
			
			for(int i=0; i<N; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; ++j) {
					input[i][j] = Integer.parseInt(st.nextToken());
					if(input[i][j]==1) {
						arr.add(new int[] {i, j});
					}else if(input[i][j]!=0) {
						if(stair[0][0]==-1) {
							stair[0][0] = i;
							stair[0][1] = j;
						}else {
							stair[1][0] = i;
							stair[1][1] = j;
						}
					}
				}
			}
			
			isSelected = new boolean[arr.size()];
			go(0);
			
			sb.append('#').append(tc).append(' ').append(anw).append('\n');
		}
		System.out.print(sb);
	}

	private static void go(int cnt) {
		if(cnt==arr.size()) {
			ArrayList<Integer>A = new ArrayList<>();
			ArrayList<Integer>B = new ArrayList<>();
			
			for(int i=0; i<arr.size(); ++i) {
				if(isSelected[i]) {
					/*System.out.print(i);*/ A.add(i);
				}
				else {
					/*System.out.print('X');*/ B.add(i);
				}
			}
//			System.out.println();
			
			simulate(A, B);
			return;
		}
		
		isSelected[cnt] = true;
		go(cnt+1);
		
		isSelected[cnt] = false;
		go(cnt+1);
	}

	private static void simulate(ArrayList<Integer> a, ArrayList<Integer> b) {
		int[] A = new int[a.size()];
		int Astair = input[stair[0][0]][stair[0][1]];
		for(int i=0; i<a.size(); ++i) {
			int x = arr.get(a.get(i))[0];
			int y = arr.get(a.get(i))[1];
			
			A[i] = (Math.max(x, stair[0][0])-Math.min(x, stair[0][0])) 
					+ (Math.max(y, stair[0][1])-Math.min(y, stair[0][1]));
			
		}
		Arrays.sort(A);
//		System.out.println(Arrays.toString(A));
		for(int i=3; i<A.length; ++i) {
			if(A[i] - A[i-3] < Astair) {
				A[i] = A[i-3] + Astair;
			}
		}
		int timeA = 0;
		if(A.length>0) {
			timeA = A[A.length-1] + Astair + 1;
		}
	
		
		int[] B = new int[b.size()];
		int Bstair = input[stair[1][0]][stair[1][1]];
		for(int i=0; i<b.size(); ++i) {
			int x = arr.get(b.get(i))[0];
			int y = arr.get(b.get(i))[1];
			
			B[i] = (Math.max(x, stair[1][0])-Math.min(x, stair[1][0])) 
					+ (Math.max(y, stair[1][1])-Math.min(y, stair[1][1]));
			
		}
		Arrays.sort(B);
//		System.out.println(Arrays.toString(B));
		for(int i=3; i<B.length; ++i) {
			if(B[i] - B[i-3] < Bstair) {
				B[i] = B[i-3] + Bstair;
			}
		}
		int timeB = 0;
		if(B.length>0) {
			timeB = B[B.length-1] + Bstair + 1;
		}
	
		int res = Math.max(timeA, timeB);
//		System.out.println(timeB+"??");
//		System.out.println("Res"+res);
		anw = Math.min(res, anw);
		
	}
}
