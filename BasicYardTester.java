import java.util.Random;

public class BasicYardTester implements Runnable {

	private final Yard yard;
	private final Random rand;
	private final int numRepeats;

	BasicYardTester(int repeats) {
		yard = new Yard();
		rand = new Random();
		numRepeats = repeats;
	}

	public void run() {
		try {
			// Repeatedly+randomly act as one of Alice's or Bob's pets
			// Feel free to modify this to whatever will make your tests easier
			for (int i = 0; i < numRepeats; i++) {
				// if (i % 2000 == 0) {
				// 	System.out.println(Thread.currentThread().getName() + ' ' + i);
				// }
				yard.enterAlicePet();
				assert (yard.bobPets == 0); // Assert there are no Bob pets around
				yard.leaveAlicePet();
				yard.enterBobPet();
				assert (yard.alicePets == 0); // Assert there are no Alice pets around
				yard.leaveBobPet();
			}
			System.out.println(Thread.currentThread().getName() + " Finish!");
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("---" + Thread.currentThread().getName());
			System.out.println(e.getMessage());
		}
	}
}
