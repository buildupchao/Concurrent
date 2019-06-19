package com.buildupchao.concurrent.discover.smallskill.communication.pc.piped;

import java.io.IOException;
import java.io.PipedInputStream;

public class ReadData {
	
	public void readMethod(PipedInputStream in) {
		try {
			byte[] buffer = new byte[512];
		int len = -1;
			while ((len = in.read(buffer)) != -1) {
				String newData = new String(buffer, 0, len);
				System.out.print(newData + "\t");
			}
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
