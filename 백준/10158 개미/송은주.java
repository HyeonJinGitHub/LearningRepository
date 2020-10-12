import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	private static int w;
	private static int h;
	private static int p;
	private static int q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		
		p = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		
		int t = Integer.parseInt(br.readLine().trim());
		
		int tempX = (t+p) % (2*w);
		int tempY = (t+q) % (2*h);
		
		if(tempX >= w) p = w - tempX%w;
		else p = tempX;
		if(tempY >= h) q = h - tempY%h;
		else q = tempY;
		
		System.out.println(p + " " + q);
		
		
		System.out.println();
		
	}
}
