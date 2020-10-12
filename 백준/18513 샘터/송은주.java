import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
class Info{
	int target;
	int dir;
	public Info(int target, int dir) {
		super();
		this.target = target;
		this.dir = dir;
	}
}
class Main{
	private static int N;
	private static int K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		Set<Integer>set = new HashSet<>();
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		Queue<Info>q = new LinkedList<>();
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; ++i) {
			int n = Integer.parseInt(st.nextToken());
			q.offer(new Info(n, -1));
			q.offer(new Info(n, +1));
			set.add(n);
		}
		
		long anw = 0;
		int temp = 1;
		int kCnt = 0;
		while(!q.isEmpty()) {
			int qSize = q.size();
			
			for(int i=0; i<qSize; ++i) {
				int k = q.peek().target;
				int dir = q.poll().dir;
				
				int nK = k+dir;
				
				if(!set.contains(nK)) {
					set.add(nK);
					q.add(new Info(nK, dir));
					anw += temp;
					if(++kCnt==K) {
						System.out.println(anw);
						return;
					}
					
				}
			}
			temp += 1;
		}
		
	}
}
