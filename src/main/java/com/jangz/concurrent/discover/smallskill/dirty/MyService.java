package com.jangz.concurrent.discover.smallskill.dirty;

public class MyService {
	
	public MyOneList addServiceMethod(MyOneList list, String data) {
		try {
			synchronized (list) {
				if (list.getSize() < 1) {
					Thread.sleep(2000);
					list.add(data);
				}
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		return list;
	}
}
