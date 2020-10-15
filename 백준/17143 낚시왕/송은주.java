import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class Info{
	int num;
	boolean dead = true;  //true:살아있음 false:죽음
	
	int r;
	int c;
	int s;
	int d;
	int z;
	public Info(int num, int r, int c, int s, int d, int z) {
		super();
		this.num = num;
		this.r = r;
		this.c = c;
		this.s = s;
		this.d = d;
		this.z = z;
	}
	public Info(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}
	@Override
	public String toString() {
		return "Info [num=" + (num+1) + ", dead=" + dead + ", r=" + (r+1) + ", c=" + (c+1) + ", s=" + s + ", d=" + d + ", z=" + z
				+ "]";
	}
	
	
}
public class Main{
	private static int R;
	private static int C;
	private static int M;
	private static ArrayList[][] arr;
	private static Info[] sharks;
	private static Info people;
	private static int[] dx = {0, -1, 1, 0, 0};
	private static int[] dy = {0, 0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
	
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList[R][C];
		sharks = new Info[M];
		people = new Info(-1, -1);
		for(int i=0; i<R; ++i) {
			for(int j=0; j<C; ++j) {
				arr[i][j] = new ArrayList<Integer>();
			}
		}
		
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			sharks[i] = new Info(i, r-1, c-1, s, d, z);
			arr[r-1][c-1].add(i);
//			System.out.println(sharks[i].toString());
			
		}
		
		int anw = 0;
		for(int i=0; i<C; ++i) {
			//나씨왕 한칸 이동
			people.c+=1;
			//낙시왕 하나 먹음
			for(int j=0; j<R; ++j) {
				if(arr[j][people.c].size()==1) {
					int k = (int)arr[j][people.c].get(0);
//					System.out.println((k+1)+"상어 먹음");
					sharks[k].dead = false;
					anw += sharks[k].z;
					break;
				}
			}
			//System.out.println("===========");
			//상어 이동
			move();
//			System.out.println("이동 후 결과");
//			print();
			//상어 잡아먹
			kill();
//			System.out.println("==============");
		}
		
		System.out.println(anw);
	
	}

	private static void print() {
		for(int i=0; i<sharks.length; ++i) {
			System.out.println(sharks[i].toString());
		}
		
	}

	private static void kill() {
//		System.out.println("상어 잡아먹으러옴");
		for(int i=0; i<R; ++i) {
			for(int j=0; j<C; ++j) {
				if(arr[i][j].size()>1) {
//					System.out.println((i+1)+","+(j+1)+"일 때");
					ArrayList<Info>temp = new ArrayList<>();
					for(int k=0; k<arr[i][j].size(); ++k) {
						int t = (int) arr[i][j].get(k);
//						System.out.println((t+1)+"상어입니당.");
						temp.add(new Info(t, sharks[t].z));
					}
					
					Collections.sort(temp, new Comparator<Info>() {
						@Override
						public int compare(Info o1, Info o2) {
							return o2.c - o1.c;
						}
					});
					/*
					for(int z =0 ; z<temp.size(); ++z) {
						System.out.println(temp.get(z).r+"ㅋㅋㅋ"+temp.get(z).c);
					}
					*/
					int t = (int) temp.get(0).r; //상어 가 크기가 큰게 잡아먹는거임.
					//번호랑 관련없을무
					arr[i][j].clear();
					arr[i][j].add(t);
					
					for(int k=1; k<temp.size(); ++k) {
						int s = temp.get(k).r;
//						System.out.println((s+1)+"상어냠");
						sharks[s].dead = false;
					}
				}
			}
		}
		
	}

	private static void move() {
		//상어 하나씩 이동
		//이전에 arr초기화
		for(int a=0; a<R; ++a) {
			for(int b=0; b<C; ++b) {
				arr[a][b].clear();
			}
		}
		//상어 하나씩 이동
		for(int i=0; i<sharks.length; ++i) {
			if(!sharks[i].dead) continue; //죽은 상어면 넘어가
//			System.out.println((i+1)+"번째상어입민당");
			Info I = sharks[i];
			int s = -1;
			if(I.d==1 || I.d==2) {
				s = I.s%((R-1)*2);
			}else if(I.d==3 || I.d==4) {
				s = I.s%((C-1)*2);
			}
//			System.out.println(s+"만큼 왕복");
			for(int k=0; k<s; ++k) {
//				System.out.println("원래좌표=>"+I.r+","+I.c);
				int nX = I.r + dx[I.d];
				int nY = I.c + dy[I.d];
//				System.out.println(nX+","+nY);
				
				if(0<=nX && nX < R && 0<=nY && nY < C) {
					I.r = nX;
					I.c = nY;
				}else {
//					System.out.println("방향 바꿔야 할듯");
					I.d = _toggle(I.d);
					nX = I.r + dx[I.d];
					nY = I.c + dy[I.d];
					
					I.r = nX;
					I.c = nY;
//					System.out.println("방향 바꿔서 다시 좌표 "+nX+","+nY);
//					System.out.println(I.d);
				}
			}
			
			sharks[i] = I;
			arr[sharks[i].r][sharks[i].c].add(i);
		}
		
	}

	private static int _toggle(int d) {
		switch(d) {
		case 1: return 2;
		case 2: return 1;
		case 3: return 4;
		case 4: return 3;
		}
		return -1;
	}
}
