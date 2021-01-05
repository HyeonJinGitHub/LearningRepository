import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main{
	private static int N;
	private static ArrayList[] arr;
//	private static int[] depth;
	private static int[] parent;
	private static boolean[] check;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new ArrayList[N+1];
		
		for(int i=0; i<N+1; ++i) {
			arr[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<N-1; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			arr[from].add(to);
			arr[to].add(from);
//			System.out.println(from+","+to);
		}
//		System.out.println("??");
		
		Queue<Integer>q = new LinkedList<>();
//		depth = new int[N+1];
		parent = new int[N+1];
		check = new boolean[N+1];
		
		parent[1] = 0;
//		depth[1] = 0;
		
		q.offer(1);
		check[1] = true;
		
		while(q.size()>0) {
			int now = q.poll();
			
			for(int i=0; i<arr[now].size(); ++i) {
				int temp = (int)arr[now].get(i);
				if(!check[temp]) {
					check[temp] = true;
					parent[temp] = now;
//					depth[temp] = depth[now] + 1;
					q.offer(temp);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=2; i<N+1; ++i) {
			sb.append(parent[i]).append('\n');
		}
		System.out.print(sb);
		
		
	}

	private static void print() {
		for(int i=0; i<N+1; ++i) {
			for(int j=0; j<arr[i].size(); ++j) {
				System.out.print(arr[i].get(j)+"\t");
			}
			System.out.println();
		}
		
	}
}
