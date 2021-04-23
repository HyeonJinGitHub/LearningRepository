import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main{
	private static int N;
	private static int L;
	private static int R;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};
	private static int[][] input;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		input = new int[N][N];
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; ++j) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int anw =0;
		while(chk()) {
			anw++;
		}
		
		System.out.println(anw);
	}

	private static boolean chk() {
		boolean flag = false;
		boolean visited[][]  = new boolean[N][N];
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				if(!visited[i][j]) {
					visited[i][j] = true;
					
					ArrayList<int[]> arr = new ArrayList<>();
					Queue<int[]> q = new LinkedList<>();
					
					arr.add(new int[] {i, j});
					q.offer(new int[] {i, j, input[i][j]});
					
					while(!q.isEmpty()) {
						int[] temp = q.poll();
						
						for(int k=0; k<4; ++k) {
							int nX = temp[0] + dx[k];
							int nY = temp[1] + dy[k];
							
							if(0<=nX && nX < N && 0<=nY && nY < N) {
								int gap = Math.abs(temp[2]-input[nX][nY]);
								if(L<=gap && gap <= R && !visited[nX][nY]) {
									visited[nX][nY] = true;
									arr.add(new int[] {nX, nY});
									q.offer(new int[] {nX, nY, input[nX][nY]});
								}
							}
						}
					}
					
					if(arr.size()>1) {
						flag = true;
						int num = 0;
						for(int k=0; k<arr.size(); ++k) {
							num += input[arr.get(k)[0]][arr.get(k)[1]];
						
						}
						
						num = num / arr.size();
						for(int k=0; k<arr.size(); ++k) {
							input[arr.get(k)[0]][arr.get(k)[1]] = num;
						}
					}
				}
			}
		}
		return flag;
	}
}
