import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
class Main{
	private static int R;
	private static int C;
	private static char[][] input;
	private static int K;
	private static int[] game;
	private static int[] dx = {-1, 0, 0, 1};
	private static int[] dy = {0, -1, 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		input = new char[R][C];
		for(int i=0; i<R; ++i) {
			String s = br.readLine();
			for(int j=0; j<C; ++j) {
				input[i][j] = s.charAt(j);
			}
		}
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		game = new int[K];
		
		for(int k=0; k<K; ++k) {
			game[k] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<K; ++i) {
			attack(game[i], i%2);
//			print();
			down();
//			System.out.println("내린후");
//			print();
//			System.out.println("===");
		}
		print();
	}

	private static void attack(int idx, int dir) {
		int target = R - idx;
//		System.out.println(target);
		if(dir==0) {
			for(int i=0; i<C; ++i) {
				if(input[target][i]=='x') {
					input[target][i] = '.';
					break;
				}
			}			
		}else {
			for(int i=C-1; i>=0; --i) {
				if(input[target][i]=='x') {
					input[target][i] = '.';
					break;
				}
			}
		}
	}

	private static void print() {
		for(int i=0; i<R; ++i) {
			for(int j=0; j<C; ++j) {
				System.out.print(input[i][j]);
			}
			System.out.println();
		}
		
	}

	private static void down() {
		boolean[][] visited = new boolean[R][C];
		
		for(int i=0; i<R-1; ++i) {
			for(int j=0; j<C; ++j) {
				if(input[i][j]=='x' && !visited[i][j]) {
					go(i, j, visited);
				}
			}
		}
	
		
	}

	private static void go(int i, int j, boolean[][] visited) {
		ArrayList<Info> arr = new ArrayList<>();
		arr.add(new Info(i, j));
		visited[i][j] = true;
		Queue<Info> q = new LinkedList<>();
		
		q.offer(new Info(i, j));
		
		while(!q.isEmpty()) {
			int x = q.peek().x;
			int y = q.poll().y;
			
			for(int k=0; k<4; ++k) {
				int nX = x + dx[k];
				int nY = y + dy[k];
				
				if(0<=nX && nX < R && 0<=nY && nY < C) {
					if(!visited[nX][nY] && input[nX][nY]=='x') {
						visited[nX][nY] = true;
						q.offer(new Info(nX, nY));
						arr.add(new Info(nX, nY));
					}
				}
			}
		}
		
		go_down(arr, visited);
	}

	private static void go_down(ArrayList<Info> arr, boolean[][] visited) {
		boolean[][] visit = new boolean[R][C]; //임시
		
			ArrayList<Info> temp = new ArrayList<>();

			for(Info i:arr) {
				visit[i.x][i.y]= true; 
			}
//			System.out.println(arr.size()+"사이즈");
			boolean flag = false;
			for(int i=0; i<arr.size(); ++i) {
				int x = arr.get(i).x;
				int y = arr.get(i).y;
//				System.out.println("이얏호응"+x+","+y);
				if(x+1 < R) {
					if(input[x+1][y]=='x' && !visit[x+1][y]) {
						flag = true;
						break;						
					}
				}else {
					flag=true;
					break;
				}
				
			}
//			System.out.println("flag:"+flag);
//			System.out.println("============");
//			print();
			if(!flag) {
				for(int i=0; i<arr.size(); ++i) {
					input[arr.get(i).x][arr.get(i).y] = '.'; 
					visited[arr.get(i).x][arr.get(i).y] = false; 
				}
				for(int i=0; i<arr.size(); ++i) {
					input[arr.get(i).x+1][arr.get(i).y] = 'x';
					temp.add(new Info(arr.get(i).x+1, arr.get(i).y));
					visited[arr.get(i).x+1][arr.get(i).y] = true;
				}
				
//				System.out.println("=========이후");
//				print();
				go_down(temp, visited);
				
			}
			
			
		
	}

}
