import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Info{
	int x;
	int y;
	
	public Info(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}
public class Main{
	private static int W;
	private static int H;
	private static char[][] input;
	private static Info startInfo;
	private static Info endInfo;
	private static int[][] visited;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};
	
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		input = new char[H][W];
		startInfo = new Info(-1, -1);
		endInfo = new Info(-1, -1);
		
		for(int i=0; i<H; ++i) {
			String s = br.readLine();
			for(int j=0; j<W; ++j) {
				input[i][j] = s.charAt(j);
				if(input[i][j]=='C') {
					if(startInfo.x==-1) {
						startInfo.x = i;
						startInfo.y = j;
						continue;
					}
					endInfo.x = i;
					endInfo.y = j;
				}
				
			}
		}
		
		
		visited = new int[H][W];
		for(int i=0; i<H; ++i) {
			for(int j=0; j<W; ++j) {
				visited[i][j] = -1;
			}
		}

		Queue<Info>q = new LinkedList<Info>();
		q.offer(new Info(startInfo.x, startInfo.y));
		visited[startInfo.x][startInfo.y] = 0;
		
		while(!q.isEmpty()) {
			int x = q.peek().x;
			int y = q.poll().y;
			
			for(int k=0; k<4; ++k) {
				int nX = x + dx[k];
				int nY = y + dy[k];
				
				while(0<=nX && nX < H && 0<=nY && nY < W) {
					if(input[nX][nY]=='*') break;
          //if(visited[nX][nY]!=-1) break;
					if(visited[nX][nY]==-1) {
						visited[nX][nY] = visited[x][y] + 1;
						q.offer(new Info(nX, nY));
					}
					nX += dx[k];
					nY += dy[k];
				}
				
				
			}
		}
		/*
		for(int i=0; i<H; ++i) {
			for(int j=0; j<W; ++j) {
				System.out.print(visited[i][j] + "\t");
			}
			System.out.println();
		}
		*/
		System.out.println(visited[endInfo.x][endInfo.y]-1);
		
		
	}




}
