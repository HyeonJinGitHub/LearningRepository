import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
	int x;
	int y;
	int force;
	public Point(int x, int y, int force) {
		super();
		this.x = x;
		this.y = y;
		this.force = force;
	}
}

public class Main{
	private static int H;
	private static int W;
	private static int O;
	private static int F;
	private static int sX;
	private static int sY;
	private static int eX;
	private static int eY;
	private static int[][] input;
	private static int[][] visited;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; ++t) {
			st = new StringTokenizer(br.readLine(), " ");
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			O = Integer.parseInt(st.nextToken());
			F = Integer.parseInt(st.nextToken());
			sX = Integer.parseInt(st.nextToken());
			sY = Integer.parseInt(st.nextToken());
			eX = Integer.parseInt(st.nextToken());
			eY = Integer.parseInt(st.nextToken());
			
			input = new int[H+1][W+1];
			visited = new int[H+1][W+1];
			for(int i=0; i<O; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int o = Integer.parseInt(st.nextToken());
				
				input[x][y] = o;
			}
			
			System.out.println(bfs()?"잘했어!!":"인성 문제있어??");
		}
	}

	private static boolean bfs() {
		Queue<Point> q = new LinkedList<Point>();
		
		q.offer(new Point(sX, sY, F));
		visited[sX][sY] = 1;
		
		while(!q.isEmpty()) {
			int x = q.peek().x;
			int y = q.peek().y;
			int f = q.poll().force;
//			System.out.println(x+","+y+"=>"+f);
			
			if(x==eX && y==eY && f>=0) {
//				System.out.println("도착했어.true");
				return true;
			}
			if(f<=0) continue;
			
			for(int k=0; k<4; ++k) {
				int nX = x + dx[k];
				int nY = y + dy[k];
				
				if(0<nX && nX <= H && 0<nY && nY <= W) {
					if((input[x][y] > input[nX][nY]) && visited[nX][nY]==0) {
						q.offer(new Point(nX, nY, f-1));
						visited[nX][nY] = 1;
					}
					
					if(f >= (input[nX][nY] - input[x][y]) && visited[nX][nY]==0) {
						q.offer(new Point(nX, nY, f-1));
						visited[nX][nY] = 1;
					}
				}
			}
		}
		return false;
	}
}