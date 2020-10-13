import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
class Info{
	int x;
	int y;
	int dir;
	public Info(int x, int y, int dir) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
	}	
}
public class Main{
	private static int[] dx = {0, 0, 0, -1, 1};
	private static int[] dy = {0, 1, -1, 0, 0};
	private static int N;
	private static int K;
	private static ArrayList[][] input;
	private static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		input = new ArrayList[N][N];
		map = new int[N][N];
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
				input[i][j] = new ArrayList<Integer>();
			}
		}
		ArrayList<Info> arr = new ArrayList<>();
		for(int i=0; i<K; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			
			input[x-1][y-1].add(i);
			arr.add(new Info(x-1, y-1, dir));
			
		}
		
		int T = 0;
		while(true) {
			if(T>1000) break;
			for(int a=0; a<N; ++a) {
				for(int b=0; b<N; ++b) {
					if(input[a][b].size()>=4) {
						System.out.println(T);
						return;
					}
				}
			}
			T+=1;
			for(int i=0; i<K; ++i) {
				Info I = arr.get(i);
				int x = I.x; int y = I.y; int dir = I.dir;
				int nX = x + dx[dir];
				int nY = y + dy[dir];
				
				List<Integer>temp = input[x][y];
				List<Integer>go = temp.subList(temp.indexOf(i), temp.size()); //잘라낸 거
				List<Integer>copyGo = new ArrayList<>(go);
				if(temp.size()>=4) {
					System.out.println(T);
					return;
				}
				
				if(0<=nX && nX < N && 0 <=nY && nY < N) {
					switch(map[nX][nY]) {
					case 0: //흰색
						input[nX][nY].addAll(copyGo);
						input[x][y].removeAll(copyGo);
						int length = copyGo.size();
						for(int a=0; a<length; ++a) {
							int t = copyGo.get(a);
							arr.get(t).x = nX;
							arr.get(t).y = nY;
						}
						break;
					case 1: //빨간색 순서 바꿈.
						input[x][y].removeAll(copyGo);
						Collections.reverse(copyGo);
						input[nX][nY].addAll(copyGo);
						
						for(int a=0; a<copyGo.size(); ++a) {
							int t = copyGo.get(a);
							arr.get(t).x = nX;
							arr.get(t).y = nY;
						}
						
						if(input[nX][nY].size()>=4) {
							System.out.println(T);
							return;
						}
						
						break;
					case 2: //파란색
						dir = _toggle(dir);
						nX = x + dx[dir];
						nY = y + dy[dir];
						if(0<=nX && nX < N &&0<=nY && nY < N) {
							switch(map[nX][nY]) {
							case 0:
								input[nX][nY].addAll(copyGo);
								input[x][y].removeAll(copyGo);
								
								for(int a=0; a<copyGo.size(); ++a) {
									int t = copyGo.get(a);
									arr.get(t).x = nX;
									arr.get(t).y = nY;
								}
								
								if(input[nX][nY].size()>=4) {
									System.out.println(T);
									return;
								}
								break;
							case 1:
								input[x][y].removeAll(copyGo);
								Collections.reverse(copyGo);
								input[nX][nY].addAll(copyGo);
								
								for(int a=0; a<copyGo.size(); ++a) {
									int t = copyGo.get(a);
									arr.get(t).x = nX;
									arr.get(t).y = nY;
								}
								
								if(input[nX][nY].size()>=4) {
									System.out.println(T);
									return;
								}
								break;
							case 2:
								nX = x;
								nY = y;
								break;
							}
						}else {
							nX = x;
							nY = y;
						}
						break;
					}
				}else {
					dir = _toggle(dir);
					nX = x + dx[dir];
					nY = y + dy[dir];
					if(0<=nX && nX < N &&0<=nY && nY < N) {
						switch(map[nX][nY]) {
						case 0:
							input[nX][nY].addAll(copyGo);
							input[x][y].removeAll(copyGo);
							
							for(int a=0; a<copyGo.size(); ++a) {
								int t = copyGo.get(a);
								arr.get(t).x = nX;
								arr.get(t).y = nY;
							}
							
							if(input[nX][nY].size()>=4) {
								System.out.println(T);
								return;
							}
							break;
						case 1:
							input[x][y].removeAll(copyGo);
							Collections.reverse(copyGo);
							input[nX][nY].addAll(copyGo);
							
							for(int a=0; a<copyGo.size(); ++a) {
								int t = copyGo.get(a);
								arr.get(t).x = nX;
								arr.get(t).y = nY;
							}
							
							if(input[nX][nY].size()>=4) {
								System.out.println(T);
								return;
							}
							break;
						case 2:
							nX = x;
							nY = y;
							break;
						}
					}else {
						nX = x;
						nY = y;
					}
				}
				
				arr.get(i).x = nX;
				arr.get(i).y = nY;
				arr.get(i).dir = dir;
			}
		}
		System.out.println(-1);
		
	}
	private static void print() {
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				System.out.print(i+","+j+"일떄\t");
				
				for(int k=0; k<input[i][j].size(); ++k)
					System.out.print(input[i][j].get(k)+"\t");
				System.out.println();
			}
			
		}
		
	}
	private static int _toggle(int dir) {
		switch(dir) {
		case 1:
			return 2;
		case 2:
			return 1;
		case 3:
			return 4;
		case 4:
			return 3;
		}
		return 0;
	}
}
