package zychaowill.discover.research.action.datastructure;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWriteArrayListExample {

	public static void main(String[] args) {
		CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
		list.add(1);
		list.add(1);
		
		CopyOnWriteArraySet<Integer> set = new CopyOnWriteArraySet<>();
		set.add(1);
		set.add(1);
	}
}
