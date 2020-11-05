import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	private static int N;
	private static int M;
	private static int anw = Integer.MAX_VALUE;
	private static int[][] input;
	private static ArrayList<int[]> house;
	private static ArrayList<int[]> chicken;
	private static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		input = new int[N][N];
		
		chicken = new ArrayList<int[]>();
		house = new ArrayList<int[]>();
		
		arr = new int[M];
		
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; ++j) {
				input[i][j] = Integer.parseInt(st.nextToken());
				if(input[i][j]==1) {
					house.add(new int[] {i, j});
				}else if(input[i][j]==2) {
					chicken.add(new int[] {i, j});
				}
				
			}
		}
		
		dfs(0, 0);
		System.out.println(anw);
	}

	private static void dfs(int idx, int cnt) {
		if(cnt==M) {
//			System.out.println(Arrays.toString(arr));
			go();
			return;
		}
		
		for(int i=idx; i<chicken.size(); ++i) {
			arr[cnt] = i;
			dfs(i+1, cnt+1);
		}
		
	}

	private static void go() {
		int res = 0;
		for(int i=0; i<house.size(); ++i) {
			int hx = house.get(i)[0];
			int hy = house.get(i)[1];
			
			int cnt = Integer.MAX_VALUE;
			
			for(int j=0; j<M; ++j) {
				int t = arr[j];
				int cx = chicken.get(t)[0];
				int cy = chicken.get(t)[1];
				
				int dist = (Math.max(cx, hx) - Math.min(cx, hx)) +(Math.max(cy, hy) - Math.min(cy, hy));
				if(dist < cnt) {
					cnt = dist;
				}
			}
			
			res += cnt;
			
		}
		
		
		if(anw > res) {
			anw = res;
		}
	}
}
