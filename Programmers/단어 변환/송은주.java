import java.util.LinkedList;
import java.util.Queue;
class Info{
	String s;
	int num;
	public Info(String s, int num) {
		super();
		this.s = s;
		this.num = num;
	}
	
}

class Solution {
    private int[] visited;

	public int solution(String begin, String target, String[] words) {
    	visited = new int[words.length];
    	
    	Queue<Info> q = new LinkedList<>();
    	q.offer(new Info(begin, 0));
    	
    	while(!q.isEmpty()) {
    		String s = q.peek().s;
    		int num = q.poll().num;
    		if(s.equals(target)) {
    			return num;
    		}
    		
    		for(int i=0; i<words.length; ++i) {
    			if(visited[i]!=0) continue;
    			
    			int flag = 0;
    			for(int k=0; k<words[i].length(); ++k) {
    				if(words[i].charAt(k)!=s.charAt(k)) flag += 1;
    			}
    			
    			if(flag==1) {
    				visited[i] = num + 1;
    				q.offer(new Info(words[i], num+1));
    			}
    		}
    		
    	}
    	
        int answer = 0;
        return answer;
    }
}
