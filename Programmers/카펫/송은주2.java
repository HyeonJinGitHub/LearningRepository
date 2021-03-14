class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int length = yellow/2+1;
        for(int i=1; i<=length; ++i) {
        	int j = yellow/i;
        	if(i*j!=yellow) continue;
        	int temp = i*2 + j*2 + 4;
        	if(temp==brown) {
        		answer[1] = i+2;
        		answer[0] = j+2;
        		return answer;
        	}
        	// System.out.println(i+","+j);
        }
        return answer;
    }
}
