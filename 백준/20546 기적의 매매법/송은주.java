import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class People{
	int seedMoney;
	int upflag;
	int downflag;
	int stock;
	public People(int seedMoney, int upflag, int downflag, int stock) {
		super();
		this.seedMoney = seedMoney;
		this.upflag = upflag;
		this.downflag = downflag;
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "People [seedMoney=" + seedMoney + ", upflag=" + upflag + ", downflag=" + downflag + ", stock=" + stock
				+ "]";
	}
}

class Main{
	private static int money;
	private static int[] input;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		money = Integer.parseInt(br.readLine());
		
		input = new int[14];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<14; ++i) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		People junhyun = new People(money, 0, 0, 0);
		People sungmin = new People(money, 0, 0, 0);
		
		for(int i=0; i<14; ++i) { //준현이 전략
			if(input[i]==0) continue;
			if(junhyun.seedMoney >= input[i]) {
				int temp = junhyun.seedMoney / input[i];
				junhyun.seedMoney -= temp*input[i];
				junhyun.stock += temp;
			}
		}
		
		for(int i=0; i<14; ++i) { //성민이 전략
			if(i!=0) {
				if(input[i-1] < input[i]) {
					sungmin.upflag += 1;
					sungmin.downflag = 0;
				}else if(input[i-1] > input[i]) {
					sungmin.downflag += 1;
					sungmin.upflag = 0;
				}
			}
			if(sungmin.upflag>=3) { //3일 내내 오른다? 갖다 팜
				if(input[i]==0) continue;
				sungmin.seedMoney += input[i] * sungmin.stock;
				sungmin.stock = 0;
			}else if(sungmin.downflag>=3) { //3일 내내 내린다? 다 삼
				if(input[i]==0) continue;
				if(sungmin.seedMoney >= input[i]) {
					int temp = sungmin.seedMoney / input[i];
					sungmin.seedMoney -= temp*input[i];
					sungmin.stock += temp;
				}
			}
		}

		if(sungmin.seedMoney+input[13]*sungmin.stock > junhyun.seedMoney+input[13]*junhyun.stock) {
			System.out.println("TIMING");
		}else if(sungmin.seedMoney+input[13]*sungmin.stock < junhyun.seedMoney+input[13]*junhyun.stock) {
			System.out.println("BNP");
		}else System.out.println("SAMESAME");
		
	}
}
