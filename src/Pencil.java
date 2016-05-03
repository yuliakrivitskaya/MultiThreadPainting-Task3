/**
 * Thread PENCIL
 * 
 * @author Yulia Krivitskaya
 *
 */
public class Pencil extends Thread {

	public Pencil() {
	}

	/**
	 * Override method run()
	 */
	@Override
	public void run() {
		System.out.println("Pencil here");
	}

	/**
	 * Take pencil unlock thread
	 */
	public void takePencil() {
		unlock();
		System.out.println("Artist is drowing with the pencil");
	}

	/**
	 * Put pencil lock thread
	 */
	public void putPencil() {
		System.out.println("Artist has draw enough");
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
		System.out.println("Artist has put the pencil");
	}

	/**
	 * unlock thread
	 */
	public synchronized void unlock() {
		isLocked = false;
		notify();
		System.out.println("Artist is taking the pencil");
	}
}
