package zychaowill.discover.smallskill.communication.pc.piped;

import java.io.IOException;
import java.io.PipedOutputStream;

public class WriteData {

	public void writeMetho(PipedOutputStream out) {
		try {
			for (int i = 0; i < 300; i++) {
				String outData = "" + (i + 1);
				out.write(outData.getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
