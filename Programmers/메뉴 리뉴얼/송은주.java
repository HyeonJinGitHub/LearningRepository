import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class Solution {
    private Map<String, Integer> m;
	private int[] now;
	private int max;

	public String[] solution(String[] orders, int[] course) {
        ArrayList<String> anw = new ArrayList<>();
        m = new HashMap<>();
        
        for(int i=0; i<course.length; ++i) {
        	m.clear();
        	
        	max = -1 ;
        	for(int j=0; j<orders.length; ++j) {
        		now = new int[course[i]];
        		char[] k = orders[j].toCharArray();
        		Arrays.sort(k);
        		String str = new String(k);
        		go(0, 0, str, course[i]);
        	}
        	
        	if(max <=1) continue;
        	for(Map.Entry<String, Integer>entry:m.entrySet()) {
        	    int val = entry.getValue();
        		if(val==max) {
        			anw.add(entry.getKey());
        		}
        	}
        	
        	
        	
        }
        
        
        Collections.sort(anw);
        String[] answer = new String[anw.size()];
        for(int i=0; i<anw.size(); ++i) {
        	answer[i] = anw.get(i);
        }
        return answer;
    }

	private void go(int cnt, int idx, String str, int goal) {
		if(cnt==goal) {
			String temp = "";
			for(int i=0; i<now.length; ++i) {
				temp+=str.charAt(now[i]);
			}
			m.put(temp, m.getOrDefault(temp, 0)+1);
			if(max < m.get(temp)) {
				max = m.get(temp);
			}
			return;
		}
		
		for(int i=idx; i<str.length(); ++i) {
			now[cnt] = i;
			go(cnt+1, i+1, str, goal);
		}
		
	}
}
