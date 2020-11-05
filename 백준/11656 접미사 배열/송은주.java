import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		String s = br.readLine();
		
		ArrayList<String>arr =new ArrayList<>();
		
		for(int i=0; i<s.length(); ++i) {
			arr.add(s.substring(i, s.length()));
		}
		
		Collections.sort(arr);
		for(String S: arr) {
			sb.append(S).append('\n');
		}
		System.out.println(sb.toString());
	}

}
