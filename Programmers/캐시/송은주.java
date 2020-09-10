import java.util.LinkedList;

class Solution {
	final int miss = 5;
	final int hit = 1;
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        if(cacheSize==0) {
        	return 5*cities.length;
        }
        LinkedList<String> list = new LinkedList<String>();
        //처음에 deque로 짜볼생각을 했는데 그러려면 빈도수를 체크해주는 걸 따로 만들어서
        //클래스로 만들어야된다. 와 그러면 뭐라해야되지 .. deque는 remove가 어려우니까..
        //그렇게 하려면 차라리
        //pq로 짜는게 낫겠다고 생각하고.. 엄청 복잡하게 생각햇는데
        //이런 경우는 그냥 LinkedList쓰면 remove를 쓰고 deque로 쓰면 해결된다.
        //자ㅣ료구조를 잘 알고 해결할줄 알자
        for(int i=0; i<cities.length; ++i) {
        	//대소문자 구분없음
        	String temp = cities[i];
        	temp = temp.toUpperCase();
        	if(list.remove(temp)) { //지워졌다면 있었다는 뜻이니까 hit
        		list.addLast(temp); //뒤에 넣어줌
        		answer += hit;
        	}else { //지워지지 않았다면 없었다는 뜻이니까 미스.
        		int cur = list.size();
        		if(cur==cacheSize) {//꽉 찼으면 앞에있는게 제일 오리된거니까 뺴줌
        			list.pollFirst(); //앞에잇는거 뺴줌 
        		}
        		list.addLast(temp); //뒤에다가 새거 넣어줌
        		answer += miss;
        	}
        }
        return answer;
    }
}
