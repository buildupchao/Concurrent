package com.buildupchao.concurrent.discover.smallskill.communication.pc.backup;

public class BackupB extends Thread {
	
	private DBTools dbTools;

	public BackupB(DBTools dbTools) {
		super();
		this.dbTools = dbTools;
	}
	
	@Override
	public void run() {
		dbTools.backupB();
	}
}
