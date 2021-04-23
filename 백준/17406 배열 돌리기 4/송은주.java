import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

class Main{
	private static int N;
	private static int M;
	private static int K;
	private static int[][] input;
	private static int[][] arr;
	private static int[] ary;
	private static boolean[] isSelected;
	private static int anw;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		input = new int[N][M];
		arr = new int[K][3];
		anw = Integer.MAX_VALUE;
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; ++j) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<K; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<3; ++j) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
			arr[i][0]-=1;
			arr[i][1]-=1;
		}
		
		isSelected = new boolean[K];
		ary = new int[K];
		go(0);
		System.out.print(anw);
	}

	private static void go(int cnt) {
		if(cnt==K) {
//			System.out.println(Arrays.toString(ary));
			int[][] temp = deepCopy();
			
			anw = Math.min(anw, simulate(temp));
			
			return;
		}
		
		for(int i=0; i<K; ++i) {
			if(!isSelected[i]) {
				isSelected[i] = true;
				ary[cnt] = i;
				go(cnt+1);
				isSelected[i] = false;
			}
		}
		
	}

	private static int simulate(int[][] temp) {
		for(int q=0; q<ary.length; ++q) {
			int x = arr[ary[q]][0];
			int y = arr[ary[q]][1];
			int z = arr[ary[q]][2];
			
			
			int n = (x+z) - (x-z);
			int m = (y+z) - (y-z);
			
			
			int L = Math.min(n, m)/2;
			for(int l=0; l<L; ++l) {
				
				int vert = n - 2*l;
				int hori = m - 2*l;
				
				int a = l;
				int b = l;
				Deque<Integer> o =new LinkedList<>();
				for(int i=0; i<hori; ++i) {
					o.offerLast(temp[x-z+a][y-z+b]);
					b++;
				}
				for(int i=0; i<vert; ++i) {
					o.offerLast(temp[x-z+a][y-z+b]);
					a++;
				}
				for(int i=0; i<hori; ++i) {
					o.offerLast(temp[x-z+a][y-z+b]);
					b--;
				}
				for(int i=0; i<vert; ++i) {
					o.offerLast(temp[x-z+a][y-z+b]);
					a--;
				}
				o.offerFirst(o.pollLast());
				a = b = l;
				for(int i=0; i<hori; ++i) {
					temp[x-z+a][y-z+b] = o.pollFirst();
					b++;
				}
				for(int i=0; i<vert; ++i) {
					temp[x-z+a][y-z+b] = o.pollFirst();
					a++;
				}
				for(int i=0; i<hori; ++i) {
					temp[x-z+a][y-z+b] = o.pollFirst();
					b--;
				}
				for(int i=0; i<vert; ++i) {
					temp[x-z+a][y-z+b] = o.pollFirst();
					a--;
				}
				
			}
			
//			print(temp);
			
			
		}
		int res = Integer.MAX_VALUE;
		for(int i=0; i<N; ++i) {
			int e = 0;
			for(int j=0; j<M; ++j) {
				e += temp[i][j];
			}
			res = Math.min(e, res);
		}
		return res;
	}

	private static void print(int[][] temp) {
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<temp.length; ++i) {
			for(int j=0; j<temp[0].length; ++j) {
				sb.append(temp[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.print(sb);
		
	}

	private static int[][] deepCopy() {
		int[][] res = new int[N][M];
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				res[i][j] = input[i][j];
			}
		}
		return res;
	}
}
