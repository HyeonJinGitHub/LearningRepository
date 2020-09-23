class Solution {
	static boolean[][] visited;
	static char[][] input;
	static int M;
	static int N;
    public static int solution(int m, int n, String[] board) {
        visited = new boolean[m][n];
        input = new char[m][];
        
        for(int i=0; i<m; ++i) {
        	input[i] = board[i].toCharArray();
        }
        
        int answer = 0;
        M = m; N = n; //M : 높이, N : 폭
        while(true) {
        	int temp = chk();
        	if(temp==0) break;
//        	System.out.println("변경전");
        	answer += temp;
        	
        	down(); 
//        	System.out.println("변경후");
//        	print();
        }
        System.out.println("정답!=>"+answer);
        return answer;
    }
	private static void down() {
		for(int i=0; i<M; ++i) {
			for(int j=0; j<N; ++j) {
				if(visited[i][j]) {
					input[i][j] = 'z'; //없는글자로~
				}
			}
		}
		
		while(true) {
//			print();
			boolean flag = false;
			for(int i=0; i<M-1; ++i) {
				for(int j=0; j<N; ++j) {
					if(input[i+1][j]=='z' && input[i][j]!='z') {
						flag = true;
//						System.out.println(i+","+j+"으로들어옴");
						int a = i;
						int b = j;
						while(true) {
							if(a+1>=M || input[a+1][b]!='z') break;
							input[a+1][b] = input[a][b];
							input[a][b] = 'z';
							a+=1;
						}
					}
				}
		}
			
			if(!flag) break;
		}
		
		
		
	}
	
	private static void print() {
		System.out.println("============");
		for(int i=0; i<M; ++i) {
			for(int j=0; j<N; ++j) {
				System.out.print(input[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("============");
		
	}
	private static int chk() {
		init();
		int result = 0;
		
		for(int i=0; i<M-1; ++i) {
			for(int j=0; j<N-1; ++j) {
				char ch = input[i][j];
				if(ch=='z') continue;
				if(input[i+1][j] ==ch && input[i][j+1]==ch && input[i+1][j+1]==ch) {
					result += 4;
					if(visited[i][j]) result-=1;
					if(visited[i+1][j]) result-=1;
					if(visited[i][j+1]) result-=1;
					if(visited[i+1][j+1]) result-=1;
					
					visited[i][j] = true;
					visited[i+1][j] = true;
					visited[i][j+1] = true;
					visited[i+1][j+1] = true;
				}
			}
		}
		
//		System.out.println("result==>"+result);
		return result;
	}
	private static void init() {
		for(int i=0; i<M; ++i) {
			for(int j=0; j<N; ++j) {
				visited[i][j] = false;
			}
		}
		
	}
	
	public static void main(String[] args) {
		String[] temp = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
		solution(4, 5, temp);
	}
}
