import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;


class Solution {
	class Node {
		int r1;
		int c1;
		int r2;
		int c2;
		
		public Node(int r1, int c1, int r2, int c2) {
			super();
			this.r1 = r1;
			this.c1 = c1;
			this.r2 = r2;
			this.c2 = c2;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getInstance().hashCode();
			result = prime * result + c1;
			result = prime * result + c2;
			result = prime * result + r1;
			result = prime * result + r2;
			
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			Node node = (Node)obj;
			
			if(this.r1 == node.r1 && this.c1 == node.c1 && this.r2 == node.r2 && this.c2 == node.c2) return true;
			if(this.r1 == node.r2 && this.c1 == node.c2 && this.r2 == node.r1 && this.c2 == node.c1) return true;
			
			return false;
		}
		
		private Solution getInstance() {
			return Solution.this;
		} //얘를 Node로 주니까 무한루프돌던데 이유 와이 ?
		
	}
	
    private HashSet<Node> visited;
	private int[][] input;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};	
	private Queue<Node> q;
	private int N;
	private int anw;

	public int solution(int[][] board) {
        N = board.length;
        
        input = new int[N+2][N+2];
        
        for(int i=0; i<input.length; ++i) {
        	for(int j=0; j<input.length; ++j) {
        		if(i==0 || i==input.length-1 || j==0 || j==input[0].length-1) input[i][j] = 1;
        		else input[i][j] = board[i-1][j-1];

        	}

        } //패딩
        
        visited = new HashSet<Node>();
        q = new LinkedList<Node>();
        
        push(1, 1, 1, 2);
        bfs();
        
        return anw;
    }

	private void bfs() {
		int[] rotate = {-1, 1};
		
		while(!q.isEmpty()) {
			int qSize = q.size();
			
			for(int z=0; z<qSize; ++z) {
				Node n = q.poll();
				
				if((n.r1==N && n.c1==N) || (n.r2==N && n.c2==N)) return;
							
				for(int k=0; k<4; ++k) {
					int nr1 = n.r1 + dx[k];
					int nc1 = n.c1 + dy[k];
					int nr2 = n.r2 + dx[k];
					int nc2 = n.c2 + dy[k];
					
					
					if(input[nr1][nc1]==0 && input[nr2][nc2]==0) {
						push(nr1, nc1, nr2, nc2);
					}
				}
				
				
				if(n.r1==n.r2) { //가로 회전, -- 이런 모양으로 있을 때
					for(int k=0; k<rotate.length; ++k) {
						int nr1 = n.r1 + rotate[k];
						int nc1 = n.c1;
						int nr2 = n.r2 + rotate[k];
						int nc2 = n.c2;
						
						if(input[nr1][nc1]==0 && input[nr2][nc2]==0) {
							push(n.r1, n.c1, nr1, nc1);
							push(n.r2, n.c2, nr2, nc2);
						}
						
					}
					
				}
				if(n.c1==n.c2) {//세로 회전, | 이런 모양으로 있을때
					for(int k=0; k<rotate.length; ++k) {
						int nr1 = n.r1;
						int nc1 = n.c1 +rotate[k];
						int nr2 = n.r2;
						int nc2 = n.c2 + rotate[k];
					
						if(input[nr1][nc1]==0 && input[nr2][nc2]==0) {
							push(n.r1, n.c1, nr1, nc1);
							push(n.r2, n.c2, nr2, nc2);
						}
					}
				}
				
			}
			anw += 1;
		}
		
	}

	private boolean push(int nr1, int nc1, int nr2, int nc2) {
		Node n = new Node(nr1, nc1, nr2, nc2);
		
		if(visited.contains(n)) {
			return false;
		}
		
		visited.add(n);
		q.offer(new Node(nr1, nc1, nr2, nc2));
		
		return true;
	}
}
