import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Solution{
	private static int[][] input;
	private static int tc;
	private static int K;
	private static boolean[] check;
	private static Deque<Integer> dq;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		input = new int[4][8];
		dq = new LinkedList<>();
		
		tc = Integer.parseInt(br.readLine().trim());
		for(int TC = 1; TC<=tc; ++TC) {
			K = Integer.parseInt(br.readLine().trim());
			for(int i=0; i<4; ++i) {
				String s = br.readLine();
				for(int j=0, index=0; j<8; ++j, index+=2) {
					input[i][j] = s.charAt(index);
				}
			}
			
			for(int i=0; i<K; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				int target =Integer.parseInt(st.nextToken())-1;
				int clock =Integer.parseInt(st.nextToken());
				check = new boolean[3];
				for(int j=0; j<3; ++j) {
					if(input[j][2]==input[j+1][6]) {
						check[j] = false; //같음
					}else check[j] = true; //다름
				}
				rotate(target, clock);
			}
			int anw = 0;
			for(int i=0, score=1; i<4; ++i, score*=2) {
				if(input[i][0]-'0'==1) anw += score;
			}
			System.out.println("#" + TC + " " + anw);
		}//end of TC
		
		
		
	}
	private static void rotate(int target, int clock) {
		int temp = clock;
		for(int i=target; i>0; --i) {
			if(check[i-1]) { //다르면 돌려야대
				go(i-1, temp*-1);
				temp *= -1;
			}else break;
		}
		
		go(target, clock);
		
		temp = clock;
		for(int i=target; i<3; ++i) {
			if(check[i]) {
				go(i+1, temp*-1);
				temp *= -1;
			}else break;
		}
		
	}

	private static void go(int target, int clock) {
		dq.clear();
		
		for(int k=0; k<8; ++k) {
			dq.offerLast(input[target][k]);
		}
		
		//1일 경우 시계방향
		//-1일 경우 반시계방향
		switch(clock) {
		case 1:
			dq.offerFirst(dq.pollLast());
			break;
		case -1:
			dq.offerLast(dq.pollFirst());
			break;
		}
		
		for(int k=0; k<8; ++k) {
			input[target][k] = dq.pollFirst();
		}
	}
}
