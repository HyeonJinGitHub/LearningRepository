import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
	private static int B;
	private static int A;
	private static int[][] arr;
	private static int[] dx = {-1, 0, 1, 0};
	private static int[] dy = {0, 1, 0, -1};
	private static int N;
	private static int M;
	private static int[] dir;
	private static ArrayList<int[]> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		B = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		
		arr = new int[A][B];
		
		st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList<int[]>();
		list.add(new int[] {-1, -1});
		
		dir = new int[N+1];
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			char d = st.nextToken().charAt(0);
			dir[i+1] = __getDir(d);
			arr[A-x][y-1] = i+1;
			
			list.add(new int[] {A-x, y-1});
		}
		
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int robot = Integer.parseInt(st.nextToken());
			char goDir = st.nextToken().charAt(0);
			int loofCount = Integer.parseInt(st.nextToken());
			
			
			if(!go(robot, goDir, loofCount)) return;
			
		}
		System.out.println("OK");
	}

	private static boolean go(int robot, char goDir, int loofCount) {
		int x = list.get(robot)[0];
		int y = list.get(robot)[1];
		
		for(int i=0; i<loofCount; ++i) {
			switch(goDir) {
			case 'L':
				dir[robot] -= 1;
				if(dir[robot]==-1) {
					dir[robot] = 3;
				}
				break;
			case 'R':
				dir[robot] = (dir[robot]+1)%4;
				break;
			case 'F':
				int nX = x + dx[dir[robot]];
				int nY = y + dy[dir[robot]];
				
//				System.out.println(nX+","+nY);
				if(0<=nX && nX < A && 0 <= nY && nY < B) {
					if(arr[nX][nY]!=0) {
						StringBuilder sb = new StringBuilder();
						sb.append("Robot ").append(robot).append(" crashes into robot ").append(arr[nX][nY]);
						System.out.println(sb);
						return false;
					}
					
					arr[x][y] = 0;
					arr[nX][nY] = robot;
					
					list.get(robot)[0] = nX;
					list.get(robot)[1] = nY;
					x = nX; y = nY;
					
				}else {
					StringBuilder sb = new StringBuilder();
					sb.append("Robot ").append(robot).append(" crashes into the wall");
					System.out.println(sb);
					return false;
				}
				break;
			}
//			print();
		}
		
		return true;
	}

	private static void print() {
		for(int i=0; i<A; ++i) {
			for(int j=0; j<B; ++j) {
				System.out.print(arr[i][j] +"\t");
			}
			System.out.println();
		}
		
	}

	private static int __getDir(char d) {
		switch(d) {
		case 'N':
			return 0;
		case 'E':
			return 1;
		case 'S':
			return 2;
		case 'W':
			return 3;
		}
		return -1;
	}
}
