import java.util.ArrayList;
import java.util.Scanner;

class Main{
	private static boolean[] prime;
	private static ArrayList<Integer> list;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		init(N);
		int now = 0;
		int end = 0;
		int anw = 0;
		int size = list.size();
		for(int start=0; start<size; ++start) {
			while(now < N && end < size) {
				now += list.get(end);
				++end;
			}
			
			if(now==N) {
				++anw;
			}
			
			now -= list.get(start);
		}
		
		System.out.println(anw);
	}

	private static void init(int N) {
		prime = new boolean[N+1];
		
		for(int i=2; i<N+1; ++i) {
			if(!prime[i]) {
				//prime[i] = true;
				for(int j=i*2; j<N+1; j+=i) {
					prime[j] = true;
				}
			}
		}
		
		list = new ArrayList<Integer>();
		for(int i=2; i<N+1; ++i) {
			if(!prime[i]) list.add(i);
		}
		/*
		for(int i=0; i<N; ++i) {
			System.out.print(i+"\t");
			System.out.println(prime[i]?"O":"X");
		}
		*/
	}
}
