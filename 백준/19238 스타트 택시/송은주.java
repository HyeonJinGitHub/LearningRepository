import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Info{
	int startX;
	int startY;
	int endX;
	int endY;
	public Info(int startX, int startY, int endX, int endY) {
		super();
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
	}
	public Info(int startX, int startY) {
		super();
		this.startX = startX;
		this.startY = startY;
	}
}

class Main{
	private static int N;
	private static boolean[][] input;
	private static int M;
	private static int F;
	private static int[][] people;
	private static Info taxi;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		
		input = new boolean[N][N];
		people = new int[N][N];
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; ++j) {
				input[i][j] = Integer.parseInt(st.nextToken())==0?true:false;
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		
		taxi = new Info(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1);
		ArrayList<Info>arr = new ArrayList<>();
		arr.add(new Info(-1, -1));
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int startX = Integer.parseInt(st.nextToken())-1;
			int startY = Integer.parseInt(st.nextToken())-1;
			int endX = Integer.parseInt(st.nextToken())-1;
			int endY = Integer.parseInt(st.nextToken())-1;
			
			people[startX][startY] = i+1;
			
			arr.add(new Info(startX, startY, endX, endY));
		}
		//x 는 몇번째인지, y는 얼마나 가야하는지..
		for(int i=0; i<M; ++i){
//			System.out.println("============================");
			Info c = chk();
			if(people[taxi.startX][taxi.startY]!=0) {
				c.startX = people[taxi.startX][taxi.startY];
				c.startY = 0;
				people[taxi.startX][taxi.startY]= 0; 
			}
			if(c.startX==-1) {
//				System.out.println("chkbra");
				F = -1;
				break;
			}
//			System.out.println(c.startX+","+c.startY);
			
			int tempX = arr.get(c.startX).startX;
			int tempY = arr.get(c.startX).startY;
			
			int driveTemp = drive(tempX, tempY, arr.get(c.startX).endX, arr.get(c.startX).endY);
//			System.out.println(driveTemp);
			
			if(driveTemp==-1) {
				F = -1;
//				System.out.println("drive");
				break;
			}
			people[tempX][tempY] = 0;
			F = F - c.startY;
//			System.out.println("F는=>"+F);
			if(driveTemp <= F) {
				F = F - driveTemp;
//				System.out.println();
				F = F + driveTemp*2;
				taxi.startX = arr.get(c.startX).endX;
				taxi.startY = arr.get(c.startX).endY;
//				System.out.println("택시좌표:"+taxi.startX+","+taxi.startY);
			}else {
//				System.out.println("i cant go");
				F = -1;
				break;
			}
//			System.out.println("F는=>ㅋㅋ"+F);
		}
		System.out.println(F);
		
	}

	private static int drive(int tempX, int tempY, int endX, int endY) {
//		System.out.println((tempX+1)+","+(tempY+1)+","+(endX+1)+","+(endY+1));
		int[][] visited = new int[N][N];
		int result = -1;
		Queue<Info>q = new LinkedList<>();
		
		q.offer(new Info(tempX, tempY));
		
		visited[tempX][tempY] = 1;
		
		while(!q.isEmpty()) {
			//print(visited);
			int x = q.peek().startX;
			int y = q.poll().startY;
//			System.out.println(x+","+y);
			for(int k=0; k<4; ++k) {
				int nX = x + dx[k];
				int nY = y + dy[k];
				
				if(0<=nX && nX < N && 0<=nY && nY < N) {
					if(!input[nX][nY]) continue;
					if(visited[nX][nY]==0) {						
						visited[nX][nY] = visited[x][y] + 1;
						if(nX==endX && nY==endY) {
							return visited[nX][nY]-1;
						}
						
						q.offer(new Info(nX, nY));
					}
				}
			}
		}
		
		return result;
	}

	private static void print(int[][] visited) {

		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				System.out.print(visited[i][j] + "\t");
			}
			System.out.println();
		}
	}

	private static Info chk() {
		int[][] visited = new int[N][N];
		Info result = new Info(-1, -1);
		Queue<Info>q = new LinkedList<Info>();
		
		q.offer(new Info(taxi.startX, taxi.startY));
		visited[taxi.startX][taxi.startY]= 1;
		
		int min = 999999999;
		
		while(!q.isEmpty()) {
			int x = q.peek().startX;
			int y = q.poll().startY;
			
			if(visited[x][y] > F) {
				continue;
			}
			
			
			for(int k=0; k<4; ++k) {
				int nX = x + dx[k];
				int nY = y + dy[k];
				
				if(0<= nX && nX < N && 0 <= nY && nY < N) {
					if(!input[nX][nY]) continue;
					if(visited[nX][nY]==0) {
						q.offer(new Info(nX, nY));
						visited[nX][nY] = visited[x][y] + 1;
						if(people[nX][nY]!=0) {
							if(min > visited[nX][nY]) {
								min = visited[nX][nY];
							}							
						}
					}
				
				}
			}
		}
		/*
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				System.out.print(people[i][j] + "\t");
			}
			System.out.println();
		}
		
		System.out.println("=============================");
		
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				System.out.print(visited[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("======================="+min);
		*/
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				if(people[i][j]!=0 && visited[i][j]==min) {
					result.startX = people[i][j];
					result.startY = min-1;
					return result;
				}
			}
		}
		
		
		return result;
	}
}
