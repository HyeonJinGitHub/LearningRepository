import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	private static int N;
	private static int[][] picture;
	private static int M;
	private static int pointer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		picture = new int[N][3]; //[i][0]: 학생번호, [i][1]: 추천받은횟수 [i][2]: 오래된횟수
		for(int i=0; i<N; ++i) {
			picture[i][0] = -1;
		}
		
		pointer = 0;
		M = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		HashMap<Integer, Integer>hm = new HashMap<>(); //K:학생번호, V:그 학생번호 인덱스
		for(int i=0; i<M; ++i) {
			int now = Integer.parseInt(st.nextToken());
			
			if(hm.containsKey(now)) {
				picture[hm.get(now)][1]++; //추천받은 횟수 증가
				continue;
			}else {
				int less = Integer.MAX_VALUE;
				int lessIdx = -1;
				
				boolean emptyFlag = false; //비어있는지 여부 확인
				for(int j=0; j<N; ++j) {
					if(picture[j][0]==-1) {
						//틀이 비어있었다는 뜻임
						emptyFlag = true;
						picture[j][0] = now;
						picture[j][1]++;
						
						hm.put(now, j);
//						print();
						break;
					}
					picture[j][2]++; //아니면 하나씩 증가해야돼
					
					if(less >= picture[j][1]) {
						if(less==picture[j][1]) {
							if(picture[j][2] < picture[lessIdx][2]) {
								continue;
							}else {
								less = picture[j][1];
								lessIdx = j;						
							}
						}else {
							less = picture[j][1];
							lessIdx = j;
						}
					}
					
				}
				
				if(emptyFlag) continue;
				
				hm.remove(picture[lessIdx][0]);
				hm.put(now, lessIdx);
				
				picture[lessIdx][0] = now;
				picture[lessIdx][1] = 1;
				picture[lessIdx][2] = 0;
			}
		}

		StringBuilder sb = new StringBuilder();
		List<Integer>arr = new ArrayList<>();
		for(int i=0; i<picture.length; ++i) {
			if(picture[i][0]==-1) continue;
			arr.add(picture[i][0]);
		}
		
		Collections.sort(arr);
		for(int i=0; i<arr.size(); ++i) {
			sb.append(arr.get(i)).append(' ');
		}
		System.out.print(sb);
	}

}
