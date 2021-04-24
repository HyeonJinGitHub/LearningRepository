import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	private static int N;
	private static int M;
	private static int K;
	private static int[][] input;
	private static ArrayList[][] list;
	private static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	private static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	private static ArrayList<int[]> fireball;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		input = new int[N][N];
		list = new ArrayList[N][N];
		fireball = new ArrayList<int[]>();
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				list[i][j] = new ArrayList<Integer>();
			}
		}
		
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			fireball.add(new int[] {r-1, c-1, m, d, s});
//			list[r-1][c-1].add(i);
		}
		
		
		for(int i=0; i<K; ++i) {
//			System.out.println("========================"+i);
			move();
			bomb();
//			for(int j=0; j<fireball.size(); ++j) {
//				System.out.println(Arrays.toString(fireball.get(j)));
//			}
		}
		int anw = 0;
		for(int i=0; i<fireball.size(); ++i) {
//			System.out.println(Arrays.toString(fireball.get(i)));
			anw += fireball.get(i)[2];
		}
		System.out.println(anw);
	}

	private static void bomb() {
		ArrayList<int[]> movefireball = new ArrayList<>();
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				if(list[i][j].size()>0) {
					if(list[i][j].size()==1) {
						int[] ball = fireball.get((int)list[i][j].get(0));
						movefireball.add(ball);
					}else {
						int odd = 0;
						int even = 0;
						int newM = 0;
						int newS = 0;
						for(int k=0; k<list[i][j].size(); ++k) {
							int[] temp = fireball.get((int)list[i][j].get(k));
							
							newM += temp[2];
							newS += temp[4];
							
							if(temp[3]%2==0) {
								even++;
							}else odd++;
						}
						newM = newM/5;
						if(newM==0) continue;
						
						newS = newS/list[i][j].size();
						
						if(even==list[i][j].size() || odd==list[i][j].size()) {
							movefireball.add(new int[] {i, j, newM, 0, newS});
							movefireball.add(new int[] {i, j, newM, 2, newS});
							movefireball.add(new int[] {i, j, newM, 4, newS});
							movefireball.add(new int[] {i, j, newM, 6, newS});
						}else {
							movefireball.add(new int[] {i, j, newM, 1, newS});
							movefireball.add(new int[] {i, j, newM, 3, newS});
							movefireball.add(new int[] {i, j, newM, 5, newS});
							movefireball.add(new int[] {i, j, newM, 7, newS});							
						}
					}
				}
			}
		}
		fireball = movefireball;
	}

	private static void move() {
		ArrayList<int[]> movefireball = new ArrayList<>();
		
		clear();
		for(int i=0; i<fireball.size(); ++i) {
			
			int[] temp = fireball.get(i);
//			System.out.println(Arrays.toString(temp ));
			int r = temp[0]; int c = temp[1];
			int m = temp[2]; int d = temp[3]; int s = temp[4];
			
			for(int j=0; j<s; ++j) {
				int nX = r + dx[d];
				int nY = c + dy[d];
				
//				System.out.println(nX+","+nY);
				if(0<=nX && nX < N && 0<=nY && nY < N) {
					r = nX;
					c = nY;
				}else {
					if(nX < 0) {
						r = N-1;
					}
					
					if( nX >= N) {
						r = 0;
					}
					
					if(nY < 0) {
						 c = N-1;
					}
					
					if(nY >= N) {
						c = 0;
					}
				}
			}
			
			
			movefireball.add(new int[] {r, c, m, d, s});
			list[r][c].add(i);
		}
		fireball = movefireball;
	}

	private static void clear() {
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				list[i][j].clear();
			}
		}
		
	}
}
