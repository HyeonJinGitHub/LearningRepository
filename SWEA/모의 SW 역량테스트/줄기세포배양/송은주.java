import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution{
	private static int TC;
	private static int N;
	private static int M;
	private static int K;
	private static int[][] input;
	private static int[][] remain;
	private static int[][] state;
	private static ArrayList[][] arr;
	private static final int num = 400;
	
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		input = new int[num][num];
		remain = new int[num][num];
		state = new int[num][num];
		arr = new ArrayList[num][num];
		
		for(int i=0; i<arr.length; ++i) {
			for(int j=0; j<arr.length; ++j) {
				arr[i][j] = new ArrayList<Integer>();
			}
		}
		//1은 활성 2는 비활성 -1은 죽음
		for(int tc = 1; tc<=TC; ++tc) {
			init();
			st = new StringTokenizer(br.readLine(), " ");
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			int m = input.length/2;
			
			for(int i=0; i<N; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<M; ++j) {
					int t = Integer.parseInt(st.nextToken());
					
					if(t!=0) {
						input[i+m][j+m] = t;
						remain[i+m][j+m] = t; 
						state[i+m][j+m] = 2;
					}
				}
			}
			sb.append('#').append(tc).append(' ').append(go()).append('\n');
			
		}
		
		System.out.print(sb);
	}

	//1은 활성 2는 비활성 -1은 죽음
	private static int go() {
		for(int k=0; k<K; ++k) {
			Queue<int[]> q = new LinkedList<>();
			for(int i=0; i<input.length; ++i) {
				for(int j=0; j<input[0].length; ++j) {
					if(state[i][j]==1) {
						if(remain[i][j]==0 && state[i][j]==1) {
							q.offer(new int[] {i, j, input[i][j]});							
						}
						if(remain[i][j]+1==input[i][j]) {
							state[i][j] = -1;
						}else {
							remain[i][j]++;
						}
					}else if(state[i][j]==2) {
						remain[i][j]--;
						if(remain[i][j]==0) {
							state[i][j] = 1;
						}
					}
					
				}
			}
			
			while(q.size()>0) {
				int[] temp = q.poll();
				
				int x = temp[0];
				int y = temp[1];
				int val= temp[2];
				
				for(int p=0; p<4; ++p) {
					int nX = x + dx[p];
					int nY = y + dy[p];
					
					if(input[nX][nY]==0) {
						arr[nX][nY].add(val);
					}
				}
			}
			
			for(int i=0; i<arr.length; ++i) {
				for(int j=0; j<arr.length; ++j) {
					if(arr[i][j].size()>0) {
						if(arr[i][j].size()>=2) {
							Collections.sort(arr[i][j]);
							int w = (int) arr[i][j].get(arr[i][j].size()-1);
							input[i][j] = w;
							state[i][j] = 2;
							remain[i][j] = w;
						}else if(arr[i][j].size()==1) {
							int w = (int) arr[i][j].get(0);
							input[i][j] = w;
							state[i][j] = 2;
							remain[i][j] = w;							
						}
						arr[i][j].clear();
					}
				}
			}
//			if(k==1) {
//				print();
//				
//			}
		}
		int res = 0;
		for(int i=0; i<input.length; ++i) {
			for(int j=0; j<input[0].length; ++j) {
				if(state[i][j] == 1 || state[i][j] == 2) {
					res++;
				}
			}
		}
		
		return res;
	}

	private static void print() {
		for(int i=0; i<input.length; ++i) {
			for(int j=0; j<input[0].length; ++j) {
				if(input[i][j]!=0) {
					System.out.println(input[i][j]);
					
				}
			}
		}
		System.out.println("끝!");
	}

	private static void init() {
		for(int i=0; i<input.length; ++i) {
			for(int j=0; j<input[0].length; ++j) {
				input[i][j] = 0;
				remain[i][j] = 0;
				state[i][j] = 0;
			}
		}
		
	}
}
