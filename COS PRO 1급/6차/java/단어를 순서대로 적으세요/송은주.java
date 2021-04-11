// 다음과 같이 import를 사용할 수 있습니다.
import java.util.*;

class Main {
    public int solution(int K, String[] words) {
			int answer = 0;
			Queue<String> q = new LinkedList<>();
			
			for(int i=0; i<words.length; ++i){
				q.offer(words[i]);
			}
			
			int len = q.poll().length();
			boolean flag = false;
			while(!q.isEmpty()){

				int k = len + q.peek().length();
				if(flag){
					flag = false;
					k -=1;
				}
				
								System.out.println(k);
				System.out.println(q.peek());
				if(k+1 > K){
					
					answer++;
// 					System.out.println("냐옹, 갱신됨!"+answer);
					len = 0;
				}else if(k+1 ==K){
					answer++;
// 					System.out.println("냐옹, 같음!"+answer);
					q.poll();
// 					System.out.println("아오.."+q.peek());
					len = 0;
					flag = true;
				}else {
// 					System.out.println("애옹..");
					len = k+1;
					q.poll();
				}
				
			}
			
			if(len > 0){
				answer++;
			}
			return answer;
    }

    // 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다.
    public static void main(String[] args) {
        Main sol = new Main();
        int K = 10;
			// String[] words = {"a", "aaaaaaaa" , "a", "aaaaaaaa"}; //2
			// String[] words = {"a", "aaaaaaaaa" , "a", "aaaaaaaaa"};
			// String[] words = {"a", "a", "a", "a", "a"};
			// String[] words = {"niced", "happy", "hello", "world", "hi"}; //4
        // String[] words = {"abc", "def", "ghk"}; //2
			 // String[] words = {"aaa", "bb"};
			// String[] words = {"aaadd", "bbbb"}; //1
			// String[] words = {"niced", "a", "ob", "dsfgf", "ddddd"}; //
			String[] words = {"nice", "happy", "hello", "world", "hi"};
        int ret = sol.solution(K, words);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret + " 입니다.");
    }
}
