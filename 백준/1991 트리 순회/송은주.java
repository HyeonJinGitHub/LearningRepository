import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
	private static int N;
	private static ArrayList<char[]> arr = new ArrayList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; ++i) {
			arr.add(new char[2]); //0->왼, 1->오
			arr.get(i)[0] = '.';
			arr.get(i)[1] = '.'; //일단 . 로 초기화
		}
		
		for(int i=0; i<N; ++i) {
			String s = br.readLine();
			char target = s.charAt(0);
			char left = s.charAt(2);
			char right = s.charAt(4);
//			System.out.println(target+","+left+","+right);
			
			arr.get(target-'A')[0] = left;
			arr.get(target-'A')[1] = right;
		}
		
//		print();
		preOrder('A');
		System.out.println();
		inOrder('A');
		System.out.println();
		postOrder('A');
	}

	private static void postOrder(char c) {
		if(c=='.') return;
		
		postOrder(arr.get(c-'A')[0]);
		postOrder(arr.get(c-'A')[1]);
		System.out.print(c);
		
	}

	private static void inOrder(char c) {
		if(c=='.') return;
		
		inOrder(arr.get(c-'A')[0]);
		System.out.print(c);
		inOrder(arr.get(c-'A')[1]);

	}

	private static void preOrder(char c) {
		if(c=='.') return;
		System.out.print(c);
		
		preOrder(arr.get(c-'A')[0]);
		preOrder(arr.get(c-'A')[1]);
	}

	private static void print() {
		for(int i=0; i<N; ++i) {
			System.out.print((i+'A')+"에서:");			
			System.out.println(arr.get(i)[0] +","+arr.get(i)[1]);
		}
		
	}
}
