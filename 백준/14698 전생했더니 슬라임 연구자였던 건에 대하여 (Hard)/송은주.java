import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static int T;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<T; ++i) {
			int N = Integer.parseInt(br.readLine());
			PriorityQueue<Long> q = new PriorityQueue<>(new Comparator<Long>() {
				@Override
				public int compare(Long o1, Long o2) {
					return o1.compareTo(o2);
				}
			});
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; ++j) {
				q.offer(Long.parseLong(st.nextToken()));
			}
			
			long anw = 1;
			while(q.size()!=1) {
				long slimeA = q.poll();
				long slimeB = q.poll();
				
				long newSlime = (slimeA * slimeB);
				q.offer(newSlime);
				newSlime = newSlime % 1_000_000_007L;
				
				
				anw = anw * newSlime;
				anw = anw % 1_000_000_007L;
			}
			sb.append(anw).append('\n');
		}
		
		System.out.print(sb);
	}
}
