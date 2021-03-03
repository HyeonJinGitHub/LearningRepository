import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int[] solution(int[] answers) {
        Queue<Integer>one = new LinkedList<>();
        Queue<Integer>two = new LinkedList<>();
        Queue<Integer>three = new LinkedList<>();
        
        one.add(1);
        one.add(2);
        one.add(3);
        one.add(4);
        one.add(5);
        
        two.add(2);
        two.add(1);
        two.add(2);
        two.add(3);
        two.add(2);
        two.add(4);
        two.add(2);
        two.add(5);
        
        three.add(3);
        three.add(3);
        three.add(1);
        three.add(1);
        three.add(2);
        three.add(2);
        three.add(4);
        three.add(4);
        three.add(5);
        three.add(5);
        
        int anw1=0;
        int anw2=0;
        int anw3=0;
        
        for(int i=0; i<answers.length; ++i) {
        	if(one.peek()==answers[i]) {
        		anw1++;
        	}
        	if(two.peek()==answers[i]) {
        		anw2++;
        	}
        	if(three.peek()==answers[i]) {
        		anw3++;
        	}
        	one.offer(one.poll());
        	two.offer(two.poll());
        	three.offer(three.poll());
        }
        
        ArrayList<Integer>temp = new ArrayList<>();
        temp.add(anw1);
        temp.add(anw2);
        temp.add(anw3);
        Collections.sort(temp);
        
        System.out.println(temp.toString());
        
        ArrayList<Integer> k = new ArrayList<>();
        if(temp.get(2)==anw1) {
        	k.add(1);
        }
        if(temp.get(2)==anw2) {
        	k.add(2);
        }
        if(temp.get(2)==anw3) {
        	k.add(3);
        }
        
        int[] answer = new int[k.size()];
        for(int i=0; i<k.size(); ++i) {
        	answer[i] = k.get(i);
        }
        return answer;
    }
}
