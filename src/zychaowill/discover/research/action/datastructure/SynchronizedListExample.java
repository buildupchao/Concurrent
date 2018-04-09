package zychaowill.discover.research.action.datastructure;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SynchronizedListExample {

	public static void main(String[] args) {
		List<Integer> synchronizedList = Collections.synchronizedList(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
		synchronizedList.size();
	}
}
