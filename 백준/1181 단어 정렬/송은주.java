import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Main{
	private static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		List<String>arr = new ArrayList<>();
		Set<String>s = new HashSet<>();
		
		N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; ++i) {
			String str = br.readLine();
			if(!s.contains(str)) {
				s.add(str);
				arr.add(str);
			}
		}
		
		Collections.sort(arr, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if(o1.length() > o2.length()) {
					return 1; //o1이 길이가 기니까 뒤로가야되니까 1
				}else if(o1.length()==o2.length()) {
					return o1.compareTo(o2);
				}else return -1;
			}
		});
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<arr.size(); ++i) {
			sb.append(arr.get(i)).append('\n');
		}
		System.out.print(sb);
	}
}
