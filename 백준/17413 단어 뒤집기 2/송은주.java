import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String s = br.readLine();
		Stack<Character>stk = new Stack<>();
		
		for(int i=0; i<s.length(); ++i) {
//			System.out.println(s.charAt(i));
			if(s.charAt(i)==' ') {
				while(!stk.isEmpty()) {
					sb.append(stk.pop());
				}
				sb.append(s.charAt(i));
				continue;
			}
			if(s.charAt(i)=='<') {
				while(!stk.isEmpty()) {
					sb.append(stk.pop());
				}
				sb.append(s.charAt(i));
				while(s.charAt(i)!='>') {
					i++;
					sb.append(s.charAt(i));
				}
			}
			if(s.charAt(i)=='>') continue;
			stk.push(s.charAt(i));
			
		}
		
		while(!stk.isEmpty()) {
			sb.append(stk.pop());
		}
		System.out.println(sb.toString());
	}

}
