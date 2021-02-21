import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	private static int R;
	private static int C;
	private static int anw;
	private static String[] input;
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		input = new String[R];
		visited = new boolean[R][C];
		for(int i=0; i<R; ++i) {
			input[i] = br.readLine();
		}
		
		for(int i=0; i<R; ++i) {
			visited[i][0] = true;
			go(i, 0);
		}
		
		System.out.println(anw);
	}

	private static int[] dx = {-1, 0, 1}; //이 순서 중요
	private static boolean go(int x, int y) {
		if(y==C-1) {
			anw++;
			return true;
		}
		
		for(int i=0; i<3; ++i) {
			int nX = x + dx[i];
			int nY = y + 1;
			
			if(0<=nX && nX < R) {
				if(visited[nX][nY]) continue;
				if(input[nX].charAt(nY)=='x') continue;
				
				visited[nX][nY] = true;
				
				if(go(nX, nY)) {
					return true;
				}
//				visited[nX][nY] = false;
			}
			
		}
		
		return false;
	}
}
