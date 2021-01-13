import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	private static int V;
	private static int E;
	private static int[][] input;
	private static final int inf = 10000000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		input = new int[V+1][V+1];
		for(int i=1; i<=V; ++i) {
			for(int j=1; j<=V; ++j) {
				input[i][j] = inf; //일단 다 갈수 없다고 정의하고 ..(inf값을 통해)
			}
		}
		
		for(int i=0; i<E; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			if(input[from][to] > dist) { //똑같이 from->to로 갈때 dist값이 지금보다 크면 저장할 필요없다. 최솟값 찾는 거니까 애초에 그냥 걸러주자
				input[from][to] = dist;
			}
		}
		int min = Integer.MAX_VALUE;
		
		for(int k=1; k<=V; ++k) {
			for(int i=1; i<=V; ++i) {
				for(int j=1; j<=V; ++j) {
					if(input[i][j] > input[i][k] + input[k][j])
						input[i][j] = input[i][k] + input[k][j];
					
				}
			}
		}
	
		for(int i=1; i<=V; ++i) {
			for(int j=1; j<=V; ++j) {
				//사이클을 찾아야 한다고했으니까
				if(i==j) continue; //같은 정점이면 넘어가
				if(input[i][j]==inf) continue; //i->j로 못온다(inf다?)면 넘어가
				if(input[j][i]==inf) continue; //반대로 j->i도 못온다(inf다) 그러면 넘어가
				if(input[i][j] + input[j][i] < min) { //i->j + j->i (사이클 이니까 이쪽 왔다가 저쪽 왔다가 값을 다 더해줘야댐)이 min보다 작으면 갱신
					min = input[i][j] + input[j][i];
				}
				
			}
		}
		
		System.out.println(min==Integer.MAX_VALUE?"-1":min);
	}
}
