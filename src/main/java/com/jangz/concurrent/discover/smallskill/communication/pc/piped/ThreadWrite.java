package com.jangz.concurrent.discover.smallskill.communication.pc.piped;

import java.io.PipedOutputStream;

public class ThreadWrite extends Thread {

	private WriteData write;
	private PipedOutputStream out;

	public ThreadWrite(WriteData write, PipedOutputStream out) {
		super();
		this.write = write;
		this.out = out;
	}

	@Override
	public void run() {
		write.writeMetho(out);
	}
}
