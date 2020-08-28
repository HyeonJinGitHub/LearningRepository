import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static int N;
	private static String[] input;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		input = new String[N];
		
		for(int i=0; i<N; ++i) {
			input[i] = br.readLine();
		}
		
		go(0, 0, N);
		
	}

	private static void go(int x, int y, int size) {
		char now = input[x].charAt(y);
		boolean flag = true;
		for(int i=x; i<x+size; ++i) {
			for(int j=y; j<y+size; ++j) {
				if(input[i].charAt(j)!=now) {
					flag = false;
					break;
				}
			}
		}
		
		if(flag) {
			System.out.print(now);
		}else {
			int nSize = size/2;
			System.out.print("(");
			go(x, y, nSize);
			go(x, y+nSize, nSize);
			go(x+nSize, y, nSize);
			go(x+nSize, y+nSize, nSize);
			System.out.print(")");
		}

	}
	
}
