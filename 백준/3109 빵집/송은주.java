import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int C;
	private static int R;
	private static char[][] map;
	private static boolean[][] visited;
	private static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
	
		map = new char[R][];
		visited = new boolean[R][C];
		for(int i=0; i<R; ++i) {
			map[i] = br.readLine().toCharArray();
		}
		
		makePipe();
		System.out.println(cnt);
	}

	private static void makePipe() {
		//0열의 빵집 모든 행의 위치에서 가스관 놓기 시작
		for(int i=0; i<R; ++i) {
			visited[i][0] = true;
			go(i, 0);
		}
		
	}
	
	public static int[] dx = {-1, 0, 1};
//	public static int[] dy = {1, 1, 1};
	
	//현 위치에서 오른쪽대각선위, 오른쪽, 오른쪽대각선아래 순서적으로 까스관 연결시도
	private static boolean go(int x, int y) {
		if(y==C-1) { //끄까지 연결된 상황
			cnt+=1;
			return true;
		}
		for(int k=0; k<3; ++k) {
			int nX = x + dx[k];
			int nY = y + 1;
			if(0<=nX && nX < R) {
				if(visited[nX][nY]) continue;
				if(map[nX][nY]=='x') continue;
				
				visited[nX][nY] = true; //놓기 (지나간 녀석들, 놓았다 라는 뜻이 아니다. 그냥 들렸다는 뜻 헷갈리지 말것 )
										//BFS라면 좀 달라질지도 하지만 얘는 그냥 여기를 왓다 이런뜻이고 길이가 상관이 없자나 그냥 마지막까지 가면되는지 그것만 확인하면 되는거니까..
										//밑에서 위로 올라와서 이 visited를 만난다고 해도 그 뒤로는 똑같은 시나리오 되는거시야(진행 시나리오 같다!)
				if(go(nX, nY)) { //다음 칸으로 이동 후 진행 결과가 결국 끝까지 연결이 가능했다면
					return true; //현 위치에서 다른 방향으로 놓기 시도를 중단하고 이전 위치로 돌아가
				}
				
//				visited[nX][nY] = false; //이거있으면 가지치기 하는게 아님 (불필요한 작업 계속)
				//갈필요가 없는거슬을 또 체크를 해주는거야 이 코드가 잇다면..
			}
		}
		
		return false;
		
	}
}//end of class
