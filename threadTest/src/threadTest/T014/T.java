package threadTest.T014;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Atomic 的类中可以保证线程的原子性
 * @author LiXianZhong
 *
 */
public class T {

	AtomicInteger account = new AtomicInteger();
	
	public void m1(){
		for (int i = 0; i < 10000; i++) 
			account.incrementAndGet();
	}
	public static void main(String[] args) {
		T t = new T();
		ArrayList<Thread> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(t::m1, "t" + i);
			list.add(thread);
		}
		list.forEach(o -> o.start());
		list.forEach(o -> {
			try {
				o.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		System.out.println(t.account);
	}

}
