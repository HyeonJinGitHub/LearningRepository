import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	private static int N;
	private static int M;
	private static int[][] input;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken()); 
		
		input = new int[N][M];
		for(int i=0; i<N; ++i){
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; ++j){
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int anw = 0;
		
		for(int i=0; i<N; ++i){
			for(int j=0; j<M; ++j){
				if(i+3<N){
					int temp = input[i][j] + input[i+1][j] + input[i+2][j] + input[i+3][j];
					if(anw < temp){
						anw = temp;
					}
				}
				if(j+3<M){
					int temp = input[i][j] + input[i][j+1] + input[i][j+2] + input[i][j+3];
					if(anw<temp){
						anw = temp;
					}
				}
				
				if(i+1<N && j+1<M){
					int temp = input[i][j] + input[i+1][j] + input[i][j+1] + input[i+1][j+1];
					if(anw<temp){
						anw = temp;
					}
				}
				
				if(i+2<N && j+1<M){
					int temp = input[i][j] + input[i+1][j] + input[i+2][j] + input[i+2][j+1];
					if(anw<temp){
						anw = temp;
					}
				}
				
				if(i+1<N && j+2<M){
					int temp = input[i][j] + input[i+1][j] + input[i][j+1] + input[i][j+2];
					if(anw<temp){
						anw = temp;
					}
				}
				
				if(i+2<N && j+1<M){
					int temp = input[i][j] + input[i][j+1] + input[i+1][j+1] + input[i+2][j+1];
					if(anw<temp){
						anw = temp;
					}
				}
				
				if(0<=i-1 && j+2<M){
					int temp = input[i][j] + input[i][j+1] + input[i][j+2] + input[i-1][j+2];
					if(anw<temp){
						anw = temp;
					}
				}
				
				if(i+1<N && j+2<M){
					int temp = input[i][j] + input[i][j+1] + input[i][j+2] + input[i+1][j+1];
					if(anw<temp){
						anw = temp;
					}
				}
				
				if(0<=j-1 && i+2<N){
					int temp = input[i][j] + input[i+1][j-1] + input[i+1][j] + input[i+2][j];
					if(anw<temp){
						anw = temp;
					}
				}
				if(i+2<N && j+1<M){
					int temp = input[i][j] + input[i+1][j] + input[i+1][j+1] + input[i+2][j+1];
					if(anw<temp){
						anw = temp;
					}
				}
				
				if(0<=i-1 && j+2<M){
					int temp = input[i][j] + input[i][j+1] + input[i-1][j+1] + input[i-1][j+2];
					if(anw<temp){
						anw = temp;
					}
				}
				
				if(i+2<N && 0<=j-1){
					int temp = input[i][j] + input[i+1][j] + input[i+1][j-1] + input[i+2][j-1];
					if(anw<temp){
						anw = temp;
					}
				}
				
				if(i+1<N && j+2<M){
					int temp = input[i][j] + input[i][j+1] + input[i+1][j+1] + input[i+1][j+2];
					if(anw<temp){
						anw = temp;
					}
				}
				
				if(0<=j-1 && i+2<N){
					int temp = input[i][j] + input[i+1][j] + input[i+2][j] + input[i+2][j-1];
					if(anw<temp){
						anw = temp;
					}
				}
				
				if(i+1<N && j+2<M){
					int temp = input[i][j] + input[i+1][j] + input[i+1][j+1] + input[i+1][j+2];
					if(anw<temp){
						anw = temp;
					}
				}
				
				if(0<=j-1 && i+2<N){
					int temp = input[i][j] + input[i][j-1] + input[i+1][j-1] + input[i+2][j-1];
					if(anw<temp){
						anw = temp;
					}
				}
				
				if(i+1<N && j+2<M){
					int temp = input[i][j] + input[i][j+1] + input[i][j+2] + input[i+1][j+2];
					if(anw<temp){
						anw = temp;
					}
				}
				
				if(0<=i-1 && j+2<M){
					int temp = input[i][j] + input[i][j+1] + input[i][j+2] + input[i-1][j+1];
					if(anw<temp){
						anw = temp;
					}
				}
				
				if(i+2<N && j+1<M){
					int temp = input[i][j] + input[i+1][j] + input[i+2][j] + input[i+1][j+1];
					if(anw<temp){
						anw = temp;
					}
				}
			}
		}
		
		
		System.out.println(anw);
		
	}
}
