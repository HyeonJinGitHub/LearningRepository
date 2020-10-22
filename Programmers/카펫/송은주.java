class Solution {
    public int sum;
    public int[] solution(int brown, int yellow) {
        int[] answer = null;
        sum = brown + yellow;
        
        int a = 1;
        int b = yellow;
        while(a<(sum/2)){
            b = sum/a;
            // System.out.println(a+","+b);
            if(b*a!=sum){
                // System.out.println("캬엉");
                a++;
                continue;
            }
            
            int cnt = 0;
            for(int i=1; i<a-1; ++i){
                for(int j=1; j<b-1; ++j){
                    // System.out.println(i+","+j+"히히");
                    cnt++;
                }
            }
            // System.out.println(cnt+"개");
            if(cnt==yellow){
                answer = new int[] {b, a};
                return answer;
            }
            a++;
        }
        return answer;
    }
}
