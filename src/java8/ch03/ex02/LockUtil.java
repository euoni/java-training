package java8.ch03.ex02;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;

public class LockUtil {
	public static <V> V withLock(Lock lock, Callable<V> task) throws Exception {
		lock.lock();
		try {
			return task.call();
		} finally {
			lock.unlock();
		}
	}
}
