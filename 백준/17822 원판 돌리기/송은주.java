import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
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
	private static int N;
	private static int M;
	private static int T;
	private static int[][] input;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		input = new int[N][M];
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; ++j) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<T; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			for(int z = x-1; z<N; z+=x) {
				List<Integer> arr = new ArrayList<>();
				for(int j=0; j<M; ++j) {
					arr.add(input[z][j]);
				}
//				System.out.println(arr.toString());
				List<Integer> head;
				List<Integer> tail;
				ArrayList<Integer> last = new ArrayList<>();
				switch(d) {
				case 0: //시계
					head = arr.subList(M-k, arr.size());
					tail = arr.subList(0, M-k);
					
					last.addAll(head);
					last.addAll(tail);
					break;
				case 1: //반시계
					head = arr.subList(0, k+1);
					tail = arr.subList(k, M);
					
					last.addAll(tail);
					last.addAll(head);
					break;
				}
				
//				System.out.println(last.toString());
				for(int j=0; j<M; ++j) {
					input[z][j] = last.get(j);
				}
				
			}

			if(bfs()) {
				//true면 지운거 있음
//				System.out.println("지웠음");
				continue;
				
			}else {
				//false면 지운거 없음
				int size = 0;
				int total = 0;
//				System.out.println("못지웠음");
				
				for(int a=0; a<N; ++a) {
					for(int b=0; b<M; ++b) {
						if(input[a][b] ==0 ) continue;
						size+=1;
						total+=input[a][b];
					}
				}
				
				if(size==0) continue;
				double avg = (double)total/(double)size;
				
				for(int a=0; a<N; ++a) {
					for(int b=0; b<M; ++b) {
						if(input[a][b]==0) continue;
						if((double)input[a][b] > avg) {
							input[a][b]-=1;
						}else if((double)input[a][b] < avg) {
							input[a][b]+=1;
						}
					}
				}
			}
//				print();
		}
			
		int anw = 0;
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				anw += input[i][j];
			}
		}
		System.out.println(anw);
	}

	private static void print() {
		System.out.println("=====================");
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				System.out.print(input[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("=====================");
	}

	private static boolean bfs() {
		boolean flag = false;
		
		boolean[][] visited = new boolean[N][M];
		
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(input[i][j]==0) continue;
				if(visited[i][j]) continue;
				
				Queue<Info> q = new LinkedList<Info>();
				q.offer(new Info(i, j));
				visited[i][j] = true;
				
				ArrayList<Info> arr = new ArrayList<>();
				arr.add(new Info(i, j));
				
				while(!q.isEmpty()) {
					int x = q.peek().x;
					int y = q.poll().y;
					
					for(int k=0; k<4; ++k) {
						int nX = x + dx[k];
						int nY = y + dy[k];
						
						if(0<=nX && nX < N && 0 <= nY && nY < M) {
							if(input[x][y]==input[nX][nY] && !visited[nX][nY]) {
								visited[nX][nY] = true;
								arr.add(new Info(nX, nY));
								q.offer(new Info(nX, nY));
								flag = true;
							}
						}
					}
					
					if(y==0) {
						if(input[x][M-1]==input[x][y] && !visited[x][M-1]) {
							visited[x][M-1] = true;
							arr.add(new Info(x, M-1));
							q.offer(new Info(x, M-1));
							flag = true;
						}
					}else if(y==M-1) {
						if(input[x][0]==input[x][y] && !visited[x][0]) {
							visited[x][0] = true;
							arr.add(new Info(x, 0));
							q.offer(new Info(x, 0));
							flag= true;
						}
					}
				}
				if(arr.size()>1) {
					for(Info f:arr) {
//						System.out.println(f.x+","+f.y);
						input[f.x][f.y]=0;
					}
				}
				
				
			}
		}
		return flag;
	}
}
