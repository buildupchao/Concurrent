package zychaowill.discover.smallskill.pc.backup;

public class BackupA extends Thread {
	
	private DBTools dbTools;

	public BackupA(DBTools dbTools) {
		super();
		this.dbTools = dbTools;
	}
	
	@Override
	public void run() {
		dbTools.backupA();
	}
}
