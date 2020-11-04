import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
	private static int[][] input;
	private static boolean fff;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = new int[9][9];
		for(int i=0; i<9; ++i) {
			String s = br.readLine();
			for(int j=0; j<9; ++j) {
				input[i][j] = s.charAt(j)-'0';
			}
		}
		go(0);
		
	}

	private static void go(int idx) {
		
		boolean forFlag = false;
		for(int i=idx/9; i<9; ++i) {
			int j = (idx%9);
			if(forFlag) j=0;
			for(; j<9; ++j) {
				if(input[i][j]==0) {
					int k=1;
					for(; k<=9; ++k) {
						
						boolean flag = false;
						for(int z=0; z<9; ++z) {
							if(input[z][j]==k) {
								flag = true;
								break;
							}
							if(input[i][z] ==k) {
								flag = true;
								break;
							}
						}
						
						if(flag) continue;
						
						int z = i/3*3;
						int y = j/3*3;
						for(int a=z; a<z+3; ++a) {
							for(int b=y; b<y+3; ++b) {
								if(input[a][b]==k) {
									flag = true;
									break;
								}
							}
						}
						
						if(flag) continue;
						input[i][j] = k;
						if(!fff) go((i*9+j));
					}
					if(k==10) {
						input[idx/9][idx%9] = 0;
						return;
					}
					
					
				}
			}
			forFlag = true;
		}
		
		for(int i=0; i<9; ++i) {
			for(int j=0; j<9; ++j) {
				System.out.print(input[i][j]);
			}
			System.out.println();
		}
		fff = true;
		
	}
}
