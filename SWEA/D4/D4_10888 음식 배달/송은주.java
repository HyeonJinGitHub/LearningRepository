import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
class Info{
	int x;
	int y;
	public Info(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "Info [x=" + x + ", y=" + y + "]";
	}
	
}
public class Solution {
	private static int N;
	private static int[][] input;
	private static ArrayList<Info> chi;
	private static ArrayList<Info> house;
	private static boolean[] ary;
	private static int anw;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine().trim());
		
		for(int tc =1; tc<=TC; ++tc) {
			anw = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			input= new int[N][N];
			chi = new ArrayList<>();
			house = new ArrayList<>();
			
			for(int i=0; i<N; ++i) {
				st = new StringTokenizer(br.readLine()," ");
				for(int j=0; j<N; ++j) {
					input[i][j] = Integer.parseInt(st.nextToken());
					if(input[i][j]==1) house.add(new Info(i, j));
					else if(input[i][j]!=0) chi.add(new Info(i, j));
				}
			}
			
			
			ary = new boolean[chi.size()];
			
			go(0);
			
			System.out.println("#" + tc + " " + anw);
		}
	}

	private static void go(int cnt) {
		if(cnt==ary.length) {
			ArrayList<Info> a = new ArrayList<>();
			for(int i=0; i<ary.length; ++i) {
				if(ary[i]) a.add(new Info(chi.get(i).x, chi.get(i).y));
			}
			if(a.size()==0) return;
			gogo(a);
			return;
		}
		
		ary[cnt] = true;
		go(cnt+1);
		
		ary[cnt] = false;
		go(cnt+1);
		
	}

	private static void gogo(ArrayList<Info> a) {
		int res = 0;
		for(int i=0; i<a.size(); ++i) {
			int x = a.get(i).x;
			int y = a.get(i).y;
			res += input[x][y];
		}
		
		for(int i=0; i<house.size(); ++i) {
			int hx = house.get(i).x;
			int hy = house.get(i).y;
			
			int temp = Integer.MAX_VALUE;
			for(int j=0; j<a.size(); ++j) {
				int x = a.get(j).x;
				int y = a.get(j).y;
				
				int dist = (Math.max(hx, x) - Math.min(hx, x)) + (Math.max(hy, y) - Math.min(hy, y));
				
				if(temp > dist) temp = dist;
			}
			
			res += temp; 
		}
		
		
		if(res < anw) {
			anw = res;
		}
		
	}

}
