public class RotateArray {
	private static int n;

	public static void main(String[] args) {
		n = 5;
		int[][] arr = new int[n][n]; //정사각형만 가능
		int cnt = 1;
		for(int i=0; i<n; ++i) {
			for(int j=0; j<n; ++j) {
				arr[i][j] = cnt++;
			}
		}
		
		System.out.println("===변경 전===");
		
		for(int i=0; i<n; ++i) {
			for(int j=0; j<n; ++j) {
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
		// 1	2	3	4	5	
		// 6	7	8	9	10	
		// 11	12	13	14	15	
		// 16	17	18	19	20	
		// 21	22	23	24	25
		
		int temp[][] = rotateRight(arr);
		
		System.out.println("===시계방향 변경 후===");
		for(int i=0; i<n; ++i) {
			for(int j=0; j<n; ++j) {
				System.out.print(temp[i][j] + "\t");
			}
			System.out.println();
		}
		// 21	16	11	6	1	
		// 22	17	12	7	2	
		// 23	18	13	8	3	
		// 24	19	14	9	4	
		// 25	20	15	10	5		
		
		temp = rotateLeft(arr);
		System.out.println("===반시계방향 변경 후===");
		for(int i=0; i<n; ++i) {
			for(int j=0; j<n; ++j) {
				System.out.print(temp[i][j] + "\t");
			}
			System.out.println();
		}
		// 5	10	15	20	25	
		// 4	9	14	19	24	
		// 3	8	13	18	23	
		// 2	7	12	17	22	
		// 1	6	11	16	21		
		
		
		temp = reverseTopBottom(arr);
		System.out.println("===위아래 뒤집은 후===");
		for(int i=0; i<n; ++i) {
			for(int j=0; j<n; ++j) {
				System.out.print(temp[i][j] + "\t");
			}
			System.out.println();
		}
		// 21	22	23	24	25	
		// 16	17	18	19	20	
		// 11	12	13	14	15	
		// 6	7	8	9	10	
		// 1	2	3	4	5
		
		temp = reverseLeftRight(arr);
		System.out.println("===좌우반전 후===");
		for(int i=0; i<n; ++i) {
			for(int j=0; j<n; ++j) {
				System.out.print(temp[i][j] + "\t");
			}
			System.out.println();
		}
		// 5	4	3	2	1	
		// 10	9	8	7	6	
		// 15	14	13	12	11	
		// 20	19	18	17	16	
		// 25	24	23	22	21		
		
	}


	//시계방향
	private static int[][] rotateRight(int[][] arr) {
		int[][] next = new int[n][n];
		
		for(int i=0; i<n; ++i) {
			for(int j=0; j<n; ++j) {
				next[i][j] = arr[n-j-1][i];
			}
		}
		
		return next;
	}
	
	//반시계방향
	private static int[][] rotateLeft(int[][] arr) {
		int[][] next = new int[n][n];
		
		for(int i=0; i<n; ++i) {
			for(int j=0; j<n; ++j) {
				next[i][j] = arr[j][n-i-1];
			}
		}
		
		return next;
	}

	//상하뒤집기
	private static int[][] reverseTopBottom(int[][] arr){
		int[][] next = new int[n][n];
		
		for(int i=0; i<n; ++i) {
			for(int j=0; j<n; ++j) {
				next[i][j] = arr[n-i-1][j];
			}
		}
		
		return next;
	}
	
	//좌우반전
	private static int[][] reverseLeftRight(int[][] arr) {
		int[][] next = new int[n][n];
		
		for(int i=0; i<n; ++i) {
			for(int j=0; j<n; ++j) {
				next[i][j] = arr[i][n-j-1];
			}
		}
		return next;
	}
	

}
