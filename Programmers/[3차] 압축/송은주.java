import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    public static int[] solution(String msg) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        ArrayList<Integer> arr = new ArrayList<Integer>();
        char ch = 'A';
        int index=0;
        for(index = 1; index<27; ++index) {
        	map.put(Character.toString(ch++), index);
        }
        boolean flag = false;
        for(int i=0; i<msg.length(); ++i) {
        	int z = 0;
        	String temp = Character.toString(msg.charAt(i));
        	int j;
        	for(j=i+1; j<msg.length(); ++j) {
        		if(map.containsKey(temp)) {
        			temp += msg.charAt(j);
        			z++;
        			continue;
        		}
        		break;
        	}
        	if(temp.length()==1) {//1글자면'
        		
        		arr.add(map.get(temp));
        		continue;
        	}
        	
        	if(map.containsKey(temp)){
        		arr.add(map.get(temp));
        		break;
        	}
        	
        	String last = "";
        	for(int k=0; k<temp.length()-1; ++k) {
        		last += msg.charAt(i+k);
        	}

        	
        	arr.add(map.get(last));
        	
        	map.put(temp, index++);
        	i += (z-1);

        }
        int[] answer = new int[arr.size()];
        
        for(int i=0; i<arr.size(); ++i) {
        	answer[i] = arr.get(i);
        }
        return answer;
    }
    public static void main(String[] args) {
		solution("TOBEORNOTTOBEORTOBEORNOT");
	}
}
