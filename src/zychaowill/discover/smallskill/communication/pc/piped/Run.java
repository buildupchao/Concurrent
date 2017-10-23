package zychaowill.discover.smallskill.communication.pc.piped;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Run {
	public static void main(String[] args) {
		try {
			WriteData writeData = new WriteData();
			ReadData readData = new ReadData();

			PipedInputStream in = new PipedInputStream();
			PipedOutputStream out = new PipedOutputStream();

			out.connect(in);
			
			ThreadRead reader = new ThreadRead(readData, in);
			reader.start();
			
			Thread.sleep(2000);
			
			ThreadWrite writer = new ThreadWrite(writeData, out);
			writer.start();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
