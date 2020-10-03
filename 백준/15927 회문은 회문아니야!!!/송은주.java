import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		
		boolean palindromeFlag = true;
		boolean sameFlag = true;
		int cnt = 0;
		for(int i=0; i<input.length(); ++i) {
			if(input.charAt(i)!=input.charAt(input.length()-1-i)) {
				palindromeFlag = false;
			}
			if(i>0 && (input.charAt(i)!=input.charAt(i-1))){
				sameFlag = false;
			}
		}
		
		if(sameFlag) {
			System.out.println(-1);
			return;
		}
		
		if(!palindromeFlag) {
			System.out.println(input.length());
			return;
		}else {
			System.out.println(input.length()-1);
		}
		
	}
}//end of class
