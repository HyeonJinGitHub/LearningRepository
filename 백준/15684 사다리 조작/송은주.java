import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {	
	private static int N;
	private static int M;
	private static int H;
	private static boolean[][] visited;
	private static int anw =987654321;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1][H+1];
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			visited[b][a] = true;
		}
		/*
		for(int i=0; i<visited.length; ++i) {
			for(int j=0; j<visited[0].length; ++j) {
				System.out.print(visited[i][j] + "\t");
			}
			System.out.println();
		}*/
		dfs(0, 1);
		if(anw==987654321)
			anw = -1;
		System.out.println(anw);
	}

	private static void dfs(int cnt, int idx) {
		if(cnt>=4) return;
		
		if(valid()) {
			if(anw > cnt) {
				anw = cnt;
			}
			return;
		}
		
		
		for(int i=idx; i<=H; ++i) {
			for(int j=1; j<N; ++j) { //맨 아래는 깨끗
//				System.out.println(i+","+j);
				if(visited[j-1][i]) continue;
				if(visited[j][i]) continue;
				if(visited[j+1][i]) continue;
				
				visited[j][i] = true;
				dfs(cnt+1, i);
				visited[j][i] = false;
			}
		}
	}

	private static boolean valid() {
		for(int i=1; i<=N; ++i) {
			int temp = i;
			for(int j=1; j<=H; ++j) {
				if(visited[temp][j]) temp++;
				else if(visited[temp-1][j]) temp--;
			}
			if(temp!=i) return false;
		}
		return true;
	}
}//end of class
