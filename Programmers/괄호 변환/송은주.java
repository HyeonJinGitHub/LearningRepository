
class Solution {
    public static String solution(String p) {
        return go(p);
    }
    
    private static String go(String p) {
//    	System.out.println(p+"를 데리고 왔어요");
		if("".equals(p)) {
//			System.out.println("비어있어서 빈 문자 리턴해요");
			return "";
		}
		StringBuilder U = new StringBuilder();
		StringBuilder V = new StringBuilder();
		int cnt = 0;
		int right = 0;
		int left = 0;
		boolean flag = false;
		for(int i=0; i<p.length(); ++i) {
			if(p.charAt(i)=='(') {
				cnt += 1;
				right += 1;
				U.append(p.charAt(i));
			}
			if(p.charAt(i)==')') {
				if(cnt==0) cnt += 1000;
				else cnt-=1;
				left += 1;
				U.append(p.charAt(i));
			}
			if(right==left) {
				if(cnt==0) flag = true;
				
				for(int k=i+1; k<p.length(); ++k) {
					V.append(p.charAt(k));
				}
				break;
			}
		}
		String u = U.toString();
		String v = V.toString();
//		System.out.println(u+"랑, "+v+"로 나눴어요.");
		
		if(flag) {
//			System.out.println(u+"는 올바른 괄호 문자열이에요.");
			String result = u+go(v);
//			System.out.println(result+"를 리턴할게요.");
			return result;
		}
		
//		System.out.println(u+"는 올바른 괄호 문자열이 아니에요.");
		String result = "(";
//		System.out.println(v+"에 대해 다시 재귀함수를 불러올게요.");
		
		result += go(v);
//		System.out.println("불러온 결과,  ( 가 하나 붙고, " + result+"됐어요");
		result += ")";
		
//		System.out.println("짝으로 마지막 처음 괄호 묶고, " +result+"됐어요. 이제 뒤집을게요");
		for(int i=1; i<u.length()-1; ++i) {
			if(u.charAt(i)==')') {
				result += "(";
			}else result += ")";
		}
		
//		System.out.println(result+"가 최종입니다.");
		return result;
		
	}

	public static void main(String[] args) {
		System.out.println(solution("(()())()"));
	}
}
