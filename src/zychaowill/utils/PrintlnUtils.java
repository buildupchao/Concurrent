package zychaowill.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class PrintlnUtils {

	public static void println(Object o) {
		try {
			OutputStream out = new FileOutputStream(new File("D:\\jangz\\git\\orders.log"), true);
			out.write((o.toString() + "\n").getBytes());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
