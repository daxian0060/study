package threadTest.T019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * wait �� notify ������waitʹ�̵߳ȴ����������������Ķ������wait���ͷ���
 * ���������̶߳Ը���notify�����ͷ���
 * @author LiXianZhong
 *
 */
public class MyCoontainer {
	List<Object> list = new ArrayList<>();
	void add(){
		list.add(new Object());
	}
	int size(){
		return list.size();
	}
	public static void main(String[] args) {
		MyCoontainer c = new MyCoontainer();
		Object lock = new Object();
		Thread t1 = new Thread(()->{
			synchronized (lock) {
				if(c.size()!=5)
					try {
						System.out.println("t1���ڵȴ�");
						lock.wait();
						lock.notify();
						System.out.println("�ȴ�����");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
		}, "t1");
		
		Thread t2 = new Thread(()->{
			synchronized (lock) {
				for (int i = 0; i < 10; i++) {
					c.add();
					System.out.println("add -> " + i);
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(i == 5){
						lock.notify();
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}, "t2");
		t1.start();
		t2.start();
		
		
	}

}
