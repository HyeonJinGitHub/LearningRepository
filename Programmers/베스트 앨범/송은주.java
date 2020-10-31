import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Music{
	String genre;
	int cnt;
	public Music(String genre, int cnt) {
		super();
		this.genre = genre;
		this.cnt = cnt;
	}
}
public class Main {
	private static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine().trim());
		
		Music[] music = new Music[N];
		Map<String, Integer>map = new HashMap<String, Integer>();
		
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			String gen = st.nextToken();
			int cnt = Integer.parseInt(st.nextToken());
			
			music[i] = new Music(gen, cnt);
			
			map.put(gen, map.getOrDefault(gen, 0)+cnt);
			
		}
		
		
		ArrayList<Music>arr= new ArrayList<>();
		
		for(Map.Entry<String, Integer>entry:map.entrySet()) {
			String gen = entry.getKey();
			int cnt = entry.getValue();
			
			arr.add(new Music(gen, cnt));
		}
		
		Collections.sort(arr, new Comparator<Music>() {
			@Override
			public int compare(Music o1, Music o2) {
				return o2.cnt - o1.cnt;
			}
		});
		/*
		for(Music m: arr) {
			System.out.println(m.genre+ ","+ m.cnt);
		}
		*/
		for(int i=0; i<arr.size(); ++i) {
			int max = -1;
			int tempCnt=0;
			int index = 0;
			String gr = arr.get(i).genre;
			for(int j=0; j<N; ++j) {
				if(gr.equals(music[j].genre)) {
					tempCnt++;
					if(music[j].cnt > max) {
						max = music[j].cnt;
						index = j;
					}
				}
			}
			int k = index;
			System.out.println(index);
			if(tempCnt != 1) {
				index = 0;
				max = -1;
				for(int j=0; j<N; ++j) {
					if(j==k) continue;
					if(gr.equals(music[j].genre)) {
						tempCnt++;
						if(music[j].cnt > max) {
							max = music[j].cnt;
							index = j;
						}
					}
				}
				System.out.println(index);
			}
		}
	}
}
