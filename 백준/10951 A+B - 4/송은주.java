import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		String temp;
		while((temp = br.readLine())!=null) {
			st = new StringTokenizer(temp, " ");
			sb.append(Integer.parseInt(st.nextToken())+Integer.parseInt(st.nextToken()))
			.append('\n');
		}
		System.out.println(sb);
	}
}
