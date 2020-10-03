import java.util.HashMap;
import java.util.Scanner;

public class Main{
	private static int N;
	private static int anw;
	private static HashMap<String, Integer> map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		map = new HashMap<String, Integer>();
		
		N = sc.nextInt();
		
		StringBuilder sb = new StringBuilder();
		
		int anw = go(sb, 0);
		System.out.println(anw%1_000_000);
	}
	private static int go(StringBuilder sb, int cnt) {
//		System.out.println("냥옹"+sb.toString());
		
		if(map.containsKey(sb.toString())) return map.get(sb.toString());
		
		if(sb.length() >= 3 && (sb.charAt(sb.length()-1)=='A' && sb.charAt(sb.length()-2)=='A' && sb.charAt(sb.length()-3)=='A')) {
			return 0;
		}
		if(cnt>=2) return 0;
		
		if(sb.length()==N) {
//			System.out.println(temp++ +"들어왔음" + sb.toString());
//			anw %=1_000_000;
			map.put(sb.toString(), 1);
			return 1;
		}
		
		int anw = 0;
		
		int a = go(sb.append('O'), cnt);
		map.put(sb.toString(), a);
		sb.deleteCharAt(sb.length()-1);
		anw += a;
		
		a = go(sb.append('L'), cnt+1);
		map.put(sb.toString(), a);
		sb.deleteCharAt(sb.length()-1);
		anw += a;
		
		a = go(sb.append('A'), cnt);
		map.put(sb.toString(), a);
		sb.deleteCharAt(sb.length()-1);
		anw += a;
		
		
		return anw;
		
		
	}
}
