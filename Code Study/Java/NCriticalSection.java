class Test {
	public static void main(String[] arg) {
		Buffer b = new Buffer(100);
		Producer p = new Producer(b, 10000);
		Consumer c = new Consumer(b, 10000);
		p.start();
		c.start();
		try {
			p.join();
			c.join();
		} catch (InterruptedException e) {}
		System.out.println("Number of items in the buf is " + b.count);
	}
}

class Buffer {
	int[] buf;
	int size;
	int count;
	int in;
	int out;
	Buffer(int size) {
		buf = new int[size];
		this.size = size;
		count = in = out = 0;
	}
	void insert(int item) {
		/* check if buf is full */
		while (count == size)
			;
		/* buf is not full */
		buf[in] = item;
		in = (in+1)%size;
		count++;
	}
	int remove() {
		/* check if buf is empty */
		while (count == 0)
			;
		/* buf is not empty */
		int item = buf[out];
		out = (out+1)%size;
		count--;
		return item;
	}
}

/****** 생산자 ******/
class Producer extends Thread {
	Buffer b;
	int N;
	Producer(Buffer b, int N) {
		this.b = b; this.N = N;
	}
	public void run() {
		for (int i=0; i<N; i++)
			b.insert(i);
	}
}
/****** 소비자 ******/
class Consumer extends Thread {
	Buffer b;
	int N;
	Consumer(Buffer b, int N) {
		this.b = b; this.N = N;
	}
	public void run() {
		int item;
		for (int i=0; i<N; i++)
			item = b.remove();
	}
}
