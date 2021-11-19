// 1 -> 1 ->
//

public class TestYard {
	public static void main(String[] args) {
		int numRepeats = 10000;
		int numThr = 20;
		if (args.length >= 2) {
			numThr = Integer.parseInt(args[0]);
			numRepeats = Integer.parseInt(args[1]);
		}

		System.out.println("numRepeats: " + numRepeats);

		Thread threads[] = new Thread[numThr];
		YardTester tester = new YardTester(numRepeats);

		for (int i = 0; i < numThr; i++) {
			threads[i] = new Thread(tester);
		}

		for (int i = 0; i < numThr; i++) {
			threads[i].start();
		}

		try {
			for (int i = 0; i < numThr; i++) {
				threads[i].join();
			}
		} catch (InterruptedException ex) {
			System.out.println("Something went wrong during joining.");
		}
	}

}
