import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	private static int N;
	private static int R;
	private static int[][] input;
	private static int[][] res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		N = 1 << N;
//		System.out.println(N+"?");
		R = Integer.parseInt(st.nextToken());
		
		input = new int[N][N];
		res = new int[N][N];
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; ++j) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<R; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int k = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			l = 1 << l;
//			System.out.println(l+"???!!");
			//l => len이다
			if(l==0) continue;
			switch(k) {
			case 1:
				one(l);
				break;
			case 2:
				two(l);
				break;
			case 3:
				three(l);
				break;
			case 4:
				four(l);
				break;
			case 5:
				five(l);
				break;
			case 6:
				six(l);
				break;
			case 7:
				seven(l);
				break;
			case 8:
				eight(l);
				break;
			}
		}
		
		print(input);
	}
	private static void eight(int l) {
		four(N);
		three(l);
	}
	private static void seven(int l) {
		three(N);
		four(l);
		
	}
	private static void six(int l) {
		two(N);
		two(l);
		
	}
	private static void five(int l) {
		one(N);
		one(l);
		
	}
	private static void four(int l) {
		int gapC = 0;
		int gapR = 0;
		
		int cnt = 0;
		for(int i=0; i<N; i+=l) {
			for(int j=0; j<N; j+=l) {
				int r = i+l;
				int c = j+l;
				
				for(int a=i; a<r; ++a) {
					for(int b=j; b<c; ++b) {
//						System.out.print("흠"+(c-1-b + l*gapR)+"\t");
//						System.out.println(a+l*gapC-l*cnt);
						res[c-1-b + l*gapR][a+l*gapC-l*cnt] = input[a][b];
					}
				}
				gapC++;
//				gapR++;
//				System.out.println(gapR+","+gapC+","+(l*gapC));
			}
			gapC=0;
			gapR++;
			cnt++;
		}
		
		for(int a=0; a<N; ++a) {
			for(int b=0; b<N; ++b) {
				input[a][b] = res[a][b];
			}
		}
	}
	//시계빵향 90도 회전
	private static void three(int l) {
		int gapC = 0;
		int gapR = 0;
		int cnt = 0;
		for(int i=0; i<N; i+=l) {
			for(int j=0; j<N; j+=l) {
				int r = i+l;
				int c = j+l;
//				System.out.println("i,j"+i+","+j);
				for(int a=i; a<r; ++a) {
					for(int b=j; b<c; ++b) {
//						System.out.print("흠"+(b-l*gapR + l*cnt)+"\t");
//						System.out.println(r-1-a + l*gapC);
						res[b-l*gapR + l*cnt][r-1-a + l*gapC] = input[a][b];
					}
				}
				gapR++;
				gapC++;
//				System.out.println(gapR+","+gapC);
			}
			cnt++;
			gapR=0;
			gapC=0;
		}
		for(int a=0; a<N; ++a) {
			for(int b=0; b<N; ++b) {
				input[a][b] = res[a][b];
			}
		}
		
	}
	private static void two(int l) {
		int cnt = 0;
		for(int i=0; i<N; i+=l) {
			for(int j=0; j<N; j+=l) {
				int r = i+l;
				int c = j+l;
				for(int a=i; a<r; ++a) {
					for(int b=j; b<c; ++b) {
						res[a][c-b-1 + l*cnt] = input[a][b];
					}
				}
				cnt++;  //열 기준이니까 열 까지 다 갔으면 밑에는 열을 다시 초기화해줘야되는거임
			}
			cnt=0;
		}
		for(int a=0; a<N; ++a) {
			for(int b=0; b<N; ++b) {
				input[a][b] = res[a][b];
			}
		}
		
	}
	//상하 반전
	private static void one(int l) {
		int cnt = 0;
		for(int i=0; i<N; i+=l) {
			for(int j=0; j<N; j+=l) {
				int r = i+l;
				int c = j+l;
				
				for(int a=i; a<r; ++a) {
					for(int b=j; b<c; ++b) {
						res[r-a-1 + l*cnt][b] = input[a][b];
					}
				}
//				print(res);
			}
			cnt++; //행은 다같이 가니까 상관없음
//			System.out.println(cnt);
		}
		for(int a=0; a<N; ++a) {
			for(int b=0; b<N; ++b) {
				input[a][b] = res[a][b];
			}
		}
		for(int a=0; a<N; ++a) {
			for(int b=0; b<N; ++b) {
				input[a][b] = res[a][b];
			}
		}
		
//		print(res);
	}
	private static void print(int[][] temp) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<temp.length;++i) {
			for(int j=0; j<temp[0].length; ++j) {
				sb.append(temp[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.print(sb);
//		System.out.println("==");
	}
}
