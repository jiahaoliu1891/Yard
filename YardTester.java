import java.util.Random;

public class YardTester implements Runnable {

	private final Yard yard;
	private final Random rand;
	private final int numRepeats;

	YardTester(int repeats) {
		yard = new Yard();
		rand = new Random();
		numRepeats = repeats;
	}

	public void run() {
		// Run your tests here!
		try {
			// Repeatedly+randomly act as one of Alice's or Bob's pets
			// Feel free to modify this to whatever will make your tests easier
			for (int i = 0; i < numRepeats; i++) {
				yard.enterAlicePet();
				assert (yard.bobPets == 0); // Assert there are no Bob pets around
				yard.enterAlicePet();
				assert (yard.bobPets == 0);
				yard.leaveAlicePet();
				assert (yard.bobPets == 0);
				yard.leaveAlicePet();
				yard.enterBobPet();
				assert (yard.alicePets == 0);
				yard.enterBobPet();
				assert (yard.alicePets == 0); // Assert there are no Alice pets around
				yard.leaveBobPet();
				assert (yard.alicePets == 0);
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
