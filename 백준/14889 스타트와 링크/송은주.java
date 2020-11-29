import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static int[] q;
	private static int N;
	private static int[][] input;
	private static int temp = 1;
	private static int anw = Integer.MAX_VALUE;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		
		input = new int[N][N];
		visited = new boolean[N];
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; ++j) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ArrayList<Integer>teamA = new ArrayList<Integer>();
		ArrayList<Integer>teamB = new ArrayList<Integer>();
		
		go(teamA, teamB, 0);
		System.out.println(anw);
	}

	private static void go(ArrayList<Integer> teamA, ArrayList<Integer> teamB, int idx) {
		if(idx==N){
			if(teamA.size()>N/2) return;
			if(teamB.size()>N/2) return;
			
			int a, b, res;
			a=b=res=0;
			
			for(int i=0; i<N/2; ++i) {
				for(int j=0; j<N/2; ++j) {
					if(i==j) continue;
					a+=input[teamA.get(i)][teamA.get(j)];
					b+=input[teamB.get(i)][teamB.get(j)];
				}
			}
			
			res = Math.abs(a-b);
			
			if(anw > res) anw = res;
		}
		
		if(teamA.size()>N/2) return;
		if(teamB.size()>N/2) return;
		
		teamA.add(idx);
		go(teamA, teamB, idx+1);
		teamA.remove(teamA.size()-1);
		
		teamB.add(idx);
		go(teamA, teamB, idx+1);
		teamB.remove(teamB.size()-1);
		
		return;
		
	}
}//end of class
