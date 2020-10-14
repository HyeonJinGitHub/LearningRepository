import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main{
	private static int N;
	private static int M;
	private static int K;
	private static int[] dx= {-1, -1, -1, 0, 0, 1, 1, 1};
	private static int[] dy= {-1, 0, 1, -1, 1, -1, 0, 1};
	private static int[][] input;
	private static int[][] soil;
	private static ArrayList[][] tree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		input = new int[N][N];
		soil = new int[N][N];
		tree = new ArrayList[N][N];
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; ++j) {
				tree[i][j] = new ArrayList<Integer>();
				soil[i][j] = Integer.parseInt(st.nextToken());
				input[i][j] = 5;
			}
		}
		
		for(int i=0; i<M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			tree[x-1][y-1].add(age);
		}
		for(int age=0; age<K; ++age) {
			
			for(int i=0; i<N; ++i) {
				for(int j=0; j<N; ++j) {
					//봄
					if(tree[i][j].size()>=1) {
//						System.out.println(i+","+j);
						ArrayList<Integer> temp = new ArrayList<>();
						Collections.sort(tree[i][j]);
						int dead = 0;
						for(int k=0; k<tree[i][j].size(); ++k) {
							int tempTree = (int) tree[i][j].get(k);
							if(input[i][j] - tempTree >=0) {
								input[i][j] -= tempTree;
//								System.out.println(input[i][j] +"영양분");
								temp.add(tempTree+1);
							}else {
								dead += tempTree/2;
							}
						}
//						System.out.println(temp.toString());
						tree[i][j].clear();
						tree[i][j].addAll(temp);
						input[i][j] += dead;
//						System.out.println("=====");
					}
					input[i][j] += soil[i][j];
				}
				
			}//end of N N for
//			System.out.println("================");
			for(int i=0; i<N; ++i) {
				for(int j=0; j<N; ++j) {
					if(tree[i][j].size()!=0) {
						for(int k=0; k<tree[i][j].size(); ++k) {
							int treeTemp = (int) tree[i][j].get(k);
							if(treeTemp%5==0) {
								for(int f=0; f<8; ++f) {
									int nX = i + dx[f];
									int nY = j + dy[f];
									
									if(0<=nX && nX < N && 0<=nY && nY < N) {
										tree[nX][nY].add(1);
									}
								}
							}
						}
					}
				}
			}
			
		}//end of age K
		int cnt = 0;
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				cnt += tree[i][j].size();
			}
		}
		
		System.out.println(cnt);
	}
}
