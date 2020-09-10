import java.util.HashMap;
import java.util.Map;

class Solution {
	public static String[] solution(String[] record) {

		int cnt = 0;
        Map<String, String> map = new HashMap<String, String>();
        

        for(int i=0; i<record.length; ++i) {
        	String[] s = record[i].split(" ");
        	switch(record[i].charAt(0)) {
        	case 'E':
        		cnt += 1;
        		map.put(s[1], s[2]);
        		break;
        	case 'L':
        		cnt += 1;
//        		map.remove(s[1]);
        		break;
        	case 'C':
        		map.put(s[1], s[2]);
        		break;
        	}
        }
        String[] answer= new String[cnt];
        int temp = 0;
        
        for(int i=0; i<record.length; ++i) {
        	String result = "";
        	String[] s = record[i].split(" ");
        	switch(record[i].charAt(0)) {
        	case 'E':
        		result += map.get(s[1]);
        		result += "님이 들어왔습니다.";
        		answer[temp++] = result;
        		break;
        	case 'L':
        		result += map.get(s[1]);
        		result += "님이 나갔습니다.";
        		answer[temp++] = result;
        		break;
        	}
        }
      /*  for(String s:answer) {
        	System.out.println(s);
        }
    */
        return answer;
    }
    /*
    public static void main(String[] args) {
		String[] temp = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234", "Leave uid4567"};
    	solution(temp);
	}*/
}
