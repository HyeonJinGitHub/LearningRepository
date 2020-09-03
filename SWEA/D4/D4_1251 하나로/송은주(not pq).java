import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	private static int N;
	private static long[][] adjMatrix;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T =Integer.parseInt(br.readLine().trim());
		
		for(int t=1; t<=T; ++t) {
			N = Integer.parseInt(br.readLine());
			int[] x = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N; ++i) {
				x[i] = Integer.parseInt(st.nextToken());
			}//N개 섬의 x좌표
			
			int[] y = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N; ++i) {
				y[i] = Integer.parseInt(st.nextToken());
			}//N개 섬의 y좌표
			
			adjMatrix = new long[N][N];
			for(int i=0; i<N; ++i) {
				for(int j=0; j<N; ++j) {
					adjMatrix[i][j] = adjMatrix[j][i] = getDistance(x[i],x[j],y[i],y[j]);
				}
			}
			double E = Double.parseDouble(br.readLine().trim());
			System.out.println("#" + t + " " + Math.round(E*makeMST())); //round 반올림 처리
			
		}
	}

	private static double makeMST() {
		// 크루스칼도 여기다가 해도되고 . 프림도 여기다가 해도된당.
		//프림
		long[] minEdge = new long[N];
		boolean[] visited = new boolean[N];
		
		long result = 0;//최소신장트리비용
		int cnt = 0;//처리한 정점수
		
		Arrays.fill(minEdge, Long.MAX_VALUE);
		minEdge[0] = 0;//0점을 시작점으로
		
		while(true) {
			//1.신장트리에 포함되지 않은 정점 중 최소 간선 비용의 정점 선택
			long min = Long.MAX_VALUE;
			int minNo = 0; //최소간선비용의 정점
			
			for(int i=0; i<N; ++i) {
				if(!visited[i] && min>minEdge[i]) {
					min = minEdge[i];
					minNo = i;
				}
			}
			visited[minNo] = true;
			result += min;
			visited[minNo] = true; //정점 방문 처리(신장트리에 포함시킴)
			if(++cnt==N) break;
			//2.선택된 정점에서 신장트리에 포함되지 않은 다른 정점들로의 간선의 비용을 고려하여 minEdge 업데이트
			
			for(int i=0; i<N; ++i) {
				if(!visited[i] && adjMatrix[minNo][i] > 0 && minEdge[i]>adjMatrix[minNo][i]) {
					minEdge[i] = min +adjMatrix[minNo][i];
				}
			}
			
		}
		
		return result;
		//pq를 안 쓰면 N^2의 시간 복잡도를 가지는데.
		//정점의 개수가 아주 많지 안ㅇㅎ을 때는 pq를 안 써도됨. 
		//pq를 썼을때 불리한 경우도 있는데 --> 거의 불리하지 않음 썼을떄 이점을 있는 경우가 있음.
		//절대적인 건 없다.. 
		//이 문제는 크루스칼로 풀면 불리하다. 정점의 개수가 1천개고 간선이 999개 니까 간선 개수가 50만
		//정점이 1천개인거에 비해 간선이 50만개.. 그래서 이 문제는 프림이 유리하다.
		//간선을 999개만 선택하면 되어서 간선리스트 윗부분에 몰려잇으면 운이 좋게 통과하지만 운이 나쁘면 사이클 계속 발견해서 다 봐야되는 경우가 있을지도 모른다. 
		//완전 그래프 형태 --> 프림으로 짜는게 낫다 ( 사실 둘다 시간통과를 하긴 하지만.. )
		
	}

	private static long getDistance(int x1, int x2, int y1, int y2) {
		return (long)(Math.pow((x1-x2), 2) + Math.pow((y1-y2),2)); 
	}
}//end of Class
