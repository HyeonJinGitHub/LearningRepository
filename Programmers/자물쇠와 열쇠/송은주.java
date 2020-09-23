class Solution {
	public boolean solution(int[][] key, int[][] lock) {
		int size = lock.length-1; //이 사이즈가 왜 이런지 알기..
		
		for(int d=0; d<4; ++d) {
			int[][] rotateKey = rotate(d, key);
			int[][] paddingKey = padding(rotateKey, size);
			
			for(int i=0; i<paddingKey.length - size; ++i) {
				for(int j=0; j<paddingKey[0].length-size; ++j) {
					boolean flag = true;
					
					for(int r=0; r<lock.length; ++r) {
						for(int c=0; c<lock[0].length; ++c) {
							if(lock[r][c] == paddingKey[i+r][j+c])
								flag = false;
						}
					}
					
					if(flag) return true;
				}
			}
		}
		return false;
	}

	private int[][] padding(int[][] arr, int size) {
		int[][] result = new int[arr.length+size*2][arr[0].length+size*2];
		
		for(int r=0; r<arr.length; ++r) {
			for(int c=0; c<arr[0].length; ++c) {
				result[r+size][c+size] = arr[r][c]; 
			}
		}
		//print(result);
		return result;
	}

	private void print(int[][] result) {
		for(int i=0; i<result.length; ++i) {
			for(int j=0; j<result[0].length; ++j) {
				System.out.print(result[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("===========");
	}

	private int[][] rotate(int d, int[][] key) {
		if(d==0) return key;
		int l = key.length;
		int[][] k = new int[l][l];
		
		for(int i=0; i<l; ++i) {
			for(int j=0; j<l; ++j) {
				k[i][j] = key[i][j];
			}
		}
		
		int[][] result = null;
		for(int i=0; i<d; ++i) {
			result = new int[l][l];
			
			for(int a=0; a<l; ++a) {
				for(int b=0; b<l; ++b) {
					result[a][b] = k[l-b-1][a];
				}
			}
			
			k = result;
		}
		return result;
	}
}
