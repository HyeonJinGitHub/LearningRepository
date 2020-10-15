import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
	private static int N;
	private static int M;
	private static int diceX;
	private static int diceY;
	private static int K;
	private static int[][] input;
	private static int[][] dice = {{0, 0, 0},
									{0, 0, 0},
									{0, 0, 0},
									{0, 0, 0}};
	
	private static int[] dx = {0, 0, 0, -1, 1};
	private static int[] dy = {0, 1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		diceX = Integer.parseInt(st.nextToken());
		diceY = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		input = new int[N][M];
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; ++j) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<K; ++i) {
			int dir = Integer.parseInt(st.nextToken());
			int nX = diceX + dx[dir];
			int nY = diceY + dy[dir];
			
			if(0<=nX && nX < N && 0<=nY && nY < M) {
				diceX = nX;
				diceY = nY;
				move(dir);
				if(input[nX][nY]==0) {
					input[nX][nY] = dice[3][1];
				}else {
					dice[3][1] = input[nX][nY];
					input[nX][nY] = 0;
				}
				System.out.println(dice[1][1]);
			}
			
		}
		
		
		
	}

	private static void move(int dir) {
		int temp = 0;
		List<Integer>tail;
		List<Integer>head;
		List<Integer>last = new ArrayList<>();
		List<Integer>a = new ArrayList<>();
		switch(dir) {
		case 1: //동
			for(int i=0; i<3; ++i) {
				a.add(dice[1][i]);
			}
			
			tail = a.subList(0, 3);
			head = a.subList(2, a.size());
//			System.out.println(tail.toString());
//			System.out.println(.toString());
			last.addAll(head);
			last.addAll(tail);
			for(int i=0; i<3; ++i) {
				dice[1][i] = last.get(i);
			}
			temp = dice[1][0];
			dice[1][0] = dice[3][1];
			dice[3][1] = temp;
			break;
		case 2: //서
			for(int i=0; i<3; ++i) {
				a.add(dice[1][i]);
			}
			tail = a.subList(1, a.size());
			head = a.subList(0, 2);
			last.addAll(tail);
			last.addAll(head);
			for(int i=0; i<3; ++i) {
				dice[1][i] = last.get(i);
			}
			temp = dice[1][2];
			dice[1][2] = dice[3][1];
			dice[3][1] = temp;
			break;
		case 3: //남
			for(int i=0; i<4; ++i) {
				a.add(dice[i][1]);
			}
			tail = a.subList(0, 3);
			head = a.subList(3, a.size());
			last.addAll(head);
			last.addAll(tail);
			for(int i=0; i<4; ++i) {
				dice[i][1] = last.get(i);
			}
			break;
		case 4: //북
			for(int i=0; i<4; ++i) {
				a.add(dice[i][1]);
			}
			tail = a.subList(0, 2);
			head = a.subList(1, a.size());
			last.addAll(head);
			last.addAll(tail);
			for(int i=0; i<4; ++i) {
				dice[i][1] = last.get(i);
			}
			break;
		}
		
		//print();
	}

	private static void print() {
		for(int i=0; i<4; ++i) {
			for(int j=0; j<3; ++j) {
				System.out.print(dice[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("=============");
	}
}
