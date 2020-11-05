import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;		
		
		int anw = 0;
		for(int i=0; i<8; ++i) {
			String s = br.readLine();
			
			for(int j=0; j<8; ++j) {
				char c = s.charAt(j);
				if(c=='.') continue;
				if(i%2==0 && j%2==0) {
					if(c=='F') anw++;
				}
				
				if(i%2==1 && j%2==1) {
					if(c=='F') anw++;
					continue;
				}
				
			}
		}
		
		
		System.out.println(anw);
	}
}
