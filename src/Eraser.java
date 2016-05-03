
/**
 * Thread ERASER
 * 
 * @author Yulia Krivitskaya
 *
 */
public class Eraser extends Thread {

	public Eraser() {
	}

	/**
	 * Override method run()
	 */
	@Override
	public void run() {
		System.out.println("Eraser here");

	}

	/**
	 * Take eraser unlock thread
	 */
	public void takeEraser() {
		System.out.println("Artist want to take eraser");
		unlock();
	}

	/**
	 * Put eraser lock thread
	 */
	public void putEraser() {
		System.out.println("Artist has erased enough");
		try {
			lock();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/** if thread lock */
	private boolean isLocked = false;

	/**
	 * lock thread
	 * 
	 * @throws InterruptedException
	 */
	public synchronized void lock() throws InterruptedException {
		while (isLocked) {
			wait();
		}
		isLocked = true;
		System.out.println("Artist has put the eraser");
	}

	/**
	 * unlock thread
	 */
	public synchronized void unlock() {
		isLocked = false;
		notify();
		System.out.println("Artist is taking the eraser");
	}
}
