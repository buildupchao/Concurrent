package com.jangz.concurrent.discover.smallskill.communication.base;

import java.util.ArrayList;
import java.util.List;

public class MyList {
	
	private static List<String> list = new ArrayList<>();
	
	public static void add() {
		list.add("anyThing");
	}
	
	public static int size() {
		return list.size();
	}
}
