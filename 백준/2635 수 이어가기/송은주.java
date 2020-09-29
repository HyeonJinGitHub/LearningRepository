import java.util.ArrayList;
import java.util.Scanner;

public class Main {
;	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		
		ArrayList<Integer> arr = new ArrayList<>();
		int index = -1;
		int length = -1;
		for(int i=1; i<=k; ++i) {
			arr.clear();
			
			arr.add(k);
			arr.add(i);
			while((arr.get(arr.size()-2) - arr.get(arr.size()-1)) >0 ) {
				arr.add(arr.get(arr.size()-2) - arr.get(arr.size()-1));
			}
			if(arr.size() >= length) {
				length = arr.size();
				index = i;
			}
			
		}
		
		arr.clear();
		arr.add(k);
		arr.add(index);
		while((arr.get(arr.size()-2) - arr.get(arr.size()-1)) >=0 ) {
			arr.add(arr.get(arr.size()-2) - arr.get(arr.size()-1));
		}
		System.out.println(arr.size());
		for(int i=0; i<arr.size(); ++i) {
			System.out.print(arr.get(i) + " " );
		}
	}
}
