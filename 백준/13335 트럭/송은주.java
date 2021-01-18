import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main{
	private static int n;
	private static int w; //다리길이
	private static int L; //최대하중
	private static int[] bridge;
	private static int weight;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken()); //n개의트럭
		w = Integer.parseInt(st.nextToken()); //다리의길이
		bridge = new int[w];
		
		L = Integer.parseInt(st.nextToken()); //최대하중
		Queue<Integer> q = new LinkedList<Integer>();
		st = new StringTokenizer(br.readLine(), " ");
		
		for(int i=0; i<n; ++i) {
			q.offer(Integer.parseInt(st.nextToken()));
		}
		
		int time = 0;
		weight = 0;
		while(q.size()>0) {
			if(bridge[0]!=0) continue;
			
			if(weight+q.peek() <= L) {
				weight+=q.peek();
				bridge[0] = q.poll();
			}
			
//			System.out.println(Arrays.toString(bridge));
			rotate();
			time+=1;
			
		}
		
		//다 하고 나서 다리에 있는거 옮겨줘야 하기때문에
		while(isFull()) {
			rotate();
			time+=1;
		}
		
		System.out.println(time+1);
		
	}


	private static boolean isFull() {
		for(int i=0; i<w; ++i) {
			if(bridge[i]!=0) return true;
		}
		return false;
	}


	private static void rotate() {
		if(bridge[w-1]!=0) {
			weight-=bridge[w-1];
			bridge[w-1] = 0;
		}
		
		Queue<Integer>q = new LinkedList<>();
		for(int i=0; i<w-1; ++i) {
			q.offer(bridge[i]);
		}
		
		bridge[0] = 0;
		for(int i=1; i<w; ++i) {
			bridge[i] = q.poll();
		}
//		System.out.println("변경 후");
//		System.out.println(Arrays.toString(bridge));
		
		
	}
}
