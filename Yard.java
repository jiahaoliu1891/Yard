import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.*;

public class Yard {
	// You CANNOT use AtomicIntegers and the like in
	// your solution-- only Locks and Conditions (and, of course,
	// non-concurrent data types) are allowed.
	// Another thing to keep in mind: you are running a program
	// with a finite number of threads. Depending on your strategy,
	// this may be something you need to work around, but we don't
	// suggest you take advantage of it.
	public int alicePets = 0;
	public int bobPets = 0;
	private final ReentrantLock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	Queue<Long> queue = new LinkedList<>();
	Map<Long, Integer> map = new HashMap<>();
	// 1 -> 1 -> 0 -> 0
	public void enterAlicePet() throws InterruptedException {
		// Fill out your implementation for this method below
		lock.lock();
		try {
			Long id = Thread.currentThread().getId();
			if(map.getOrDefault(id, 0) == 0) {
				queue.add(id);
				while (bobPets != 0 || queue.peek() != id) {					
					condition.await();
				}
				queue.remove();
			}
			map.put(id, map.getOrDefault(id, 0) + 1);
			alicePets++;
		} finally {
			lock.unlock();
		}
	}

	public void leaveAlicePet() throws InterruptedException {
		// Fill out your implementation for this method below
		lock.lock();
		try {
			alicePets--;
			Long id = Thread.currentThread().getId();
			map.put(id, map.get(id) - 1);
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}

	public void enterBobPet() throws InterruptedException {
		lock.lock();
		try {
			Long id = Thread.currentThread().getId();
			if(map.getOrDefault(id, 0) == 0) {
				queue.add(id);
				while (alicePets != 0 || queue.peek() != id) {					
					condition.await();
				}
				queue.remove();
			}
			map.put(id, map.getOrDefault(id, 0) + 1);
			bobPets++;
		} finally {
			lock.unlock();
		}
	}

	public void leaveBobPet() throws InterruptedException {
		lock.lock();
		try {
			bobPets--;
			Long id = Thread.currentThread().getId();
			map.put(id, map.get(id) - 1);
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}

}
