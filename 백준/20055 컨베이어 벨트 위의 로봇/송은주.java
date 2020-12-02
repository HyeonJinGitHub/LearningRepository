import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main{
	private static int N;
	private static int K;
	private static int[] input;
	private static Deque<Integer> list = new LinkedList<>();
	private static int[] robot;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		input = new int[2*N];
		robot = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<2*N; ++i) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		int anw = 1;
		while(true) {
//			벨트가 한 칸 회전한다.
			rotate();
			moveRobot();
//			올라가는 위치에 로봇이 없다면 로봇을 하나 올린다.
//			내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
			if(chk()) break;
			anw++;
		}
		
		System.out.println(anw);
	}


	private static boolean chk() {
		int res = 0;
		for(int i=0; i<input.length; ++i) {
			if(input[i]<=0) {
				res++;
			}
		}
		
		return res>=K;
		
	}


	private static void moveRobot() {
		if(robot[N-1]!=0) {
			robot[N-1] = 0;
		}
//			가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
		for(int i=N-2; i>=0; --i) {
			if(robot[i]!=0) {
//			로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
				if(input[i+1]>0 && robot[i+1]==0) {
					input[i+1]--;
					robot[i] = 0;
					robot[i+1] = 1;
				}
			}
		}
		
		if(robot[N-1]!=0) {
			robot[N-1] = 0;
		}
		
		if(robot[0]==0 && input[0]>0) {
			robot[0] = 1;
			input[0]--;
		}
	}


	private static void rotate() {
		list.clear();
		for(int i=0; i<input.length; ++i) {
			list.addLast(input[i]);
		}
		
		list.addFirst(list.pollLast());
		
		for(int i=0; i<input.length; ++i) {
			input[i] = list.pollFirst();
		}
		
		list.clear();
		for(int i=0; i<robot.length; ++i) {
			list.addLast(robot[i]);
		}
		
		list.addFirst(list.pollLast());
		
		for(int i=0; i<robot.length; ++i) {
			robot[i] = list.pollFirst();
		}
	
	}

}
