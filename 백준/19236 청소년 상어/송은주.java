import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class fish{
	int num;
	int dir;
	public fish(int num, int dir) {
		super();
		this.num = num;
		this.dir = dir;
	}
	@Override
	public String toString() {
		return "[num=" + num + ", dir=" + dir + "]";
	}
	
	
}
class Point{
	int x;
	int y;
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
}
public class Main{
	private static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	private static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	private static Point shark;
	private static int anw;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		fish[][] input = new fish[4][4];
		Point[] fishes = new Point[17];
		for(int i=0; i<4; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<4; ++j) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				input[i][j] = new fish(num, dir-1);
				fishes[num] = new Point(i, j);
			}
		}
		shark = new Point(0, 0);
		int sharkDir = input[0][0].dir;
		anw += input[0][0].num;
		input[0][0].dir = -1;
		int sum = anw;
		dfs(0,0, sharkDir, input, fishes, sum);
		
		System.out.println(anw);
		
	}
	private static void dfs(int x, int y, int dir, fish[][] input, Point[] fishes, int sum) {
		Point[] newFishes = new Point[17];
		fish[][] newInput = rotate(input, fishes, newFishes, x, y);
		//shark moving
		for(int z=0; z<3; ++z) {
			int nX = x + dx[dir];
			int nY = y + dy[dir];
			
			if(0<=nX && nX < 4 && 0<=nY && nY < 4) {
				if(newInput[nX][nY].dir==-1) {
					x = nX; y = nY;
					continue;
				}
				//다시 복사
				fish[][] NEWINPUT = new fish[4][4];
				Point[] NEWFISHES = new Point[17];
				for(int i=0; i<4; ++i) {
					for(int j=0; j<4; ++j) {
						NEWINPUT[i][j] = newInput[i][j];
					}
				}
				for(int i=1; i<17; ++i) {
					NEWFISHES[i] = newFishes[i];
				}
				int newDir = NEWINPUT[nX][nY].dir;
				NEWINPUT[nX][nY].dir=-1;
				
				dfs(nX, nY, newDir, NEWINPUT, NEWFISHES, sum+NEWINPUT[nX][nY].num);
				x = nX; y = nY;
                NEWINPUT[nX][nY].dir = newDir;
			}else {
				if(anw<sum) anw = sum;
				return;
			}
		}
	
		
	}
	private static fish[][] rotate(fish[][] input, Point[] fishes, Point[] newFishes, int sx, int sy) {
		fish[][] newInput = new fish[4][4];
		for(int i=0; i<4; ++i) {
			for(int j=0; j<4; ++j) {
				fish k = new fish(input[i][j].num, input[i][j].dir);
				newInput[i][j] = k;
			}
		}
		for(int i=1; i<17; ++i) {
			Point p = new Point(fishes[i].x, fishes[i].y);
			newFishes[i] = p;
		}
		///copy
		for(int i=1; i<17; ++i) {
			if(newInput[newFishes[i].x][newFishes[i].y].dir==-1) continue;
			
			int x = newFishes[i].x;
			int y = newFishes[i].y;
			int dir = newInput[x][y].dir;
			
			for(int temp = 0; temp<8; ++temp) {
				int nX = x + dx[dir];
				int nY = y + dy[dir];
				if(0<=nX && nX < 4 && 0<=nY && nY < 4) {
					if(nX==sx && nY==sy) {
						nX -= dx[dir];
						nY -= dy[dir];
						
						dir = (dir+1)%8;
						x = nX;
						y = nY;
					}else {
						//A<->B(좌표)
						Point p = newFishes[newInput[nX][nY].num];
						newFishes[newInput[nX][nY].num]= newFishes[newInput[x][y].num];
						newFishes[newInput[x][y].num] = p;
						//A<->B(배열)
						fish f = newInput[nX][nY];
						newInput[nX][nY] = newInput[x][y];
						newInput[x][y] = f;
						newInput[nX][nY].dir = dir;

						break;
					}
				}else {
					nX -= dx[dir];
					nY -= dy[dir];
					
					dir = (dir+1)%8;
					x = nX;
					y = nY;
				}
			}
		}
		return newInput;
	}

	
	
}
