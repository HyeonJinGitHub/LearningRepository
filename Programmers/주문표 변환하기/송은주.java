import java.util.Scanner;
import java.util.Stack;

class Main {
  private static int[] pair;

private static void solution(int numOfOrder, String[] orderArr) {
	  StringBuilder sb = new StringBuilder();
	  for(int z=0; z<numOfOrder; ++z) {
		  String target = orderArr[z];
		  pair = new int[target.length()];
		  
		  Stack<Integer> stk = new Stack<Integer>();
		  
		  //일단 괄호끼리 짝지
		  for(int i=0; i<target.length(); ++i) {
			  if(target.charAt(i)=='(') stk.push(i);
			  else if(target.charAt(i)==')') {
				  pair[stk.pop()] = i;
			  }
		  }
		  
		  System.out.println(gogogo(0, target.length(), target));
		  
		  
		  
	  }
  }

  private static String gogogo(int idx, int length, String target) {
	String res = "";
	for(int i=idx; i<length; ++i) {
		if(target.charAt(i)=='(') {
			char c = target.charAt(i-1);
			if('0' <= c && c <='9') { //숫자면
				String temp = gogogo(i+1, pair[i], target);
				for(int k=0; k<c-'0'-1; ++k) {
					res += temp;
				}
			}else if('A'<= c && c <='Z') { //문자면
				String temp = gogogo(i+1, pair[i], target);
				String nTemp = "";
				for(int k=0; k<temp.length(); ++k) {
					if(k>=1) nTemp += c;
					nTemp += temp.charAt(k);
				}
				res += nTemp;
				i+= pair[i] - i;
			}
		}else if(target.charAt(i)==')'){ //)면 넘어가고
		}else if('0' <= target.charAt(i) && target.charAt(i) <='9'){ //숫잔데 다음에 괄호없는경우
			if(target.charAt(i+1) != '(') {
				for(int k=0; k<target.charAt(i)-'0'; ++k) {
					res += target.charAt(i+1);
				}
				i++;				
			}
		}else { //그외
			res += target.charAt(i);
		}
	}
//	System.out.println("리턴할문자열"+res);
	return res;
}

private static class InputData {
    int numOfOrder;
    String[] orderArr;
  }

  private static InputData processStdin() {
    InputData inputData = new InputData();

    try (Scanner scanner = new Scanner(System.in)) {
      inputData.numOfOrder = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));

      inputData.orderArr = new String[inputData.numOfOrder];
      for (int i = 0; i < inputData.numOfOrder; i++) {
        inputData.orderArr[i] = scanner.nextLine().replaceAll("\\s+", "");
      }
    } catch (Exception e) {
      throw e;
    }

    return inputData;
  }

  public static void main(String[] args) throws Exception {
    InputData inputData = processStdin();

    solution(inputData.numOfOrder, inputData.orderArr);
  }
}
