import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Info{
   int x;
   int y;
   public Info(int x, int y) {
      super();
      this.x = x;
      this.y = y;
   }
}

public class Main{
   private static int R;
   private static int C;
   private static int T;
   private static int[] dx = {0, 0, 1, -1};
   private static int[] dy = {1, -1, 0, 0};
   private static int[][] input;
	private static int m1;
	private static int m2;

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine(), " ");
      
      R = Integer.parseInt(st.nextToken());
      C = Integer.parseInt(st.nextToken());
      T = Integer.parseInt(st.nextToken());
      
      input = new int[R][C];
      m1 = -1;
      m2 = -1;
      for(int i=0; i<R; ++i) {
         st = new StringTokenizer(br.readLine(), " ");
         for(int j=0; j<C; ++j) {
            input[i][j] = Integer.parseInt(st.nextToken());
            if(input[i][j]==-1) {
               if(m1==-1) m1 = i;
               else m2 = i;
               continue;
            }
         }
      }
      
      for(int i=0; i<T; ++i) {
         diffuse();
         
         miseClean();
      }
      int anw = 0;
      for(int i=0; i<R; ++i) {
    	  for(int j=0; j<C; ++j) {
    		  if(input[i][j]==-1) continue;
    		  anw += input[i][j];
    	  }
      }
      
      System.out.println(anw);
   }

	private static void miseClean() {
		List<Integer>arr = new ArrayList<>();
		int n=m1, m=0;
		for(int i=0; i<C-1; ++i) {
			arr.add(input[n][m++]);
		}
		
		for(int i=0; i<m1; ++i) {
			arr.add(input[n--][m]);
		}
		
		for(int i=0; i<C-1; ++i) {
			arr.add(input[n][m--]);
		}
		
		for(int i=0; i<m1; ++i) {
			arr.add(input[n++][m]);
		}
//		System.out.println(arr.toString());
		List<Integer> last = new ArrayList<>();
		last.add(-1);
		last.addAll(arr);
//		System.out.println(last.toString());
		
		int idx = 0;
		n=m1; m=0;
		for(int i=0; i<C-1; ++i) {
			input[n][m++] = last.get(idx++);
		}
		
		for(int i=0; i<m1; ++i) {
			input[n--][m] = last.get(idx++);
		}
		
		for(int i=0; i<C-1; ++i) {
			input[n][m--] = last.get(idx++);
		}
		
		for(int i=0; i<m1; ++i) {
			input[n++][m] = last.get(idx++);
		}
		input[m1][1] = 0;
		arr = new ArrayList<>();
		
		n=m2; m=0;
		for(int i=0; i<C-1; ++i) {
			arr.add(input[n][m++]);
		}
		
		for(int i=m2; i<R-1; ++i) {
			arr.add(input[n++][m]);
		}
		
		for(int i=0; i<C-1; ++i) {
			arr.add(input[n][m--]);
		}
		
		for(int i=m2; i<R-1; ++i) {
			arr.add(input[n--][m]);
		}
		
		last = new ArrayList<>();
		last.add(-1);
		last.addAll(arr);
		
		n=m2; m=0; idx=0;
		for(int i=0; i<C-1; ++i) {
			input[n][m++] = last.get(idx++);
		}
		
		for(int i=m2; i<R-1; ++i) {
			input[n++][m] = last.get(idx++);
		}
		
		for(int i=0; i<C-1; ++i) {
			input[n][m--] = last.get(idx++);
		}
		
		for(int i=m2; i<R-1; ++i) {
			input[n--][m] = last.get(idx++);
		}
		input[m2][1] = 0 ; 
		/*
		for(int i=0; i<R; ++i) {
			for(int j=0; j<C; ++j) {
				System.out.print(input[i][j] + "\t");
			}
			System.out.println();
		}*/
	}

	private static void diffuse() {
		int[][] temp = new int[R][C];
		
		for(int i=0; i<R; ++i) {
			for(int j=0; j<C; ++j) {
				if(input[i][j]==-1) continue;
				int t = 0;
				for(int k=0; k<4; ++k) {
					int nX = i + dx[k];
					int nY = j + dy[k];
					
					if(0<=nX && nX < R && 0<=nY && nY < C) {
						if(input[nX][nY]==-1) continue;
						t++;
					}
					
				}
				for(int k=0; k<4; ++k) {
					int nX = i + dx[k];
					int nY = j + dy[k];
					
					if(0<=nX && nX < R && 0<=nY && nY < C) {
						if(input[nX][nY]==-1) continue;
						temp[nX][nY] += input[i][j]/5;
					}
				}
				
				input[i][j] = input[i][j] - (input[i][j]/5 * t);
				
			}
		}
		
//		System.out.println("확산후");
		for(int i=0; i<R; ++i) {
			for(int j=0; j<C; ++j) {
				input[i][j] += temp[i][j];
//				System.out.print(input[i][j] + "\t");
			}
//			System.out.println();
		}
//		System.out.println("=========");
		
	}
}
