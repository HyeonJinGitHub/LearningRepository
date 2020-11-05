import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Main{
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int TC=0; TC<tc; ++TC) {
			Stack<Character> left = new Stack<>(); //커서기준 왼쪽
			Stack<Character> right = new Stack<>(); //커서기준 오른쪽
			String s = br.readLine();
			for(int i=0; i<s.length(); ++i) {
				if(s.charAt(i)=='<') {
					if(!left.isEmpty()) {
						right.push(left.pop());
					}
					continue;
				}
				
				if(s.charAt(i)=='>') {
					if(!right.isEmpty()) {
						left.push(right.pop());
					}
					continue;
				}
				
				if(s.charAt(i)=='-') {
					if(!left.isEmpty()) {
						left.pop();
					}
					continue;
				}
				
				left.push(s.charAt(i));
			}
			
			
			StringBuilder sb = new StringBuilder();
			while(!left.isEmpty()) {
				sb.append(left.pop());
			}
			
			sb.reverse();
			
			while(!right.isEmpty()) {
				sb.append(right.pop());
			}
			
			System.out.println(sb.toString());
		}
	}

}
