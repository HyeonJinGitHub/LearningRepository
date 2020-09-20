import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class point{
	int x;
	int y;
	int target;
	public point(int x, int y, int target) {
		super();
		this.x = x;
		this.y = y;
		this.target = target;
	}
	@Override
	public String toString() {
		return "point [x=" + x + ", y=" + y + ", target=" + target + "]";
	}
	
	
}
public class Main {
	private static int N;
	private static int K;
	private static int[][] input;
	private static int[] dx= {0, 0, 1, -1};
	private static int[] dy= {1, -1, 0, 0};
	private static int S;
	private static int X;
	private static int Y;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		input = new int[N][N];
		
		PriorityQueue<point> pq = new PriorityQueue<point>(new Comparator<point>() {
			@Override
			public int compare(point o1, point o2) {
				return o1.target - o2.target;
			}
		});
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; ++j) {
				input[i][j] = Integer.parseInt(st.nextToken());
				if(input[i][j]!=0) {
					pq.offer(new point(i, j, input[i][j]));
					//System.out.println(new point(i, j, input[i][j]));
				}
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		
		X-=1; Y-=1;
		
		if(S==0) {
			System.out.println(input[X][Y]);
			return;
		}
		
		int cnt = 0;
		ArrayList<point> arr = new ArrayList<point>();
		while(!pq.isEmpty()) {
			arr.clear();
			boolean flag = false;
			int qSize = pq.size();
//			System.out.println(qSize +"..");
			for(int q=0; q<qSize; ++q) {
				point p= pq.poll();
//				System.out.println(p);
				
				for(int k=0; k<4; ++k) {
					int nX = p.x + dx[k];
					int nY = p.y + dy[k];
					
					if(0<= nX && nX < N && 0<=nY && nY <N) {
						if(input[nX][nY]!=0) continue;
						input[nX][nY] = p.target;
						if(nX == X && nY == Y) {
							System.out.println(input[nX][nY]);
							return;
						}
						arr.add(new point(nX, nY, p.target));
						//pq.offer(new point(nX, nY, p.target));
						flag = true;
					}
				}
				//print();
			}
			
			if(flag) {
				for(int i=0; i<arr.size(); ++i) {
					pq.offer(arr.get(i));
				}
				cnt += 1;
				if(cnt==S) break;
			}else break;
			
		}
		
		System.out.println(input[X][Y]);
	}

	private static void print() {
		System.out.println("============");
		
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				System.out.print(input[i][j] + "\t");
			}
			System.out.println();
		}
		
	}
}//end of class
