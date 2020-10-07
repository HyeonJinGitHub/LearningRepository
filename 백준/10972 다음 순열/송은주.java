import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
	private static int K;
	public static boolean next_permutation(int[] a) {
        int i = a.length-1;
        while (i > 0 && a[i-1] >= a[i]) {
            i -= 1;
        }

        if (i <= 0) {
            return false;
        }

        int j = a.length-1;
        while (a[j] <= a[i-1]) {
            j -= 1;
        }

        int temp = a[i-1];
        a[i-1] = a[j];
        a[j] = temp;

        j = a.length-1;
        while (i < j) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i += 1;
            j -= 1;
        }
        return true;
    }
    public static void main(String args[]) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	N= Integer.parseInt(st.nextToken());
    	
    		int[] a = new int[N];
    		st = new StringTokenizer(br.readLine(), " ");
    		
    		for(int i=0; i<N; ++i) {
    			a[i] = Integer.parseInt(st.nextToken());
    		}
    		
    		if(next_permutation(a)) {
    			for(int i=0; i<a.length; ++i) {
    				if(i==a.length-1) System.out.print(a[i]);
    				else System.out.print(a[i] + " ");
    			}
    			
    		}else System.out.println(-1);
    		
    	
    	
        
    }
}
