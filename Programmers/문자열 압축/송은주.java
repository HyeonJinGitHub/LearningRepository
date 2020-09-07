import java.util.ArrayList;

class Solution {
    public static int solution(String s) {
        int answer = 100000000;
        if(s.length()==1) answer = 1;
       
        for(int i=1; i<s.length()/2+1; ++i) {
        	ArrayList<String> arr = new ArrayList<String>();
//        	StringBuilder sb = new StringBuilder();
        	
        	for(int j=0; j<s.length(); j+=i) {
        		String temp = null;
//        		System.out.println(j + "," + i);
        		if(j+i>=s.length()) {
        			temp = s.substring(j);
        		}else {        			
        			temp = s.substring(j, j+i);
        		}
//        		System.out.println(temp);
        		arr.add(temp);
        	}
        	StringBuilder sb = new StringBuilder();
        	int temp = 1;
        	for(int j=0; j<arr.size(); j+=temp) {
        		temp = 1;
//        		System.out.println(j+"번쨰=>"+arr.get(j));
        		for(int l=j+1; l<arr.size(); ++l) {
        			if(arr.get(j).equals(arr.get(l))) {
        				temp++;
        			}else break;
        		}
        		
        		if(temp==1) {
        			sb.append(arr.get(j));
        		}else {
        			sb.append(Integer.toString(temp)).append(arr.get(j));
        		}
        	}

        	
//        	System.out.println("result=>"+sb.toString());
        	if(sb.length() < answer) {
        		answer = sb.length();
        	}
        }
        System.out.println("정답+>"+answer);
        return answer;
    }
}
