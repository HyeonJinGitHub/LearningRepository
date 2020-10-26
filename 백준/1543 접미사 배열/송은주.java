import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String target = br.readLine();
		String find = br.readLine();
		int cnt = 0;
		
		for(int i=0; i<target.length(); ++i) {
			String temp = target.substring(i, target.length());
			if(temp.startsWith(find)) {
				cnt++;
				i += find.length()-1;
			}
		}
		
		System.out.println(cnt);
		
	}

}
