import java.util.ArrayList;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        
        for(int i=0; i<arr1.length; ++i) {
        	StringBuilder one = new StringBuilder();
        	
        	String temp = Integer.toBinaryString(arr1[i]);
        	if(temp.length() < n) {
        		for(int k=0; k<n-temp.length(); ++k) {
        			one.append(0);
        		}
        	}
        	one.append(temp);
//        	System.out.println("one");
//        	System.out.println(one.toString());

        	
        	StringBuilder two = new StringBuilder();
        	temp = Integer.toBinaryString(arr2[i]);
        	if(temp.length() < n) {
        		for(int k=0; k<n-temp.length(); ++k) {
        			two.append(0);
        		}
        	}
        	two.append(temp);
//        	System.out.println("two");
//        	System.out.println(two.toString());
        	
        	String last = "";
        	for(int z=0; z<n; ++z) {
        		if(one.toString().charAt(z)=='1' || two.toString().charAt(z)=='1') {
        			last += "#";
        		}else last += " ";
        	}
        	
        	answer[i] = last;
//        	System.out.println(answer[i]);
        }
        
        return answer;
    }
}
