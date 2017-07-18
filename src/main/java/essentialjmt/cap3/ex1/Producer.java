package essentialjmt.cap3.ex1;

import essentialjmt.cap3.Source;

public class Producer implements Runnable {

	public Producer(Source source, int consumerCount) {
	}

	@Override
	public void run() {
	}

	public static String getDeathPillId() {
		return "#done#";
	}
}
