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
		if(fff) return;
		
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
						
						
						if(0<= i && i< 3 && 0<= j && j<3) {
							for(int z=0; z<3; ++z) {
								for(int y=0; y<3; ++y) {
									if(input[z][y]==k) {
										flag = true;
										break;
									}
								}
							}
						}
						if(flag) continue;
						
						if(3<= i && i< 6 && 0<= j && j < 3) {
							for(int z=3; z<6; ++z) {
								for(int y=0; y<3; ++y) {
									if(input[z][y]==k) {
										flag = true;
										break;
									}
								}
							}
						}
						if(flag) continue;
					
						if(6<= i && i<9 && 0<=j && j<3) {
							for(int z=6; z<9; ++z) {
								for(int y=0; y<3; ++y) {
									if(input[z][y]==k) {
										flag = true;
										break;
									}
								}
							}
						}
						if(flag) continue;
						
						
						if(0<= i && i<3 && 3<= j && j<6) {
							for(int z=0; z<3; ++z) {
								for(int y=3; y<6; ++y) {
									if(input[z][y]==k) {
										flag = true;
										break;
									}
								}
							}
						}
						if(flag) continue;
						
						if(3<=i && i<6 && 3<=j && j<6) {
							for(int z=3; z<6; ++z) {
								for(int y=3; y<6; ++y) {
									if(input[z][y]==k) {
										flag = true;
										break;
									}
								}
							}
						}
						if(flag) continue;
						
						if(6<=i && i<9 && 3<=j && j<6) {
							for(int z=6; z<9; ++z) {
								for(int y=3; y<6; ++y) {
									if(input[z][y]==k) {
										flag = true;
										break;
									}
								}
							}							
						}
						
						if(flag) continue;
						
						if(0<=i && i<3 && 6<=j && j<9) {
							for(int z=0; z<3; ++z) {
								for(int y=6; y<9; ++y) {
									if(input[z][y]==k) {
										flag = true;
										break;
									}
								}
							}
						}
						
						if(flag) continue;
						
						if(3<=i && i<6 && 6<=j && j<9) {
							for(int z=3; z<6; ++z) {
								for(int y=6; y<9; ++y) {
									if(input[z][y]==k) {
										flag = true;
										break;
									}
								}
							}
						}
						if(flag) continue;
						
						if(6<=i && i<9 && 6<=j && j<9) {
							for(int z=6; z<9; ++z) {
								for(int y=6; y<9; ++y) {
									if(input[z][y]==k) {
										flag = true;
										break;
									}
								}
							}
						}
						
						if(flag) continue;
						input[i][j] = k;
						go((i*9 + j));
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
