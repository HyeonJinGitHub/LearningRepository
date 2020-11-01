import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

	static int row,col;
	static char map[][];
	static int startX,startY;
	static boolean[][][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		map = new char[row][col];
		visited = new boolean[row][col][1<<6]; // low번호 열쇠 a=0부터 high 번호 열쇠 f=5까지 모두 가지는 상태를 표현하기 위해 1<<6
														// 1<<6 ==> 1000000 :     모두 열쇠를 가질때 비트 111111 
		
		char chArr[],ch;
		for(int i=0; i<row; ++i) {
			chArr = br.readLine().toCharArray();
			for(int j=0; j<col; ++j) {
				ch = chArr[j];
				if(ch=='0') {//민식이 위치
					startX = i;
					startY = j;
					ch = '.';
				}
				map[i][j] = ch;
			}
		}
		
		Queue<int[]> queue = new LinkedList<int[]>();
		visited[startX][startY][0] = true;
		queue.offer(new int[] {startX,startY,0,0}); // x,y,key,count
		
		int temp[],nx,ny,newKey=0;
		int result = -1;
		L:while(!queue.isEmpty()) {
			temp = queue.poll();
			
			for(int d=0; d<4; ++d) {
				nx = temp[0]+dx[d];
				ny = temp[1]+dy[d];
				newKey = temp[2];
				
				if(nx<0 || nx >= row || ny<0 || ny>=col  // 경계를 벗어나거나
						|| map[nx][ny] == '#') { // 벽이거나
					continue;
				}
				
				if(map[nx][ny] == '1') {// 출구이면
					result = temp[3]+1;
					break L;
				}  
				
				//이동할 위치에 문이 있다면 기존 열쇠들로 문을 열수 있는지 판단
				if(map[nx][ny]>='A' && map[nx][ny]<='F') {
					if((newKey & 1<<(map[nx][ny]-'A')) == 0 ) { //열쇠 없음
						continue;
					}
				//이동할 위치에 열쇠가 있다면 기존 열쇠의 조합에 처리	
				}else if(map[nx][ny]>='a' && map[nx][ny]<='f') {
					newKey |= (1<<map[nx][ny]-'a');
				}
				
				if(!visited[nx][ny][newKey]) {
					visited[nx][ny][newKey] = true;
					queue.offer(new int[] {nx,ny,newKey,temp[3]+1});
				}
			}// end for(direction)
		}// end while(queue)
		
		System.out.println(result);
	}

}
