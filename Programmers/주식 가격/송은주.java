class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        for(int i=0; i<prices.length; ++i){
            int k = prices[i];
            int cnt = 0;
            boolean flag =false;
            for(int j=i+1; j<prices.length; ++j){
                if(k<=prices[j]) {
                    //System.out.println("조건 만족"+k+","+prices[j]);
                    cnt++;
                    continue;
                }else {
                    flag = true;
                    answer[i] = cnt+1;
                    break;
                }
            }
            
            if(!flag){
                answer[i] = cnt;
            }
        }
        return answer;
    }
}
