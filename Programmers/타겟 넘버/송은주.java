class Solution {
    static int answer = 0;
    public static int solution(int[] numbers, int target) {
        go(numbers, target, numbers[0], 1);
        go(numbers, target, -numbers[0], 1);
        return answer;
    }
    
    public static void go(int[] numbers, int target, int now, int idx){
//    	System.out.println("idx는"+idx+",now는"+now);
    	if(numbers.length==idx) {
        	if(now==target) answer += 1;
        	return;
        }
        
        go(numbers, target, now+numbers[idx], idx+1);
        go(numbers, target, now-numbers[idx], idx+1);
    }
    
    
    public static void main(String[] args) {
		int[] temp = {1, 1, 1, 1, 1};
		solution(temp, 3);
		System.out.println(answer);
	}
}
