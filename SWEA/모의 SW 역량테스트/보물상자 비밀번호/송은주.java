import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Solution{
	private static int N;
	private static int K;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine().trim());
		
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=TC; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			Deque<Character> dq = new LinkedList<Character>();
			String s = br.readLine().trim();
			for(int i=0; i<N; ++i) {
				dq.add(s.charAt(i));
			}
			TreeSet<String> ts = new TreeSet<String>(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o2.compareTo(o1);
				}
			});
			
			int o = N/4;
			for(int i=0; i<o; ++i) {
				int cnt = 0;
				String temp = "";
				/*for(int j=0;j<N; ++j) {
					System.out.print(dq.peekFirst());
					dq.offerLast(dq.pollFirst());
				}
				System.out.println();
				*/
				for(int j=0; j<N; ++j) {
					temp += dq.peekFirst();
					dq.offerLast(dq.pollFirst());
					if(++cnt%o==0) {
						cnt=0;
						ts.add(temp);
						temp="";
					}
					
				}
				
				
				dq.offerFirst(dq.pollLast());
			}
			
//			System.out.println(ts.toString());
			String[] result = ts.toArray(new String[ts.size()]);
//			System.out.println(result[K-1]);
			sb.append('#').append(tc).append(' ').append(Integer.parseInt(result[K-1], 16)).append('\n');
		}
		
		System.out.println(sb);
	}
}
