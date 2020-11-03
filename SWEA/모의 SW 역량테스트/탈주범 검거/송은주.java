import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution{
	private static int N;
	private static int M;
	private static int sX;
	private static int sY;
	private static int L;
	private static int[][] input;
	private static boolean[][] visited;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=TC; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			sX = Integer.parseInt(st.nextToken());
			sY = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			input = new int[N][M];
			visited = new boolean[N][M];
			
			for(int i=0; i<N; ++i) {
				String s = br.readLine();
				for(int j=0, index=0; j<M; ++j, index+=2) {
					input[i][j] = s.charAt(index)-'0';
				}
			}
			
			bfs();
			int cnt = 0;
			for(int i=0; i<N; ++i) {
				for(int j=0; j<M; ++j) {
					if(visited[i][j])cnt++;
				}
			}
			sb.append("#").append(tc).append(' ').append(cnt).append('\n');
		}
		
		System.out.println(sb.toString());
	}

	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		
		q.offer(new int[] {sX, sY, input[sX][sY]});
		visited[sX][sY] = true;
		int cnt = 0;
		while(true) {
			int qSize = q.size();
			for(int qS=0; qS<qSize; ++qS) {
				int[] t = q.poll();
				int x = t[0];
				int y = t[1];
				int pipe = t[2];
				visited[x][y] = true;
				
				for(int k=0; k<4; ++k) {
					//k는 우좌하상
					int nX = x + dx[k];
					int nY = y + dy[k];
					
					if(0<=nX && nX < N && 0<=nY && nY < M) {
						if(visited[nX][nY]) continue;
						if(check(pipe, k, input[nX][nY])) q.offer(new int[] {nX, nY, input[nX][nY]});
						
					}
					
				}
				
			}
			
			cnt++;
			if(cnt==L) break;
		}
		
	}

	private static boolean check(int pipe, int k, int nextPipe) {
		switch(pipe) {
		case 1:
			switch(k) {
			case 0:
				if(nextPipe==3 || nextPipe==6 || nextPipe==7 || nextPipe==1) return true;
				break;
			case 1:
				if(nextPipe==3 || nextPipe==4 || nextPipe==5 || nextPipe==1) return true;
				break;
			case 2:
				if(nextPipe==2 || nextPipe==4 || nextPipe==7 || nextPipe==1) return true;
				break;
			case 3:
				if(nextPipe==2 || nextPipe==5 || nextPipe==6 || nextPipe==1) return true;
				break;
			}
			break;
			
		case 2:
			switch(k) {
			case 2:
				if(nextPipe==4 || nextPipe==7 || nextPipe==1 || nextPipe==2) return true;
				break;
			case 3:
				if(nextPipe==1 || nextPipe==5 || nextPipe==6 || nextPipe==2) return true;
				break;
			}
			break;
		case 3:
			switch(k) {
			case 0:
				if(nextPipe==1 || nextPipe==6 || nextPipe==7 || nextPipe==3) return true;
				break;
			case 1:
				if(nextPipe==4 || nextPipe==5 || nextPipe==3 || nextPipe==1) return true;
				
				break;
			}
			break;
		case 4:
			switch(k) {
			case 3:
				if(nextPipe==1 || nextPipe==2 || nextPipe==5 || nextPipe==6) return true;
				break;
			case 0:
				if(nextPipe==1 || nextPipe==3 || nextPipe==6 || nextPipe==7) return true;			
				break;
			}
			break;
		case 5:
			switch(k) {
			case 2:
				if(nextPipe==1 || nextPipe==4 || nextPipe==2 || nextPipe==7) return true;							
				break;
			case 0:
				if(nextPipe==1 || nextPipe==3 || nextPipe==6 || nextPipe==7) return true;							
				break;
			}
			
			break;
		case 6:
			switch(k) {
			case 1:
				if(nextPipe==5 || nextPipe==4 || nextPipe==3 || nextPipe==1) return true;							
				break;
			case 2:
				if(nextPipe==1 || nextPipe==4 || nextPipe==2 || nextPipe==7) return true;							
				break;
			}
			break;
		case 7:
			switch(k) {
			case 3:
				if(nextPipe==6 || nextPipe==5 || nextPipe==2 || nextPipe==1) return true;							
				break;
			case 1:
				if(nextPipe==5 || nextPipe==4 || nextPipe==3 || nextPipe==1) return true;							
				break;
			}
			break;
		}
		
		return false;
	}
}
