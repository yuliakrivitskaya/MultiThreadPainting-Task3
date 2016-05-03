/**
 * 
 * Thread ARTIST
 * 
 * @author Yulia Krivitskaya
 *
 */
public class Artist extends Thread {

	public Pencil pencil;
	public Eraser eraser;
	/** what in hand */
	public InHand hand;
	/** percent of painting picture */
	public int percentOfPainting = 0;

	/**
	 * Constructor
	 */
	public Artist() {
		pencil = new Pencil();
		eraser = new Eraser();
	}

	/**
	 * Override method run()
	 */
	@Override
	public void run() {
		System.out.println("Artist has decided to paint a picture");

		eraser.run();
		pencil.run();

		System.out.println("Artist has painted " + percentOfPainting + "% of picture");

		// until 100% will be painted
		while (percentOfPainting < 100) {
			synchronized (this) {
				takePencil();
			}

			synchronized (this) {
				takeEraser();
			}
			percentOfPainting += 10;
			System.out.println("Artist has painted " + percentOfPainting + "% of picture");
		}

	}

	/**
	 * Take pencil
	 */
	public synchronized void takePencil() {
		System.out.println("Artist has decided to take the pencil");
		if (hand != null && hand.equals(InHand.ERASER)) {
			eraser.putEraser();
		}
		pencil.takePencil();
		setHand(InHand.PENCIL);
	}

	/**
	 * Take Eraser
	 */
	public synchronized void takeEraser() {
		System.out.println("Artist has decided to take the eraser");

		if (hand != null && hand.equals(InHand.PENCIL)) {
			pencil.putPencil();
		}

		eraser.takeEraser();
		setHand(InHand.ERASER);
	}

	public InHand getHand() {
		return hand;
	}

	public void setHand(InHand hand) {
		this.hand = hand;
	}

}