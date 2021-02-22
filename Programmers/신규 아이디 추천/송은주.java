class Solution {
    public static String solution(String new_id) {
    	String answer = "";
    	
    	new_id = new_id.toLowerCase();
    	System.out.println("1단계: "+new_id);
    	
    	for(int i=0; i<new_id.length(); ++i) {
    		if('a' <= new_id.charAt(i) && new_id.charAt(i) <='z') {
    			answer += new_id.charAt(i);
    			continue;
    		}
    		if('0' <= new_id.charAt(i) && new_id.charAt(i) <='9') {
    			answer += new_id.charAt(i);
    			continue;
    		}
    		if(new_id.charAt(i)=='-') {
    			answer += new_id.charAt(i);
    			continue;
    		}
    		if(new_id.charAt(i)=='_') {
    			answer += new_id.charAt(i);
    			continue;
    		}
    		if(new_id.charAt(i)=='.') {
    			answer += new_id.charAt(i);
    			continue;
    		}
    		
    		
    	}
    	new_id = answer;
    	System.out.println("2단계: "+new_id);
    	
    	
    	for(int i=0; i<10; ++i) {
    		new_id = new_id.replace("..", ".");
    	}
    	
    	System.out.println("3단계: "+new_id);
    	
    	if(new_id.length()>1){
    		if(new_id.charAt(0)=='.') {
    			answer = new_id.substring(1);
    			new_id = answer;
    		}
    		
    		if(new_id.charAt(new_id.length()-1)=='.') {
    			if(new_id.length()==1) {
    				answer = "";
    				new_id = answer;
    			}else {
    				answer = new_id.substring(0, new_id.length()-1);
    				new_id = answer;
    			}
    		}
  
    	}else if(new_id.length()==1) {
    		if(new_id.charAt(0)=='.') {
    			answer = new_id.substring(1);
    			new_id = answer;
    		}
    		
    	}
    	
    	if(new_id.length()==0) {
    		new_id = "";
    	}
    	
    	System.out.println("4단계: "+new_id);
    	
    	if("".equals(new_id)) {
    		new_id = "a";
    	}
    	System.out.println("5단계: "+new_id);
    	
    	if(new_id.length()>=16) {
    		new_id = new_id.substring(0, 15);
    		
    		if(new_id.charAt(new_id.length()-1)=='.') {
    			answer = new_id.substring(0, new_id.length()-1);
    			new_id = answer;
    		}
    	}
    	
    	System.out.println("6단계: "+new_id);
    	
    	if(new_id.length()<=2) {
    		char c = new_id.charAt(new_id.length()-1);
    		while(new_id.length()!=3) {
    			new_id += c;
    		}
    	}
    	System.out.println("7단계: "+new_id);
    	
    	return new_id;
    }
    
    public static void main(String[] args) {
    	System.out.println(solution("z-+.^."));
	}
}
