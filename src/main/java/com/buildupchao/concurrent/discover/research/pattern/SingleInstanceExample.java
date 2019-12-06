package com.buildupchao.concurrent.discover.research.pattern;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author buildupchao
 */
@Slf4j
public class SingleInstanceExample {

	@Test
	public void singleInstanceThread() {
		Runnable target = () -> log.info("Object id is {}.", SingleInstance.instance().hashCode());
		Thread t1 = new Thread(target);
		Thread t2 = new Thread(target);
		Thread t3 = new Thread(target);
		t1.start();
		t2.start();
		t3.start();
	}

	@Test
	public void singleInstanceLazyThread() {
		Runnable target = () -> log.info("Object id is {}.", SingleInstanceLazy.newInstance().hashCode());
		Thread t1 = new Thread(target);
		Thread t2 = new Thread(target);
		Thread t3 = new Thread(target);
		t1.start();
		t2.start();
		t3.start();
	}

	@Test
	public void singleInstanceLazyUsingStaticInnerClassThread() {
		Runnable target = () -> log.info("Object id is {}.", SingleInstanceLazyUsingStaticInnerClass.getInstance().hashCode());
		Thread t1 = new Thread(target);
		Thread t2 = new Thread(target);
		Thread t3 = new Thread(target);
		t1.start();
		t2.start();
		t3.start();
	}

	@Test
	public void usingStaticInnerClassWithSerializing() {
		out();
		in();
	}

	@Test
	public void out() {
		try {
			SingleInstanceLazyUsingStaticInnerClass instance = SingleInstanceLazyUsingStaticInnerClass.getInstance();
			String filepath = System.getProperty("user.dir") + "/src/zychaowill/discover/research/pattern/single.txt";
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(filepath)));
			out.writeObject(instance);
			out.close();
			log.info("out is {}.", instance.hashCode());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void in() {
		try {
			String filepath = System.getProperty("user.dir") + "/src/zychaowill/discover/research/pattern/single.txt";
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(filepath)));
			SingleInstanceLazyUsingStaticInnerClass instance = (SingleInstanceLazyUsingStaticInnerClass) in.readObject();
			in.close();
			log.info("in is {}.", instance.hashCode());
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
