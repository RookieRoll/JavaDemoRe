import entity.ListNode;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

	public static void main(String[] args) {
		NQueen nqueen = new NQueen(4);
		nqueen.found(0);
		Executor executors = Executors.newCachedThreadPool();
		Executors.newFixedThreadPool(2);
		Executors.newScheduledThreadPool();
		Executors.newSingleThreadExecutor();
		Executors.newSingleThreadScheduledExecutor();
		Executors.newWorkStealingPool();

		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor();

	}
}
