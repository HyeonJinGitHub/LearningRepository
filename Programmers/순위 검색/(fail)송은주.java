import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

class Solution {
    ArrayList[] arr = new ArrayList[60];
	public int[] solution(String[] info, String[] query) {

        for(int i=0; i<60; ++i) {
        	arr[i] = new ArrayList<Integer>();
        }
        
        for(int i=0; i<info.length; ++i) {
        	String[] temp = info[i].split(" ");
        
        	int index = 1;
        	
        	switch(temp[1]) {
        	case "backend":
        		index = index*2;
        		break;
        	case "frontend":
        		index = index*2+1;
        		break;
        	}
        	
        	switch(temp[2]) {
        	case "junior":
        		index = index*2;
        		break;
        	case "senior":
        		index = index*2+1;
        		break;
        	}
        	
        	switch(temp[3]) {
        	case "chicken":
        		index = index*2;
        		break;
        	case "pizza":
        		index = index*2+1;
        		break;
        	}
        	//System.out.println(index);
        	switch(temp[0]) {
        	case "cpp":
        		arr[index].add(Integer.parseInt(temp[4]));
        		break;
        	case "java":
        		index+=15;
       		    arr[index].add(Integer.parseInt(temp[4]));
        		break;
        	case "python":
        		index+=30;
       		    arr[index].add(Integer.parseInt(temp[4]));
        		break;
        	}
        	
        }
        for(int i=0; i<arr.length; ++i){
            if(arr[i].size()!=0){
                Collections.sort(arr[i]);
            }
        }
        int[] answer = new int[query.length];
        for(int i=0; i<query.length; ++i){
            String[] temp = query[i].split(" ");
            // System.out.println(Arrays.toString(temp));
            // System.out.println(temp.length+"ㅎㅎ");
            answer[i] = go(temp, 1, 1);
        }
        return answer;
    }
    
    public int go(String[]temp, int depth, int index){
        // System.out.println(temp[depth]+","+depth+","+index);
        if("and".equals(temp[depth])){
            // System.out.println("냐옹잉?");
            return go(temp, depth+1, index);
        }
        if(depth==temp.length-1){
            // System.out.println("나르나르!"+temp[depth]+","+index);
            
            boolean flag = false;
            switch(temp[0]) {
        	case "java":
        		index+=15;
        		break;
        	case "python":
        		index+=30;
        		break;
            case "-":
                // System.out.println("-이다냥");
                flag = true;
                    
        	}
            int score = Integer.parseInt(temp[depth]);
            if(flag){
                int value = 0;
                for(int i=0; i<3; ++i){
                    int k = index+(15*i);
                // System.out.println(k+"슈기슈기슈기");    
                    if(arr[k].size()==0){
                // System.out.println("0리턴이다녀엉");
                value += 0;
                        
                        continue;
                }
                if(arr[k].size()==1){
                    // System.out.println("사이즈1리턴이다녀엉");
                    value = (int)arr[k].get(0)>=score?1:0;
                    continue;
                }

                int idx = Collections.binarySearch(arr[k], score);
                // System.out.println(idx+"이긔윤..");

                if(idx < 0){
                    idx += 1;
                    
                    idx *= -1;
                }
                    // System.out.println((arr[k].size() - idx)+"더하긔윤");
                    value += arr[k].size() - idx;    
                }
                return value;
            }else {
                if(arr[index].size()==0){
                // System.out.println("0리턴이다냥");
                return 0;
                }
                if(arr[index].size()==1){
                    // System.out.println("사이즈1리턴이다냥");
                    return (int)arr[index].get(0)>=score?1:0;
                }

                int idx = Collections.binarySearch(arr[index], score);
// System.out.println(idx+"이긔윤..~");
                if(idx < 0){
                    idx += 1;
                     
                    idx *= -1;
                }
            
                // System.out.println((arr[index].size() - idx)+"리턴하긔윤");
            return arr[index].size() - idx;    
            }
            
        }
        // System.out.println(temp[depth]+"나르~");
        switch(temp[depth]) {
                
        	case "backend":
        		return go(temp, depth+1, index*2);
                
        		// break;
        	case "frontend":
        		
                return go(temp, depth+1, index*2+1);
        		// break;
            case "junior":
        		return go(temp, depth+1, index*2);
        		// break;
        	case "senior":                
                return go(temp, depth+1, index*2+1);
        		// break;
            case "chicken":
        		return go(temp, depth+1, index*2);
        		// break;
        	case "pizza":
                return go(temp, depth+1, index*2+1);
        		// break; 
            case "-":
                return go(temp, depth+1, index*2) + go(temp, depth+1, index*2+1);
        }  
        // System.out.println(temp[depth]+","+depth+","+index+"뭐지진/자");
         // System.out.println("뭔가 잘못됐음;");
         return -1;
    }
    
}
