import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[] dx = {0, 1, 0, -1}; //시계 방향
	private static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int M = Integer.parseInt(st.nextToken()); // 열
		int N = Integer.parseInt(st.nextToken()); //행
		N+=1; M+=1;
		
		int[][] map = new int[N][M];
		int total = (N*2+M*2)-4;
		int k = Integer.parseInt(br.readLine());
		
		for(int i=0; i<k; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int dir = Integer.parseInt(st.nextToken());
			int diff = Integer.parseInt(st.nextToken());
			
			switch(dir) {
			case 1:
				map[0][diff] = i+1;
				break;
			case 2:
				map[N-1][diff] = i+1;
				break;
			case 3:
				map[diff][0] = i+1;
				break;
			case 4:
				map[diff][M-1] = i+1;
				break;
			}
			
		}

		st = new StringTokenizer(br.readLine(), " ");
		int dir = Integer.parseInt(st.nextToken());
		int diff = Integer.parseInt(st.nextToken());
		
		
		int store_x = 0;
		int store_y = 0;
		int go_dir = 0;

		switch(dir) {
		case 1: //북
			map[0][diff] = -1;
			store_x = 0; store_y = diff;
			go_dir = 0;
			break;
		case 2:
			map[N-1][diff] = -1;
			store_x = N-1; store_y = diff;
			go_dir = 2;
			break;
		case 3:
			map[diff][0] = -1;
			store_x = diff; store_y = 0;
			go_dir = 3;
			break;
		case 4:
			map[diff][M-1] = -1;
			store_x = diff; store_y = M-1;
			go_dir = 1;
			break;
		}
		
		int cnt = 0;
		int nX = store_x + dx[go_dir];
		int nY = store_y + dy[go_dir];
		int result = 0;
		
		while(true) {
			if(0<=nX && nX < N && 0<=nY && nY < M) {
				cnt += 1;
				if(map[nX][nY]!=-1 && map[nX][nY]!=0) {
					if(cnt <= total-cnt) {
						result += cnt;
					}else result += (total-cnt);
				}
				if(map[nX][nY]==-1 || cnt==total) break;
				//map[nX][nY]=999;
				nX = nX + dx[go_dir];
				nY = nY + dy[go_dir];
			}else {
				nX = nX - dx[go_dir];
				nY = nY - dy[go_dir];
				go_dir += 1;
				if(go_dir==4) {
					go_dir = 0;
				}
				nX = nX + dx[go_dir];
				nY = nY + dy[go_dir];
			}
		}
		
		System.out.println(result);
	}//end of main
}
