import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

class Solution{
	private static int TC;
	private static int N;
	private static int K;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			String input = br.readLine();
			
			int t = N/4;
			int k = 0;
			HashSet<String>hs = new HashSet<>();
			StringBuilder b = new StringBuilder();
			for(int q=0; q<t; ++q) {
				input = input.charAt(input.length()-1) + input.substring(0, input.length()-1);
				for(int i=0; i<input.length(); ++i) {
					if(k<t) {
						k++;
						b.append(input.charAt(i));
					}
					
					if(k==t) {
						k=0;
						hs.add(b.toString());
//						System.out.println(b.toString());
						b = new StringBuilder();
					}
				}
				
			}
			
			ArrayList<Integer> arr = new ArrayList<>();
			for(String s:hs) {
				arr.add(Integer.parseInt(s, 16));
			}
			Collections.sort(arr);
			
			sb.append('#').append(tc).append(' ').append(arr.get(arr.size()-K)).append('\n');
			
		}
		
		System.out.print(sb.toString());
	}
}
