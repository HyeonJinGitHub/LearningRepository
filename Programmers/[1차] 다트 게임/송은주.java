import java.util.Arrays;

class Solution {
    public static int solution(String dartResult) {
    	int[] str = new int[3];
    	char[] chr = new char[3];
    	int z = 0;

    	int[] anw = {0, 0, 0};
    	for(int i=0; i<dartResult.length(); ++i) {
    		if('A' <= dartResult.charAt(i) && dartResult.charAt(i) <='Z') {
    			str[z] = i;
    			chr[z++] = dartResult.charAt(i);
    		}
    	}
    	
    	String Roundone = dartResult.substring(0, str[0]);
    	System.out.println("시작 " + Roundone);
    	if(chr[0]=='S') {
    		anw[0] += Integer.parseInt(Roundone); 
    	}else if(chr[0]=='D') {
    		anw[0] += Integer.parseInt(Roundone) * Integer.parseInt(Roundone);
    	}else if(chr[0]=='T') {
    		anw[0] += Integer.parseInt(Roundone) * Integer.parseInt(Roundone) * Integer.parseInt(Roundone);
    	}
    	
    	String Roundtwo = dartResult.substring(str[0]+1, str[1]);
    	System.out.println("Roundtwo" + Roundtwo);
    	//    	System.out.println(Roundtwo);
    	
    	boolean flag = Roundtwo.contains("*");
    	if(Roundtwo.contains("#")) {
    		anw[0] *=-1;
    		Roundtwo = Roundtwo.substring(1);
    	}
    	if(flag) {
    		Roundtwo = Roundtwo.substring(1);
    	}
    	//////////////////////////////////////
    	if(chr[1]=='S') {
    		anw[1] += Integer.parseInt(Roundtwo); 
    	}else if(chr[1]=='D') {
    		anw[1] += Integer.parseInt(Roundtwo) * Integer.parseInt(Roundtwo);
    	}else if(chr[1]=='T') {
    		anw[1] += Integer.parseInt(Roundtwo) * Integer.parseInt(Roundtwo) * Integer.parseInt(Roundtwo);
    	}
    	
    	if(flag) {
    		anw[0] *=2;
    	}
    	
    	String Roundthree = dartResult.substring(str[1]+1, str[2]); //roundtwo 의 정보 포함
    	System.out.println("Roundthree" + Roundthree);
    	flag = Roundthree.contains("*");
    	System.out.println(flag);
    	if(Roundthree.contains("#")) {
    		anw[1] *=-1;
    		Roundthree = Roundthree.substring(1);
    	}
    	
    	if(flag) Roundthree = Roundthree.substring(1);
    	////////////////////////////
    	if(chr[2]=='S') {
    		anw[2] += Integer.parseInt(Roundthree); 
    	}else if(chr[2]=='D') {
    		anw[2] += Integer.parseInt(Roundthree) * Integer.parseInt(Roundthree);
    	}else if(chr[2]=='T') {
    		anw[2] += Integer.parseInt(Roundthree) * Integer.parseInt(Roundthree) * Integer.parseInt(Roundthree);
    	}
    	
    	if(flag) {
    		anw[1] *= 2;
    		anw[0] *= 2;
    	}
    	
    	String finalRound = dartResult.substring(str[2]+1);
//    	System.out.println(finalRound + "fff");
    	
    	flag = finalRound.contains("*");
    	if(finalRound.contains("#")) {
    		anw[2] *=-1;
    		finalRound = finalRound.substring(1);
    	}
    	if(flag) {
    		anw[2] *=2;
    		anw[1] *=2;
    	}
    	
    	
    	System.out.println(Arrays.toString(anw));
    	System.out.println(anw[0] + anw[1] + anw[2]);
    	
    	return anw[0] + anw[1] + anw[2];
    	
    	
    	
//    	System.out.println(Arrays.toString(chr));
    	
    	
    }
    
    public static void main(String[] args) {
		solution("1D2S0T");
	}
}
