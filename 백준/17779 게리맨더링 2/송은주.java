import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Info{
	int x;
	int y;
	public Info(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "Info [x=" + x + ", y=" + y + "]";
	}
	
}

public class Main{
	private static int N;
	private static int[][] input;
	private static Info[] arrInfo;
	private static int anw;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		input = new int[N][N];
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int j=0; j<N; ++j) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
			
		}
		arrInfo = new Info[5];
		anw = Integer.MAX_VALUE;
		for(int i=0; i<N; ++i) {
//			System.out.println("i:"+i);
			for(int j=0; j<N; ++j) {
//				System.out.println("j:"+j);
				
				for(int d1= 1; d1<=j; ++d1) {
//					System.out.println("d1:"+d1);
					for(int d2=1; d2<N-j; ++d2) {
//						System.out.println("d2:"+d2);
						arrInfo[1] = new Info(i, j);
						arrInfo[2] = new Info(i+d1, j-d1);
						arrInfo[3] = new Info(i+d2, j+d2);
						arrInfo[4] = new Info(i+d1+d2, j+d2-d1);
						
						if(chk()) {
							/*for(int k=1; k<=4; ++k) {
								System.out.println(arrInfo[k].toString());
							}
							System.out.println("============");*/
							go();
						}
						
						
					}
				}
				
				
			}
		}
		System.out.println(anw);
	}

	private static void go() {
		int[][] temp = new int[N][N];
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				temp[i][j] = 5;
			}
		}
		ArrayList<Integer> arr = new ArrayList<>();
		// 왼쪽 위에 구역 1 1 1 1
		int res = 0;
		int t = arrInfo[1].y;
		for(int i=0; i<arrInfo[2].x; ++i) {
			if(i>=arrInfo[1].x) t-=1;
			for(int j=0; j<=t; ++j) {
				temp[i][j] = 1;
				res += input[i][j];
			}
		}
		
		//22222222222222
		arr.add(res);
		res = 0;
		t = arrInfo[1].y+1;
		for(int i=0; i<=arrInfo[3].x; ++i) {
			if(i>arrInfo[1].x) t+=1;
			for(int j=t; j<N; ++j) {
				temp[i][j] = 2;
				res += input[i][j];
			}
		}
		
		//333333333333
		arr.add(res);
		res = 0;
		t = arrInfo[2].y-1;
		for(int i=arrInfo[2].x; i<N; ++i) {
			if(i<=arrInfo[4].x)t+=1;
			for(int j=0; j<t; ++j) {
				temp[i][j] = 3;
				res += input[i][j];
			}
		}
		
		
		//4444444444
		arr.add(res);
		res = 0;
		t = arrInfo[4].y;
		for(int i=N-1; i>arrInfo[3].x; --i) {
			if(i<=arrInfo[4].x) t+=1;
			for(int j=t; j<N; ++j) {
				temp[i][j] = 4;
				res += input[i][j];
			}
		}
		arr.add(res);
		//5555555555
		res = 0;
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				if(temp[i][j]==5) res+=input[i][j];
			}
		}
		arr.add(res);
		Collections.sort(arr);
		int z = arr.get(arr.size()-1) - arr.get(0);
		if(anw > z) anw = z;
//		print(temp);
		
	}

	private static void print(int[][] temp) {
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				System.out.print(temp[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("=============");
	}

	private static boolean chk() {
		int x = arrInfo[1].x;
		int y = arrInfo[1].y;		
		if(0>x || x >= N || 0> y  || y >= N) return false;
			
		x = arrInfo[2].x;
		y = arrInfo[2].y;
		if(0>x || x >= N || 0> y  || y >= N) return false;
		
		x = arrInfo[3].x;
		y = arrInfo[3].y;
		if(0>x || x >= N || 0> y  || y >= N) return false;
		
		x = arrInfo[4].x;
		y = arrInfo[4].y;
		if(0>x || x >= N || 0> y  || y >= N) return false;
		
		return true;
	}
}
