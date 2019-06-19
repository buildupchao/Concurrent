package com.buildupchao.concurrent.discover.smallskill.dirty;

import java.util.ArrayList;
import java.util.List;

public class MyOneList {
	private List<String> list = new ArrayList<>();
	
	public synchronized void add(String data) {
		list.add(data);
	}
	
	public synchronized int getSize() {
		return list.size();
	}
}
