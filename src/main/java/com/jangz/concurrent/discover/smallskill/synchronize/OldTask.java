package com.jangz.concurrent.discover.smallskill.synchronize;

public class OldTask implements Task {
	private String getData1;
	private String getData2;
	
	@Override
	public void doLongTimeTask() {
		try {
			System.out.println("begin task");
			Thread.sleep(3000);
			String privateGetDeta1 = "长时间处理任务后从远程返回的值1 threadName=" + Thread.currentThread().getName();
			String privateGetDeta2 = "长时间处理任务后从远程返回的值2 threadName=" + Thread.currentThread().getName();
			
			synchronized (this) {
				getData1 = privateGetDeta1;
				getData2 = privateGetDeta2;
			}
			System.out.println(getData1);
			System.out.println(getData2);
			System.out.println("end task");
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
