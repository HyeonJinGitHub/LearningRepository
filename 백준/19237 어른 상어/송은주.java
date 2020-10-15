import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Info{
	int num;
	boolean dead = true; //false면 죽음 true면 살음
	int x;
	int y;
	int dir;
	int[][] pridir = new int[4][5];


	public Info(int num, boolean dead, int x, int y, int dir, int[][] pridir) {
		super();
		this.num = num;
		this.dead = dead;
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.pridir = pridir;
	}


	public Info(int num) {
		super();
		this.num = num;
	}


	@Override
	public String toString() {
		return "Info [num=" + num + ", dead=" + dead + ", x=" + x + ", y=" + y + ", dir=" + dir + ", pridir="
				+ Arrays.toString(pridir) + "]";
	}


	
	
	
	
	
}
public class Main{
	private static int N;
	private static int M;
	private static int K;
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};
	private static int[][] pero; //pero몬 유효 시간
	private static int[][] peroNum; //어떤상어가 페로몬뿌렷
	private static ArrayList[][] sharkPos;
	private static Info[] sharks;
	private static int[][] input;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		//1 번 상어 남을 때 까 지 임
		pero = new int[N][N];
		peroNum = new int[N][N];
		sharkPos = new ArrayList[N][N];
		sharks = new Info[M+1];
		input = new int[N][N];
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; ++j) {
				sharkPos[i][j] = new ArrayList<Integer>();
				input[i][j] = Integer.parseInt(st.nextToken());
				if(input[i][j]!=0) {
					sharkPos[i][j].add(input[i][j]);
					pero[i][j] = K;
					peroNum[i][j] = input[i][j];
					sharks[input[i][j]] = new Info(input[i][j]);
					sharks[input[i][j]].x = i;
					sharks[input[i][j]].y = j;
				}
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<M; ++i) {
			sharks[i+1].dir = Integer.parseInt(st.nextToken())-1;
		}
		for(int i=0; i<M; ++i) {
			int[][] temp = new int[4][5];
			temp[0][0] = 0;
			temp[1][0] = 1;
			temp[2][0] = 2;
			temp[3][0] = 3;
			for(int j=0; j<4; ++j) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int k=1; k<=4; ++k) {
					temp[j][k] = Integer.parseInt(st.nextToken())-1;
				}
			}
			
			sharks[i+1].pridir = temp;
		}
		
		int anw = 0;
		int fuel =0;
		while(true) {
			if(chk()) /*fuel=1;*/break; // 같은 공간에 있으면 죽이는 작업 및 1인지 아닌지 판단
			if(anw>1000)/*fuel = 2;*/ break;   //만약에 시간 추가된게 1000이면 끝
			anw += 1; // 1000이상 아니고 1도 아니니까 +1
			
			
			// 상어 이동
			move();
			//상어 죽어
			kill();
			//1씩 줄여
			//냄새 뿌려
			diffuse();
			
//			System.out.println("한 번 끝");
//			print();
		}
		
		if(anw>1000) anw = -1;
		System.out.println(anw);
	}
	private static void print() {
		System.out.println("페로몬 남은횟수");
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				System.out.print(pero[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("=================");
		System.out.println("페로몬 뿌린상어번호");
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				System.out.print(peroNum[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("=================");
		System.out.println("아기상어뚜루루");
		for(int i=1; i<=M; ++i) {
			System.out.println(sharks[i].toString());
		}
	}
	private static void diffuse() {
		//1씩 줄여
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				if(pero[i][j]==0) continue;
				pero[i][j]--;
				if(pero[i][j]<=0) {
					pero[i][j] = 0;
					peroNum[i][j] = 0;
				}
			}
		}
		//냄새 뿌려
		
		for(int i=1; i<=M; ++i) {
			Info I = sharks[i];
			if(!I.dead) continue;
			pero[I.x][I.y]= K;
			peroNum[I.x][I.y] = I.num;
		}
		
	}
	private static void kill() {
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				if(sharkPos[i][j].size()>1) {
					Collections.sort(sharkPos[i][j]);
					int z = (int)sharkPos[i][j].get(0);
					for(int k=1; k<sharkPos[i][j].size(); ++k) {
						int p = (int)sharkPos[i][j].get(k);
//						System.out.println(p+"번 상어 죽음");
						//clr(p);
						sharks[p].dead = false;
					}
					
					sharkPos[i][j].clear();
					sharkPos[i][j].add(z);
				}
			}
		}
		//상어 죽여
		
		
	}
	
	private static void move() {
		//arraylist clear
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				sharkPos[i][j].clear();
			}
		}
		
		//상어 이동 (이동 전 리스트 비었음)
		for(int i=1; i<=M; ++i) {
			if(!sharks[i].dead) continue; //죽었으면 넘어가
//			System.out.println(i+"번쌍어");
			Info I = sharks[i];
//			System.out.println("zz냐옹"+I.dir);
			
			boolean flag = false;
			for(int j=1; j<=4; ++j) {
				int nDir = I.pridir[I.dir][j];
//				System.out.println("새로운dir은?"+nDir);
				int nX = I.x + dx[nDir];
				int nY = I.y + dy[nDir];
				
				if(0<=nX && nX < N && 0 <= nY && nY < N) { //범위 안에 들어옴
					if(peroNum[nX][nY]==0) { //페로몬 0 일 때
						I.x = nX;
						I.y = nY;
						I.dir = nDir;
						flag = true;
						break;
					}
				}
			}
			if(flag) {
				sharks[i] = I;
				sharkPos[I.x][I.y].add(I.num); //arr에 추가
				continue;
			}
			
			for(int j=1; j<=4; ++j) {
				int nDir = I.pridir[I.dir][j];
//				System.out.println("새로운dir은?"+nDir);
				int nX = I.x + dx[nDir];
				int nY = I.y + dy[nDir];
				
				if(0<=nX && nX < N && 0 <= nY && nY < N) { //범위 안에 들어옴
					if(peroNum[nX][nY]==I.num) { //페로몬 0 일 때
						I.x = nX;
						I.y = nY;
						I.dir = nDir;
						flag = true;
						break;
					}
				}
			}
			sharks[i] = I;
			sharkPos[I.x][I.y].add(I.num); //arr에 추가
		}
		
	}
	private static boolean chk() {
		//같은 공간에 있으면 죽이는 작업 및 1인지 아닌지 판단
		boolean flag = true; //true 면 1밖에 안 남음		

		
		for(int i=2; i<=M; ++i) {
			if(sharks[i].dead) {
//				System.out.println("1번 상어 외 살아 있음");
				flag = false;
				break;
			}
		}
//		System.out.println("상어 끝~~");
		return flag;
	}
}
